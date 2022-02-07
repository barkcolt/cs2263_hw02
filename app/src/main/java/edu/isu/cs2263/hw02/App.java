package edu.isu.cs2263.hw02;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        //create program options
        Options options = new Options();
        options.addOption("h", "help", false, "outputs possible commands");
        options.addOption("b", "batch", true, "batch file containing expressions to evaluate");
        options.addOption("o", "output", true, "sends output to the provided file and standard output");

        //accept input from user
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("h")) {
            //print help screen
            System.out.println("use: eval [OPTIONS]");
            System.out.println("Evaluates simple expression\n");
            System.out.println("-b, --batch <file>  batch file with expressions to be evaluated");
            System.out.println("-h,--help           print usage message");
            System.out.println("-o, --output <file> output file");
        }
        else if (cmd.hasOption("b")) {
            Equation toSolve = new Equation();
            File expressions = new File(cmd.getOptionValue("b"));
            Scanner expReader = new Scanner(expressions);
            LinkedList<String> equation = new LinkedList<>();
            while (expReader.hasNextLine()) {
                String line = expReader.nextLine();
                String[] converter = line.split(" ");
                for (String s : converter) {
                    equation.addLast(s);
                }
                System.out.println(toSolve.Solve(equation, toSolve));
            }
        }
        else if (cmd.hasOption("o")) {
            String lib = cmd.getOptionValue("o");
            System.out.println("Output Value: " + lib);
        }
        else {
            LinkedList<String> convertedExpression = new LinkedList<>();
            Equation toSolve = new Equation();
            List<String> expression = cmd.getArgList();
            for (String s : expression) {
                convertedExpression.addLast(s);
            }
            System.out.println(toSolve.Solve(convertedExpression, toSolve));
        }
    }
}
