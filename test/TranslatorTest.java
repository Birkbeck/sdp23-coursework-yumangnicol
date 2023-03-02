import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Labels;
import sml.Translator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TranslatorTest {
    Translator translator;
    Labels labels;
    List<Instruction> program;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        labels = new Labels();
        program = new ArrayList<>();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        translator = null;
        labels = null;
        program = null;
        System.setOut(standardOut);
    }

    @Test
    void readAndTranslateValidOne() {
        translator = new Translator("resources/program.txt");
        try {
            translator.readAndTranslate(labels, program);
        } catch (IOException e){
            System.out.println("File not found");
        }
        int expectedSize = 7;
        Assertions.assertEquals(expectedSize, program.size());
    }

    @Test
    void readAndTranslateValidTwo() {
        translator = new Translator("resources/program2.txt");
        try {
            translator.readAndTranslate(labels, program);
        } catch (IOException e){
            System.out.println("File not found");
        }
        int expectedSize = 6;
        Assertions.assertEquals(expectedSize, program.size());
    }

    @Test
    void readAndTranslateLabelExist() {
        translator = new Translator("resources/test/program3.txt");
        try {
            translator.readAndTranslate(labels, program);
        } catch (IOException e){
            System.out.println("File not found");
        }
        String expected = "Label: f3 already exists";
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }


}
