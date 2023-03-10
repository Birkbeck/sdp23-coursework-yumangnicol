package sml.instructionfactory;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.AddInstruction;

/**
 * Represents a factory that creates AddInstructions
 * @author Nicol Luis Yumang
 */
public class AddInstructionFactory implements InstructionFactory {
    @Override
    public Instruction create(String label, String[] operands) {
        return new AddInstruction(label, Registers.Register.valueOf(operands[0]), Registers.Register.valueOf(operands[1]));
    }
}
