package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class MovInstructionTest {
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
        Instruction instruction = new MovInstruction(null, EAX, 3);
        instruction.execute(machine);
        Assertions.assertEquals(3, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 10);
        Instruction instruction = new MovInstruction(null, EAX, -4);
        instruction.execute(machine);
        Assertions.assertEquals(-4, machine.getRegisters().get(EAX));
    }

    @Test
    void equalsValid() {
        Instruction i1 = new MovInstruction("F1", EAX, 1);
        Instruction i2 = new MovInstruction("F1", EAX, 1);
        Assertions.assertEquals(i1, i2);
    }

    @Test
    void equalsInvalid() {
        Instruction i1 = new MovInstruction("F1", EAX, 1);
        Instruction i2 = new MovInstruction(null, EAX, 1);
        Assertions.assertNotEquals(i1, i2);
    }
}
