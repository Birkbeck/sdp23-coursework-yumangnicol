package sml.instructionfactory;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.JnzInstruction;

public class JnzInstructionFactory implements InstructionFactory {
    @Override
    public Instruction create(String label, String[] operands) {
        return new JnzInstruction(label, Registers.Register.valueOf(operands[0]), operands[1]);
    }
}
