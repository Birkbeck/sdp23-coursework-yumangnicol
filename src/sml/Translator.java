package sml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import java.lang.reflect.*;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        try {
            String className = opcode.substring(0,1).toUpperCase() + opcode.substring(1) + "Instruction";
            Class<?> instructionClass = Class.forName("sml.instruction." + className);

            // Assumes that there will always be just one constructor
            Constructor<?> constructor = instructionClass.getConstructors()[0];
            Class<?>[] paramTypes = constructor.getParameterTypes();

            Object[] params = new Object[paramTypes.length];
            params[0] = label;

            String[] stringParams = line.trim().split("\\s+");

            for (int i = 0; i < stringParams.length; i++) {
                params[i + 1] = parseParameter(stringParams[i], paramTypes[i+1]);
            }

            return (Instruction) constructor.newInstance(params);
        } catch (ClassNotFoundException e){
            System.out.println("Unknown instruction: " + opcode);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

            // TODO: add code for all other types of instructions ✅

            // TODO: Then, replace the switch by using the Reflection API ✅

            // TODO: Next, use dependency injection to allow this machine class
            //       to work with different sets of opcodes (different CPUs)
        return null;
    }

    private Object parseParameter(String param, Class<?> type) {
        if(type == sml.RegisterName.class){
            return Registers.Register.valueOf(param);
        } else if (type == String.class) {
            return param;
        } else if (type == int.class) {
            return Integer.parseInt(param);
        }
        System.out.println("Unknown Parameter Type: " + type);
        return null;
    }

    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}