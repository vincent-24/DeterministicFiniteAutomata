package FiniteAutomata;

import java.util.ArrayList;

public class FiniteAutomaton {
    private ArrayList<Integer> states;
    private ArrayList<Character> alphabet;
    private ArrayList<ArrayList<Character>> transitionTable;
    private int initialState = 0;
    private int currentState;
    private ArrayList<Integer> finalStates;

    public FiniteAutomaton() {
        this.states = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.transitionTable = new ArrayList<>();
        currentState = initialState;
    }

    ArrayList<Integer> getStates() {
        return this.states;
    }

    public void setStates(ArrayList<Integer> states) {
        this.states.addAll(states);
    }

    ArrayList<Integer> getFinalStates() {
        return this.finalStates;
    }

    public void setFinalStates(ArrayList<Integer> finalState) {
        this.finalStates = finalState;
    }

    ArrayList<Character> getAlphabet() {
        return this.alphabet;
    }

    public void setAlphabet(ArrayList<Character> alphabet) {
        this.alphabet.addAll(alphabet);
    }

    ArrayList<ArrayList<Character>> getTransitionTable() {
        return this.transitionTable;
    }

    public void setTransitionTable(ArrayList<ArrayList<Character>> transitionTable) {
        this.transitionTable.addAll(transitionTable);
    }

    int getInitialState() {
        return initialState;
    }

    int getCurrentState() {
        return currentState;
    }
    
}
