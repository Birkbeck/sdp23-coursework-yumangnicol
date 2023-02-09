import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Machine;
import sml.Registers;
import sml.instruction.AddInstruction;

import static sml.Registers.Register.*;

public class MachineTest {

    Machine machine;
    Registers registers;

    @BeforeEach
    void setUp() {
        registers = new Registers();
        machine = new Machine(registers);
    }

    @AfterEach
    void tearDown() {
        registers = null;
        machine = null;
    }

    @Test
    void equalsValid() {
        var otherRegister = new Registers();
        var otherMachine = new Machine(otherRegister);
        Assertions.assertEquals(machine, otherMachine);
    }

    @Test
    void equalsInvalid() {
        var otherRegister = new Registers();
        var otherMachine = new Machine(otherRegister);
        var instruction = new AddInstruction(null, EAX, EBX);
        otherMachine.getProgram().add(instruction);
        Assertions.assertNotEquals(machine, otherMachine);
    }
}
