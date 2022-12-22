package ru.job4j.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class ReconstructPhrase {

    private final Queue<Character> descendingElements;

    private final Queue<Character> evenElements;

    public ReconstructPhrase(Queue<Character> descendingElements, Queue<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder rsl = new StringBuilder();
        while (evenElements.size() > 0) {
            rsl.append(evenElements.poll());
            evenElements.poll();
        }
        return rsl.toString();
    }

    private String getDescendingElements() {
        StringBuilder rsl = new StringBuilder();
        Deque<Character> deque = new LinkedList<>(descendingElements);
        Character el = deque.pollLast();
        while (el != null) {
            rsl.append(el);
            el = deque.pollLast();
        }
        return rsl.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
