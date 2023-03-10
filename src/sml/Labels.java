package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents the registered for the instruction of a program
 * The same label cannot be used by more than 1 instruction at a time.
 *
 * @author Nicol Luis Yumang
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		if(labels.containsKey(label)){
			System.out.println("Label: " + label + " already exists");
		} else {
			labels.put(label, address);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Labels labels1 = (Labels) o;
		return labels.equals(labels1.labels);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// The provided label might not exist in labels Map.
		// in this case, a NullPointerException is thrown by the get() method.
		// when a NullPointerException is thrown, the catch block handles this by returning
		// -1 or the NORMAL_PROGRAM_COUNTER_UPDATE. The program should continue without breaking
		// skipping the jump instruction, and moving to the next one.
		try {
			return labels.get(label);
		} catch (NullPointerException e){
			System.out.println("Label: " + label + " does not exist");
			return -1;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(labels);
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " -> " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]"));
	}
}
