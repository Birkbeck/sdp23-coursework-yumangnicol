package sml;

/**
 * Represents an interface of a Factory of Machine instructions
 * @author Nicol Luis Yumang
 */
public interface  InstructionFactory {
    /**
     * Returns an instance of a machine instruction created from the details
     * of the translated program instruction
     *
     * @param label label of a program instruction
     * @param operands operands used in the program instruction (can be registers, values, labels)
     * @return an instance of a machine instruction
     */
    Instruction create (String label, String[] operands);
}
