package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents a Conditional Branch Machine Instruction
 * Machine program execution jumps to given branch label
 * if the given source register is equal to zero*
 * @author Nicol Luis Yumang
 */
public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String branch;
    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName source, String branch) {
        super(label, OP_CODE);
        this.source = source;
        this.branch = branch;
    }

    @Override
    public int execute(Machine m) {
        if (m.getRegisters().get(source) == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
        return m.getLabels().getAddress(branch);
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JnzInstruction that = (JnzInstruction) o;
        return Objects.equals(label, that.label) && source.equals(that.source) && branch.equals(that.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, branch);
    }
}
