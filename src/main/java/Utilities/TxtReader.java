package Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TxtReader {
    private final String FILE_NAME_INPUT = "src/main/resources/car_input.txt";
    private final String FILE_NAME_OUTPUT = "src/main/resources/car_output.txt";

    public List<String> readLines() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_NAME_OUTPUT)).stream().skip(1).collect(Collectors.toList());
        return lines;
    }

    public static void main(String[] args)throws IOException {
        TxtReader txtReader =new TxtReader();
        List<String>lines=txtReader.readLines();
        System.out.println(lines);
        //txtReader.readRegistrationNumbersUsingRegex();
    }

    public List<String> readRegistrationNumbersUsingRegex() throws IOException {
        Pattern pattern =Pattern.compile("([A-Z]{2}[0-9]{1,2}.[A-Z]{2,3})");
        Matcher matcher=pattern.matcher(Files.readAllLines(Paths.get(FILE_NAME_INPUT)).toString());
        List<String>allMatcher=new LinkedList<>();
        while(matcher.find()){
            System.out.println(matcher.group());
            allMatcher.add(matcher.group());
        }
        return allMatcher;
    }
}