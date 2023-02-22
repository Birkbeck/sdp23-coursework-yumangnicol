package sml;

import com.google.inject.Inject;

import java.util.Map;

public class InstructionInputRouter {
    @Inject
    private Map<String, InstructionFactory> instructionFactories;

    public Instruction routeInstructionRequest(String opcode, String label, String[] operands) {
        try {
            return instructionFactories.get(opcode).create(label, operands);
        } catch (IllegalArgumentException e){
            System.out.println("ERROR IN TRANSLATING AN INSTRUCTION: " + e.getMessage());
        }
        return null;
    }
}
