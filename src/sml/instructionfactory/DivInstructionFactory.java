package sml.instructionfactory;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.DivInstruction;

public class DivInstructionFactory implements InstructionFactory {
    @Override
    public Instruction create(String label, String[] operands) {
        return new DivInstruction(label, Registers.Register.valueOf(operands[0]), Registers.Register.valueOf(operands[1]));
    }
}
