package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

public class OutputInstruction extends Instruction {

    private final RegisterName source;

    public static final String OP_CODE = "out";

    public OutputInstruction(String label, RegisterName source){
        super(label, OP_CODE);
        this.source = source;
    }

    @Override
    public int execute(Machine machine) {
        System.out.println(machine.getRegisters().get(source));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputInstruction that = (OutputInstruction) o;
        return Objects.equals(label, that.label) && source.equals(that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source);
    }
}
