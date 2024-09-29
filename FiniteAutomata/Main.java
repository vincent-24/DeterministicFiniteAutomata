package FiniteAutomata;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static String firstTestString = "";
    public static void main(String[] args) {
        run("M1.txt");
        System.out.println("\n\n");
        run("M2.txt");
        System.out.println("\n\n");
        run("M3.txt");
        System.out.println("\n\n");
        run("M4.txt");
    }

    private static void run(String file) {
        try {
            File machineFile = new File("Inputs/" + file);
            Scanner fileReader = new Scanner(machineFile);

            FiniteAutomaton finiteAutomaton = new FiniteAutomaton();
            finiteAutomaton.setStates(populateStates(fileReader, finiteAutomaton));
            finiteAutomaton.setFinalStates(populateFinalStates(fileReader, finiteAutomaton));
            finiteAutomaton.setAlphabet(populateAlphabet(fileReader, finiteAutomaton));
            finiteAutomaton.setTransitionTable(populateTransitionTable(fileReader, finiteAutomaton));

            UniversalFA universalFA = new UniversalFA(finiteAutomaton);
            printInfo(finiteAutomaton);
            printResults(fileReader, universalFA);

            fileReader.close();
        } catch (Exception e) {
            System.out.println("File: '" + file + "' not found.");
        }
    }

    private static void printInfo(FiniteAutomaton finiteAutomaton) {
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

    private static void printResults(Scanner fileReader, UniversalFA universalFA) {
        boolean firstPass = true;
        if (firstPass) {
            System.out.print("\t\t");
            universalFA.testString(firstTestString);
            firstPass = false;
        }
        while (fileReader.hasNextLine()) {
            System.out.print("\t\t");
            universalFA.testString(fileReader.nextLine());
        }
    }   

    private static ArrayList<Integer> populateStates(Scanner fileReader, FiniteAutomaton finiteAutomaton) {
        int numberOfStates = Integer.parseInt(fileReader.nextLine());
        ArrayList<Integer> states = new ArrayList<>();
        for (int i = 0; i < numberOfStates; i++) {
            states.add(i);
        }
        return states;
    }

    private static ArrayList<Integer> populateFinalStates(Scanner fileReader, FiniteAutomaton finiteAutomaton) {
        String[] finalStatesArray = fileReader.nextLine().split(" ");
        ArrayList<Integer> finalStates = new ArrayList<>();
        for (int i = 0; i < finalStatesArray.length; i++) {
            finalStates.add(Integer.parseInt(finalStatesArray[i]));
        }
        return finalStates;
    }

    private static ArrayList<Character> populateAlphabet(Scanner fileReader, FiniteAutomaton finiteAutomaton) {
        String[] alphabetArray = fileReader.nextLine().split(" ");
        ArrayList<Character> alphabet = new ArrayList<>();
        for (int i = 0; i < alphabetArray.length; i++) {
            alphabet.add(alphabetArray[i].charAt(0));
        }
        return alphabet;
    }

    private static ArrayList<ArrayList<Character>> populateTransitionTable(Scanner fileReader, FiniteAutomaton finiteAutomaton) {
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
            }
            else {
                firstTestString = line;
                break;
            }
        }
        return transitionTable;
    }   
}
