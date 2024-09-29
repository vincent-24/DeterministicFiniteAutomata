package FiniteAutomata;

import java.util.ArrayList;

/**
 * A class to store information about a Finite Automaton.
 * Stores the FA's set of states, final states, alphabet set, transition table, initial state, and current state.
 * @author Vincent Terrelonge
 * @since  2024-07-21
 */
public class FiniteAutomaton {
    private ArrayList<Integer> states;
    private ArrayList<Character> alphabet;
    private ArrayList<ArrayList<Character>> transitionTable; 
    private int initialState = 0;
    private int currentState;
    private ArrayList<Integer> finalStates;

    /**
     * Constructor to initialize the FA's set of states, alphabet set, transition table, and current state.
     */
    public FiniteAutomaton() {
        this.states = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.transitionTable = new ArrayList<>();
        currentState = initialState;
    }

    /**
     * Gets the set of states.
     * @return the ArrayList of states.
     */
    ArrayList<Integer> getStates() {
        return this.states;
    }

    /**
     * Sets the FA's states.
     * @param states the ArrayList of states.
     */
    public void setStates(ArrayList<Integer> states) {
        this.states.addAll(states);
    }

    /**
     * Gets the set of final states.
     * @return the ArrayList of final states.
     */
    ArrayList<Integer> getFinalStates() {
        return this.finalStates;
    }

    /**
     * Sets the FA's final states.
     * @param finalState the ArrayList of final states.
     */
    public void setFinalStates(ArrayList<Integer> finalState) {
        this.finalStates = finalState;
    }

    /**
     * Gets the alphabet set.
     * @return the alphabet ArrayList.
     */
    ArrayList<Character> getAlphabet() {
        return this.alphabet;
    }

    /**
     * Sets the FA's alphabet set.
     * @param alphabet the language's alphabet ArrayList.
     */
    public void setAlphabet(ArrayList<Character> alphabet) {
        this.alphabet.addAll(alphabet);
    }

    /**
     * Gets the transition table.
     * @return the ArrayList of ArrayList transitions.
     */
    ArrayList<ArrayList<Character>> getTransitionTable() {
        return this.transitionTable;
    }

    /**
     * Sets the FA's transition table.
     * @param transitionTable the ArrayList of ArrayList transition table.
     */
    public void setTransitionTable(ArrayList<ArrayList<Character>> transitionTable) {
        this.transitionTable.addAll(transitionTable);
    }

    /**
     * Get the initial state.
     * @return the inital state as an integer.
     */
    int getInitialState() {
        return initialState;
    }

    /**
     * Get the current state.
     * @return the current state as an integer.
     */
    int getCurrentState() {
        return currentState;
    }
}