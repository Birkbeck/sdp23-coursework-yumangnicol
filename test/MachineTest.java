import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.*;
import sml.instruction.AddInstruction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static sml.Registers.Register.*;

public class MachineTest {
    Machine machine;
    Registers registers;
    Labels labels;

    PrintStream standardOut = System.out;
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        registers = new Registers();
        machine = new Machine(registers);
        labels = new Labels();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        registers = null;
        machine = null;
        System.setOut(standardOut);
    }

    @Test
    void executeValid() {
        Translator translator = new Translator("resources/program.txt");
        try {
            translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        machine.execute();
        String expected = "720";
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void executeValidTwo() {
        Translator translator = new Translator("resources/test/program4.txt");
        try {
            translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        machine.execute();
        String expected = "7";
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
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
