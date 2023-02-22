package sml.instructionfactory;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.MovInstruction;

public class MovInstructionFactory implements InstructionFactory {
    @Override
    public Instruction create(String label, String[] operands) {
        return new MovInstruction(label, Registers.Register.valueOf(operands[0]), Integer.parseInt(operands[1]));
    }
}
