package org.ttd.kata;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        numbers = findCommaSeperatedNumbers(numbers);
        return sum(numbers);
    }

    private String findCommaSeperatedNumbers(String numbers) {
        String regex = "-?\\d+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numbers);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            if (result.length() != 0) {
                result.append(", ");
            }
            result.append(matcher.group());
        }

        return result.toString();
    }

    private int sum(String numbers) {
        String[] numberArray = numbers.split(", ");
        int sum = 0;
        StringJoiner negativeNumbers = new StringJoiner(", ");
        for (String number : numberArray) {
            if(Integer.parseInt(number) < 0) {
                negativeNumbers.add(number);
            }
            sum += Integer.parseInt(number);
        }
        if(negativeNumbers.length() > 0){
            throw new IllegalArgumentException("Negative numbers not allowed" + ": " + negativeNumbers.toString());
        }
        return sum;
    }
}
