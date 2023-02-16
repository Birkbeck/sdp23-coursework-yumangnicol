package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class InstructionFactory {

    public static InstructionFactory instance;

    public static InstructionFactory getInstance() {
        if(instance == null){
            return new InstructionFactory();
        }
        return instance;
    }

    public Instruction newInstanceOf(String opcode, String label, String[] operands) {
        String className = opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";

        try {
            Class<?> instructionClass = Class.forName("sml.instruction." + className);
            for (Constructor<?> constructor : instructionClass.getConstructors()) {
                if (constructor.getParameterCount() == operands.length + 1) {
                    Class<?>[] paramTypes = constructor.getParameterTypes();
                    Object[] params = new Object[paramTypes.length];
                    params[0] = label;
                    for (int i = 0; i < operands.length; i++) {
                        params[i + 1] = parseParameter(operands[i], paramTypes[i + 1]);
                    }
                    return (Instruction) constructor.newInstance(params);
                }
            }
        } catch(ClassNotFoundException e){
            System.out.println("Unknown instruction: " + opcode);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Object parseParameter(String param, Class<?> type) {
        if (type == String.class) {
            return param;
        }

        if(type == sml.RegisterName.class){
            return Registers.Register.valueOf(param);
        } else if (type == int.class) {
            return Integer.parseInt(param);
        }

        System.out.println("Unknown Parameter Type: " + type);
        return null;
    }
}
