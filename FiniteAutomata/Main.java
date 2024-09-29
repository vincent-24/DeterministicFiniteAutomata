package FiniteAutomata;

import java.io.File;
import java.util.Scanner;

/**
 * Main class used to simulate a number of finite state automata one by one.
 * The FA will be able to recognize if a string belongs to the corresponding lanugage by Accepting or Rejecting the string.
 * All inputs are taken from a file in "Inputs/*.txt"
 * @author Vincent Terrelonge
 * @since  2024-07-21
 */
public class Main {
    public static void main(String[] args) {
        run("M1.txt");
        run("M2.txt");
        run("M3.txt");
        run("M4.txt");
        System.out.println("Our own FA's:\n");
        run("M5.txt");
        run("M6.txt");
        run("M7.txt");
    }

    /**
     * This method configures all following classes; FiniteAutomaton.java, UniversalFA.java, and Configure.java
     * It takes a file with the FA's information and processes if a string belongs to that language.
     * @param file the file which stores the FA's information.
     */
    private static void run(String file) {
        try {
            File machineFile = new File("Inputs/" + file);
            Scanner fileReader = new Scanner(machineFile);

            FiniteAutomaton finiteAutomaton = new FiniteAutomaton();
            Configure configureFA = new Configure(fileReader);
            finiteAutomaton.setStates(configureFA.populateStates());
            finiteAutomaton.setFinalStates(configureFA.populateFinalStates());
            finiteAutomaton.setAlphabet(configureFA.populateAlphabet());
            finiteAutomaton.setTransitionTable(configureFA.populateTransitionTable());
            UniversalFA universalFA = new UniversalFA(finiteAutomaton);
            configureFA.printInfo(finiteAutomaton);
            configureFA.printResults(universalFA);
            System.out.println("\n\n");

            fileReader.close();
        } catch (Exception e) {
            System.out.println("File: '" + file + "' not found.");
        }
    }
}