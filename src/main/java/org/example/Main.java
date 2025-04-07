package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        String[] names = { "Gabriel", "Hanna", "Simon", "Slava", "Dragan", "Martin", "Anna" };

        UnaryOperator<String> withMiddleInitial = (name) -> {
            char middleInitial = (char) ('A' + new Random().nextInt(26));
            return name + " " + middleInitial + ".";
        };

        List<UnaryOperator<String>> nameFunctions = Arrays.asList(
                String::toUpperCase,
                withMiddleInitial,
                Main::appendReversedLastName,
                name -> "[" + name + "]",
                String::trim,
                s -> s.replace("A", "@"),
                Main::reverseEntireString
        );

        String[] transformedNames = listTransformer(nameFunctions, names);
        Arrays.stream(transformedNames).forEach(System.out::println);
    }

    static String[] listTransformer(List<UnaryOperator<String>> functions, String[] list) {
        String[] result = Arrays.copyOf(list, list.length);

        functions.forEach(func -> {
            for (int i = 0; i < result.length; i++) {
                result[i] = func.apply(result[i]);
            }

            Arrays.stream(result).forEach(System.out::println);
            System.out.println("----------------------------------");
        });

        return result;
    }

    static String appendReversedLastName(String name) {
        String first = name.split(" ")[0];
        String reversed = new StringBuilder(first).reverse().toString();
        return name + " " + reversed;
    }

    static String reverseEntireString(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
