package sml;

public interface  InstructionFactory {
    Instruction create (String label, String[] operands);
}
