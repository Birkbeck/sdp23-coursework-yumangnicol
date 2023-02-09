package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.*;

public class OutputInstructionTest {
    private Machine machine;
    private Registers registers;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        System.setOut(new PrintStream(outputStreamCaptor));
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        System.setOut(standardOut);
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        Instruction instruction = new OutputInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals("5", outputStreamCaptor.toString().trim());
    }

    @Test
    void executeValidTwo() {
        registers.set(ESP, -5);
        Instruction instruction = new OutputInstruction(null, ESP);
        instruction.execute(machine);
        Assertions.assertEquals("-5", outputStreamCaptor.toString().trim());
    }

    @Test
    void equalsValid() {
        Instruction i1 = new OutputInstruction("F1", EAX);
        Instruction i2 = new OutputInstruction("F1", EAX);
        Assertions.assertEquals(i1, i2);
    }

    @Test
    void equalsInvalid() {
        Instruction i1 = new OutputInstruction(null, EAX);
        Instruction i2 = new OutputInstruction("F1", EAX);
        Assertions.assertNotEquals(i1, i2);
    }
}
