package sml.utils;

import com.google.inject.multibindings.MapBinder;
import sml.InstructionFactory;
import sml.instructionfactory.*;

import java.util.Map;

/**
 * Utility class that act as a registry of instructions accepted by the machine
 * @author Nicol Luis Yumang
 */
public class Registry {
    private static final Map<String, Class<? extends InstructionFactory>> REGISTERED_INSTRUCTION_FACTORIES =
            Map.of(
                    "add", AddInstructionFactory.class,
                    "sub", SubInstructionFactory.class,
                    "div", DivInstructionFactory.class,
                    "mul", MulInstructionFactory.class,
                    "jnz", JnzInstructionFactory.class,
                    "out", OutInstructionFactory.class,
                    "mov", MovInstructionFactory.class
            );

    /**
     * Binds a string value to an instance of a factory.
     * This is used by Guice to create an instance of each factory
     * @param instructionInputRouterBindings Map where the bindings are stored
     */
    public static void bindRegistrations(final MapBinder<String, InstructionFactory> instructionInputRouterBindings) {
        REGISTERED_INSTRUCTION_FACTORIES.forEach((key, value) -> instructionInputRouterBindings.addBinding(key).to(value));
    }
}
