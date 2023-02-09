import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import sml.Labels;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LabelsTest {
    Labels labels;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp(){
        labels = new Labels();
        labels.addLabel("S1", 4);
        labels.addLabel("D3", 0);
        labels.addLabel("P6", 6);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown(){
        labels = new Labels();
        System.setOut(standardOut);
    }

    @Test
    void getAddressNull() {
        Assertions.assertEquals(-1, labels.getAddress("non-existing label"));
    }

    @Test
    void getAddressValid() {
        Assertions.assertEquals(4, labels.getAddress("S1"));
    }
    @Test
    void toStringValid() {
        String expected = "[D3 -> 0, P6 -> 6, S1 -> 4]";
        Assertions.assertEquals(expected, labels.toString());
    }

    @Test
    void addLabelExisting() {
        labels.addLabel("D3", 500);
        String expected = "Label: D3 already exists";
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void addLabelValid() {
        labels.addLabel("N2", 80);
        Assertions.assertEquals(80, labels.getAddress("N2"));
    }

    @Test
    void equalsValid() {
        Labels other = new Labels();
        other.addLabel("S1", 4);
        other.addLabel("D3", 0);
        other.addLabel("P6", 6);

        Assertions.assertEquals(labels, other);
    }

    @Test
    void equalsInvalid() {
        Labels other = new Labels();
        other.addLabel("S1", 4);
        other.addLabel("D3", 0);
        other.addLabel("P3", 6);

        Assertions.assertNotEquals(labels, other);
    }

}
