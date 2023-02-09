import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Registers;

import static sml.Registers.Register.EAX;

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

}
