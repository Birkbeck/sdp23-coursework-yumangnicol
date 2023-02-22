package sml.instructionfactory;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.OutInstruction;

public class OutInstructionFactory implements InstructionFactory {
    @Override
    public Instruction create(String label, String[] operands) {
        return new OutInstruction(label, Registers.Register.valueOf(operands[0]));
    }
}
