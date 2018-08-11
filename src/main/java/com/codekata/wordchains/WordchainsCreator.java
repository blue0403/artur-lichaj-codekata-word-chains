package com.codekata.wordchains;

import java.util.ArrayDeque;
import java.util.Deque;

public class WordchainsCreator {
    private String[] dictionary;

    public WordchainsCreator(String[] dictionary) {
        this.dictionary = dictionary;
    }

    public String[] getChain(String from, String to) {
        if (!(checkIfDictionaryContains(from) && checkIfDictionaryContains(to))) return new String[0];
        int lengthOfChain = 8;
        for (int i = 0; i < lengthOfChain; i++) {
            Deque<String> words = new ArrayDeque<>();
            createChain(from, to, words, i);
            if (words.size() > 1) return words.toArray(new String[words.size()]);
        }
        return new String[0];
    }

    private boolean createChain(String from, String to, Deque<String> wordsDeque, int maxLength) {
        wordsDeque.offerLast(from);
        if (wordsDeque.size() > maxLength) return false;
        else if (from.equals(to)) return true;

        for (String word : dictionary) {
            if (word.length() == from.length() && checkDifference(word, from) == 1 && !wordsDeque.contains(word)) {
                if (createChain(word, to, wordsDeque, maxLength)) return true;
                wordsDeque.pollLast();
            }
        }
        return false;
    }

    private int checkDifference(String wordA, String wordB) {
        int diff = 0;
        for (int i = 0; i < wordA.length(); i++) {
            if (wordA.charAt(i) != wordB.charAt(i)) diff++;
        }
        return diff;
    }

    private boolean checkIfDictionaryContains(String word) {
        for (String text : dictionary) {
            if (text.equals(word)) return true;
        }
        return false;
    }
}
