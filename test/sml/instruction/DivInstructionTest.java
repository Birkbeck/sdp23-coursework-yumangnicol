package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class DivInstructionTest {
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
        registers.set(EAX, 11);
        registers.set(EBX, 2);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 10);
        registers.set(EBX, -6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
    }

    @Test
    void equalsValid() {
        Instruction i1 = new DivInstruction("F1", EAX, EBX);
        Instruction i2 = new DivInstruction("F1", EAX, EBX);
        Assertions.assertEquals(i1, i2);
    }

    @Test
    void equalsInvalid() {
        Instruction i1 = new DivInstruction(null, EAX, EBX);
        Instruction i2 = new DivInstruction("F1", EAX, EBX);
        Assertions.assertNotEquals(i1, i2);
    }
}
