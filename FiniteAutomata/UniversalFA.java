package FiniteAutomata;
import java.util.ArrayList;
import java.util.Iterator;

public class UniversalFA {
    private FiniteAutomaton finiteMachine;
    private ArrayList<Character> testString;
    private final int initialState;
    private final ArrayList<Integer> finalStates;
    private int currentState;
    private boolean exit = false;
    private Iterator<Character> nextSymbolIterator;

    public UniversalFA(FiniteAutomaton finiteMachine) {
        this.finiteMachine = finiteMachine;
        initialState = finiteMachine.getInitialState();
        currentState = finiteMachine.getCurrentState();
        finalStates = finiteMachine.getFinalStates();
    }

    public void testString(String str) {
        setSymbolIterator(str);
        currentState = initialState; 
        char symbol = ' ';
        while (!exit) {
            if (nextSymbolIterator.hasNext()) {
                symbol = nextSymbolIterator.next();
                if (checkSymbol(symbol)) 
                    currentState = nextState(currentState, symbol);
                else 
                    exit = true; 
            } 
            else 
                exit = true; 
        }
        if (finalStates.contains(currentState) && checkSymbol(symbol) != false) 
            accept();
        else 
            reject();
    }

    private void setSymbolIterator(String str) {
        testString = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) 
            testString.add(str.charAt(i));
        nextSymbolIterator = testString.iterator();
        exit = false; 
    }

    private boolean checkSymbol(char symbol) {
        return finiteMachine.getAlphabet().contains(symbol);
    }

    private int nextState(int state, char symbol) {
        for (ArrayList<Character> transition : finiteMachine.getTransitionTable()) {
            if (transition.get(0) == (char)(state + '0') && transition.get(1) == symbol) 
                return transition.get(2) - '0';
        }
        return state; 
    }

    private void reject() {
        String result = testString.toString().replaceAll("[\\[\\],\\s]", "");
        if (result.length() > 7) {
            System.out.println(result + "\tReject");
        }
        else {
            System.out.println(result + "\t\tReject");
        }
    }

    private void accept() {
        String result = testString.toString().replaceAll("[\\[\\],\\s]", "");
        if (result.length() > 7) {
            System.out.println(result + "\tAccept");
        }
        else {
            System.out.println(result + "\t\tAccept");
        }
    }
}
