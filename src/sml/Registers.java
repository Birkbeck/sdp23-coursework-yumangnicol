package sml;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents the set of Registers available to the machine.
 * An instance contains 8 register addresses.
 *
 * @author Nicol Luis Yumang
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();

    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI
    }

    /**
     * Constructor: Registers that are set to 0
     */
    public Registers() {
        clear(); // the class is final
    }


    /**
     * Sets the values to all registers to 0
     */
    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register)register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register)register);
    }

    /**
     * Compares the equality of this set of registers and another object.
     *
     * @param o concrete object to compare with
     * @return true if set of registers is equal to specified Object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers r) {
            return registers.equals(r.registers);
        }
        return false;
    }

    /**
     * Hashcode value of the set of Registers.
     *
     * @return hashcode value of this machine
     */
    @Override
    public int hashCode() {
        return registers.hashCode();
    }


    /**
     * String representation of the registers available to the machine.
     *
     * @return pretty formatted version of the registers.
     */
    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]")) ;
    }
}
