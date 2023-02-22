package sml;

import com.google.inject.Inject;

import java.util.Map;

/**
 * Represents a router that routes a program instruction to an appropriate factory
 * to be translated into machine instruction
 *
 * @author Nicol Luis Yumang
 */
public class InstructionInputRouter {
    @Inject
    private Map<String, InstructionFactory> instructionFactories;

    /**
     * Routes program instruction details to appropriate factory to be translated into machine instruction
     *
     * @param opcode operation code of the program instruction
     * @param label label of a program instruction
     * @param operands operands used in the program instruction (can be registers, values, labels)
     * @return machine instruction
     */
    public Instruction routeInstructionRequest(String opcode, String label, String[] operands) {
        try {
            return instructionFactories.get(opcode).create(label, operands);
        } catch (IllegalArgumentException e){
            System.out.println("ERROR IN TRANSLATING AN INSTRUCTION: " + e.getMessage());
        }
        return null;
    }
}
