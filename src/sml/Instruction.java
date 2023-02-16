package sml;

// TODO: write a JavaDoc for the class

/**
 * Represents an abstract instruction.
 *
 * @author Nicol Luis Yumang
 */
public abstract class Instruction {
	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	@Override
	public abstract boolean equals(Object obj);

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */
	public abstract int execute(Machine machine);

	public String getLabel() {
		return label;
	}

	/**
	 * Returns the unique label of the instruction suffixed with a colon (:)
	 *
	 * @return label
	 */
	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	/**
	 * Returns the operation code of an Instruction.
	 *
	 * @return operation code
	 */
	public String getOpcode() {
		return opcode;
	}

	@Override
	public abstract int hashCode();

	// TODO: What does abstract in the declaration below mean? ✅
	//       (Write a short explanation.)
	// Abstract methods do not have a body or implementation. Only the method signature is provided
	// Any class that contains an abstract method should also be an abstract class
	// Any class that extends an abstract class requires an implementation the abstract methods of the extended class

	/**
	 * String representation of an Instruction
	 *
	 * @return pretty formatted version of an instruction
	 */
	@Override
	public abstract String toString();
}
