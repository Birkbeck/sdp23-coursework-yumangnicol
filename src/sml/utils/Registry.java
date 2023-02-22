package sml.utils;

import com.google.inject.multibindings.MapBinder;
import sml.InstructionFactory;
import sml.instructionfactory.*;

import java.util.Map;

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

    public static void bindRegistrations(final MapBinder<String, InstructionFactory> instructionFactoryBindings) {
        REGISTERED_INSTRUCTION_FACTORIES.forEach((key, value) -> instructionFactoryBindings.addBinding(key).to(value));
    }
}
