package FiniteAutomata;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class that takes a FiniteAutomaton instance.
 * The UniversalFA can test if a string is allowed in its language by either accepting or rejecting the string.
 * @author Vincent Terrelonge
 * @since  2024-07-21
 */
public class UniversalFA {
    private FiniteAutomaton finiteMachine;
    private ArrayList<Character> testString;
    private final int initialState;
    private final ArrayList<Integer> finalStates;
    private int currentState;
    private boolean exit = false;
    private Iterator<Character> nextSymbolIterator;

    /**
     * Constructor to initalize a finite state machine's inital state, current state, and final state.
     * @param finiteMachine the finite machine built with the FiniteAutomaton class.
     */
    public UniversalFA(FiniteAutomaton finiteMachine) {
        this.finiteMachine = finiteMachine;
        initialState = finiteMachine.getInitialState();
        currentState = finiteMachine.getCurrentState();
        finalStates = finiteMachine.getFinalStates();
    }

    /**
     * Checks if a string is within the FA's language. It will accept the string if it is in the language, otherwise the string will get rejected.
     * @param str the string that will be checked if it is in the language.
     * @return true if the string is accepted; false if the string is rejected.
     */
    public boolean testString(String str) {
        setSymbolIterator(str);
        currentState = initialState; 
        char symbol = ' ';
        while (!exit) {
            if (!nextSymbolIterator.hasNext()) {
                exit = true; 
                break;
            }
            symbol = nextSymbolIterator.next();
            if (checkSymbol(symbol)) {
                currentState = nextState(currentState, symbol);
            } else {
                exit = true; 
            }
        }
        if (finalStates.contains(currentState) && checkSymbol(symbol) != false) {
            return accept();
        }
        return reject();
    }

    /**
     * Creates a character iterator from the given string.
     * @param str the string that will get iterated upon.
     */
    private void setSymbolIterator(String str) {
        testString = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            testString.add(str.charAt(i));
        }
        nextSymbolIterator = testString.iterator();
        exit = false; 
    }

    /**
     * Checks if a character is within the FA's alphabet set.
     * @param symbol the symbol that gets checked within the alphabet.
     * @return true if and only if the character is within the alphabet set.
     */
    private boolean checkSymbol(char symbol) {
        return finiteMachine.getAlphabet().contains(symbol);
    }

    /**
     * Gets the next state as determined by the current state, test string, and transition table.
     * @param state the current state of the FA.
     * @param symbol the current symbol being tested from the test string.
     * @return the next state as an integer.
     */
    private int nextState(int state, char symbol) {
        for (ArrayList<Character> transition : finiteMachine.getTransitionTable()) {
            if (transition.get(0) == (char)(state + '0') && transition.get(1) == symbol) {
                return transition.get(2) - '0';
            }
        }
        return state; 
    }

    private boolean reject() {
        return false;
    }

    private boolean accept() {
        return true;
    }
}