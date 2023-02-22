package sml.instructionfactory;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.SubInstruction;

public class SubInstructionFactory implements InstructionFactory {
    @Override
    public Instruction create(String label, String[] operands) {
        return new SubInstruction(label, Registers.Register.valueOf(operands[0]), Registers.Register.valueOf(operands[1]));
    }
}
