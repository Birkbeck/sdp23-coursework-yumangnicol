package sml.instructionfactory;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.OutInstruction;

/**
 * Represents a factory that creates OutInstructions
 * @author Nicol Luis Yumang
 */
public class OutInstructionFactory implements InstructionFactory {
    @Override
    public Instruction create(String label, String[] operands) {
        return new OutInstruction(label, Registers.Register.valueOf(operands[0]));
    }
}
