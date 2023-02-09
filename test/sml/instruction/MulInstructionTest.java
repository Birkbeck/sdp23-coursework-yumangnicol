package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class MulInstructionTest {
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
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -8);
        registers.set(EBX, 9);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-72, machine.getRegisters().get(EAX));
    }

    @Test
    void equalsValid() {
        Instruction i1 = new MulInstruction("F1", EAX, EBX);
        Instruction i2 = new MulInstruction("F1", EAX, EBX);
        Assertions.assertEquals(i1, i2);
    }

    @Test
    void equalsInvalid() {
        Instruction i1 = new MulInstruction(null, EAX, EBX);
        Instruction i2 = new MulInstruction("F1", EAX, EBX);
        Assertions.assertNotEquals(i1, i2);
    }
}
