package FiniteAutomata;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that will configure a file and create arrylists for the states, final states, alphabet, and transition table.
 * This class will also print out the information from the given file and print out the test string results.
 * @author Vincent Terrelonge
 * @since  2024-07-21
 */
public class Configure {
    private String firstTestString = "";
    private Scanner fileReader;

    /**
     * Constructor to initialize a file with the finite machine's information.
     * @param fileReader the file that input will be read from.
     */
    public Configure(Scanner fileReader) {
        this.fileReader = fileReader;
    }

    /**
     * Takes the information from the input file and populates an arraylist with the FA's states.
     * @return an arraylist of the FA's states.
     */
    public ArrayList<Integer> populateStates() {
        int numberOfStates = Integer.parseInt(fileReader.nextLine());
        ArrayList<Integer> states = new ArrayList<>();
        for (int i = 0; i < numberOfStates; i++) {
            states.add(i);
        }
        return states;
    }

    /**
     * Takes the information from the input file and populates an arraylist with the FA's final states.
     * @return an arraylist of the FA's final states.
     */
    public ArrayList<Integer> populateFinalStates() {
        String[] finalStatesArray = fileReader.nextLine().split(" ");
        ArrayList<Integer> finalStates = new ArrayList<>();
        for (int i = 0; i < finalStatesArray.length; i++) {
            finalStates.add(Integer.parseInt(finalStatesArray[i]));
        }
        return finalStates;
    }

    /**
     * Takes the information from the input file and populates an arraylist with the FA's alphabet.
     * @return an arraylist of the FA's alphabet.
     */
    public ArrayList<Character> populateAlphabet() {
        String[] alphabetArray = fileReader.nextLine().split(" ");
        ArrayList<Character> alphabet = new ArrayList<>();
        for (int i = 0; i < alphabetArray.length; i++) {
            alphabet.add(alphabetArray[i].charAt(0));
        }
        return alphabet;
    }

    /**
     * Takes the information from the input file and populates an arraylist with the FA's transition table.
     * @return an arraylist of the FA's transition table.
     */
    public ArrayList<ArrayList<Character>> populateTransitionTable() {
        ArrayList<ArrayList<Character>> transitionTable = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            if (line.startsWith("(")) {
                line = line.substring(1, 6);
                String[] lineArray = line.split(" ");
                ArrayList<Character> transitions = new ArrayList<>();
                for (String element : lineArray) {
                    transitions.add(element.charAt(0));
                }
                transitionTable.add(transitions);
            } else {
                firstTestString = line;
                break;
            }
        }
        return transitionTable;
    } 

    /**
     * Prints the FA's information from the given file.
     * @param finiteAutomaton the machine that stored the information from the file.
     */
    public void printInfo(FiniteAutomaton finiteAutomaton) {
        System.out.println("Inputted Finite State Automaton Info:");

        System.out.print("\t1) set of states: {");
        for (int i = 0; i < finiteAutomaton.getStates().size() - 1; i++) {
            System.out.print("state " + finiteAutomaton.getStates().get(i) + ", ");
        }
        System.out.println("state " + finiteAutomaton.getStates().get(finiteAutomaton.getStates().size() - 1) + "}, initial State is state " + finiteAutomaton.getInitialState() + " (default).");

        System.out.print("\t2) set of final state(s): {");
        for (int i = 0; i < finiteAutomaton.getFinalStates().size() - 1; i++) {
            System.out.print("state " + finiteAutomaton.getFinalStates().get(i) + ", ");
        }
        System.out.println("state " + finiteAutomaton.getFinalStates().get(finiteAutomaton.getFinalStates().size() - 1) + "}");

        System.out.print("\t3) alphabet set: {");
        for (int i = 0; i < finiteAutomaton.getAlphabet().size() - 1; i++) {
            System.out.print(finiteAutomaton.getAlphabet().get(i) + ", ");
        }
        System.out.println(finiteAutomaton.getAlphabet().get(finiteAutomaton.getAlphabet().size() - 1) + "}");

        System.out.println("\t4) transitions:");
        for (int i = 0; i < finiteAutomaton.getTransitionTable().size(); i++) {
            System.out.println("\t\t" + finiteAutomaton.getTransitionTable().get(i));
        }

        System.out.println("Results of test strings:");
    }

    /**
     * Prints the results of the test string.
     * @param universalFA the class that takes an FA instance and can test any string to accept or reject.
     */
    public void printResults(UniversalFA universalFA) {
        boolean firstPass = true;
        if (firstPass) {
            System.out.print("\t\t");
            evaluateString(universalFA, firstTestString);
            firstPass = false;
        }
        while (fileReader.hasNextLine()) {
            System.out.print("\t\t");
            evaluateString(universalFA, fileReader.nextLine());
        }
    }   
    
    private void evaluateString(UniversalFA universalFA, String string) {
        if (universalFA.testString(string) == true) {
            printTestStringResults("Accept", string);
        } else {
            printTestStringResults("Reject", string);
        }
    }

    private void printTestStringResults(String decision, String string) {
        String result = string.replaceAll("[\\[\\],\\s]", "");
        if (result.length() > 7) {
            System.out.println(result + "\t" + decision);
        } else {
            System.out.println(result + "\t\t" + decision);
        }
    }  
}
