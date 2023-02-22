package sml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

/**
 * Represents the machine, the context in which programs run.
 * <p>
 * An instance contains 32 registers and methods to access and change them.
 *
 */
public final class Machine {

	private final Labels labels = new Labels();

	private final List<Instruction> program = new ArrayList<>();

	private final Registers registers;

	// The program counter; it contains the index (in program)
	// of the next instruction to be executed.
	private int programCounter = 0;

	/**
	 * Constructor:a machine with a set of registers
	 */
	public Machine(Registers registers) {
		this.registers = registers;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Machine m) {
			return Objects.equals(this.labels, m.labels)
					&& Objects.equals(this.program, m.program)
					&& Objects.equals(this.registers, m.registers)
					&& this.programCounter == m.programCounter;
		}
		return false;
	}

	/**
	 * Execute the program in program, beginning at instruction 0.
	 * Precondition: the program and its labels have been stored properly.
	 */
	public void execute() {
		programCounter = 0;
		registers.clear();
		while (programCounter < program.size()) {
			Instruction ins = program.get(programCounter);
			int programCounterUpdate = ins.execute(this);
			programCounter = (programCounterUpdate == NORMAL_PROGRAM_COUNTER_UPDATE)
				? programCounter + 1
				: programCounterUpdate;
		}
	}

	/**
	 * Returns the registered instruction labels
	 * @return labels
	 */
	public Labels getLabels() {
		return this.labels;
	}

	/**
	 * Returns the list of instructions of a program
	 * @return list of instructions
	 */
	public List<Instruction> getProgram() {
		return this.program;
	}

	/**
	 * Returns the registers available to the machine
	 * @return registers
	 */
	public Registers getRegisters() {
		return this.registers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(labels, program, registers, programCounter);
	}

	/**
	 * String representation of the program under execution.
	 * @return pretty formatted version of the code.
	 */
	@Override
	public String toString() {
		return program.stream()
				.map(Instruction::toString)
				.collect(Collectors.joining("\n"));
	}
}
