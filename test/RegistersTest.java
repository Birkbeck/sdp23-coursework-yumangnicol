import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

public class RegistersTest {

    Registers registers;

    @BeforeEach
    void setUp() {
        registers = new Registers();
    }

    @AfterEach
    void tearDown() {
        registers = null;
    }

    @Test
    void equalsValid() {
        var other = new Registers();
        Assertions.assertEquals(registers, other);
    }

    @Test
    void equalsInvalid() {
        var other = new Registers();
        other.set(EAX, 1);
        Assertions.assertNotEquals(registers, other);
    }

    @Test
    void clearValid() {
        registers.set(EAX, 30);
        registers.set(EBX, 200);
        registers.clear();
        Assertions.assertEquals(0, registers.get(EAX));
        Assertions.assertEquals(0, registers.get(EBX));
    }

    @Test
    void setAndGetValid() {
        registers.set(EAX, 20);
        int expected = 20;
        Assertions.assertEquals(expected,registers.get(EAX));
    }

    @Test
    void toStringValid() {
        registers.set(EAX, 20);
        String expected = "[EAX = 20, EBX = 0, ECX = 0, EDX = 0, ESP = 0, EBP = 0, ESI = 0, EDI = 0]";
        Assertions.assertEquals(expected,registers.toString());
    }

}
