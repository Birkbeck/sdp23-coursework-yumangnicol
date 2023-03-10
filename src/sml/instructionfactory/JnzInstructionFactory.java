package sml.instructionfactory;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.JnzInstruction;

/**
 * Represents a factory that creates JnzInstructions
 * @author Nicol Luis Yumang
 */
public class JnzInstructionFactory implements InstructionFactory {
    @Override
    public Instruction create(String label, String[] operands) {
        return new JnzInstruction(label, Registers.Register.valueOf(operands[0]), operands[1]);
    }
}
