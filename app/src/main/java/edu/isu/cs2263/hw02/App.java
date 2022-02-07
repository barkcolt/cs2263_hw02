package edu.isu.cs2263.hw02;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws ParseException, IOException {
        while (true) {
            Equation toSolve = new Equation();
            //create program options
            Options options = new Options();
            options.addOption("h", "help", false, "outputs possible commands");
            options.addOption("b", "batch", true, "batch file containing expressions to evaluate");
            options.addOption("o", "output", true, "sends output to the provided file and standard output");

            //accept input from user
            System.out.println("Enter new commands: ");
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            //output help screen
            if (cmd.hasOption("h")) {
                System.out.println("use: eval [OPTIONS]");
                System.out.println("Evaluates simple expression\n");
                System.out.println("-b, --batch <file>  batch file with expressions to be evaluated");
                System.out.println("-h,--help           print usage message");
                System.out.println("-o, --output <file> output file");
            }

            //solve from a batch file, check for a file to output to
            if (cmd.hasOption("b")) {
                File expressions = new File(cmd.getOptionValue("b"));
                Scanner expReader = new Scanner(expressions);
                LinkedList<String> equation = new LinkedList<>();
                while (expReader.hasNextLine()) {
                    String line = expReader.nextLine();
                    String[] converter = line.split(" ");
                    for (String s : converter) {
                        equation.addLast(s);
                    }
                    String solution = toSolve.Solve(equation, toSolve);
                    if (cmd.hasOption("o")) {
                        File output = new File(cmd.getOptionValue("o"));
                        if (output.exists()) {
                            FileWriter writer = new FileWriter(output, true);
                            writer.write("\n" + solution);
                            writer.close();
                        } else {
                            FileWriter writer = new FileWriter(output);
                            writer.write(solution);
                            writer.close();
                        }
                    }
                    System.out.println(solution);
                }
            }

            //runs expression given from command line, checks for file to output to
            else {
                LinkedList<String> convertedExpression = new LinkedList<>();
                List<String> expression = cmd.getArgList();
                for (String s : expression) {
                    convertedExpression.addLast(s);
                }
                if (convertedExpression.size() > 0) {
                    String solution = toSolve.Solve(convertedExpression, toSolve);
                    System.out.println(solution);
                    if (cmd.hasOption("o")) {
                        File output = new File(cmd.getOptionValue("o"));
                        FileWriter writer = new FileWriter(output);
                        writer.write(solution);
                        writer.close();
                    }
                }
            }
        }
    }
}
