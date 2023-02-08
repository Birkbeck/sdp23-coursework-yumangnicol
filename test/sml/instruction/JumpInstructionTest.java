package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class JumpInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {

        Instruction i1 = new MoveInstruction(null, EAX, 1);
        Instruction i2 = new JumpInstruction(null, EAX, "f3");
        Instruction i3 = new MoveInstruction(null, EBX, 50); //skipped
        machine.getLabels().addLabel("f3", 3);
        Instruction i4 = new AddInstruction("f3", EBX, EAX);

        machine.getProgram().add(i1);
        machine.getProgram().add(i2);
        machine.getProgram().add(i3);
        machine.getProgram().add(i4);

        machine.execute();

        Assertions.assertEquals(1, registers.get(EBX));
    }

}
