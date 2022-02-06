package edu.isu.cs2263.hw02;

import org.apache.commons.cli.*;

public class App {
    public static void main(String[] args) throws ParseException {
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
        if (cmd.hasOption("b")) {
            String lib = cmd.getOptionValue("b");
            System.out.println("Batch value: " + lib);
        }
        if (cmd.hasOption("o")) {
            String lib = cmd.getOptionValue("o");
            System.out.println("Output Value: " + lib);
        }
        else {System.out.println("Nothing done for it");}
    }
}
