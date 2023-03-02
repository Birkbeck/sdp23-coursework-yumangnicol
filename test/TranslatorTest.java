import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Labels;
import sml.Translator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TranslatorTest {
    Translator translator;
    Labels labels;
    List<Instruction> program;

    @BeforeEach
    void setUp() {
        labels = new Labels();
        program = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        translator = null;
        labels = null;
        program = null;
    }

    @Test
    void readAndTranslateTestOne() {
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
    void readAndTranslateTestTwo() {
        translator = new Translator("resources/program2.txt");
        try {
            translator.readAndTranslate(labels, program);
        } catch (IOException e){
            System.out.println("File not found");
        }
        int expectedSize = 6;
        Assertions.assertEquals(expectedSize, program.size());
    }
}
