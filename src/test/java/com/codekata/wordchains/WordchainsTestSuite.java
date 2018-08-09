package com.codekata.wordchains;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class WordchainsTestSuite {

    @Test
    public void testLoadDictionaryAsFile() {
        //Given
        DictionaryLoader dictionaryFromFile = new DictionaryFromFileLoader();
        //When
        String[] words = dictionaryFromFile.loadDictionary();
        List<String> result = Arrays.asList(words);
        //Then
        assertTrue(result.contains("cat"));
    }

    @Test
    public void testLoadDictionaryFromLink() {
        //Given
        DictionaryLoader dictionaryFromLink = new DictionaryFromLinkLoader();
        //When
        String[] words = dictionaryFromLink.loadDictionary();
        List<String> result = Arrays.asList(words);
        //Then
        assertTrue(result.contains("dog"));
    }

    @Test
    public void testGetChain() {
        //Given
        WordchainsCreator wordchain = new WordchainsCreator(new String[] {"cat", "code", "cog", "cot", "dog", "goad",
                "gold", "lead", "load", "robs", "rode", "rods", "ruby", "rubs"});
        //When
        String[] chain1 = wordchain.getChain("cat", "dog");
        String[] chain2 = wordchain.getChain("ruby", "code");
        String[] chain3 = wordchain.getChain("lead", "gold");
        //Then
        assertArrayEquals(new String[]{"cat", "cot", "cog", "dog"}, chain1);
        assertArrayEquals(new String[]{"ruby", "rubs", "robs", "rods", "rode", "code"}, chain2);
        assertArrayEquals(new String[]{"lead", "load", "goad", "gold"}, chain3);
    }

    @Test
    public void testGetChainWithWordOutsideDictionary() {
        //Given
        WordchainsCreator wordchain = new WordchainsCreator(new String[] {"code", "cog", "cot", "dog", "goad", "gold",
                "lead", "load", "robs", "rode", "rods", "ruby", "rubs"});
        //When
        String[] chain = wordchain.getChain("cat", "dog");
        //Then
        assertTrue(chain.length == 0);
    }

    @Test
    public void testGetChainWithDictionaryAsFile() {
        //Given
        DictionaryLoader dictionaryFromFile = new DictionaryFromFileLoader();
        WordchainsCreator wordchain = new WordchainsCreator(dictionaryFromFile.loadDictionary());
        //When
        String[] chain = wordchain.getChain("cat", "dog");
        //Then
        assertArrayEquals(new String[]{"cat", "cot", "cog", "dog"}, chain);
    }

    @Test
    public void testGetChainWithDictionaryFromLink() {
        //Given
        DictionaryLoader dictionaryFromLink = new DictionaryFromLinkLoader();
        WordchainsCreator wordchain = new WordchainsCreator(dictionaryFromLink.loadDictionary());
        //When
        String[] chain = wordchain.getChain("bond", "gold");
        //Then
        assertArrayEquals(new String[]{"bond", "bold", "gold"}, chain);
    }

    @Test
    public void testTimeOfGetChainWithDictionaryAsFile() {
        //Given
        long begin = System.currentTimeMillis();
        DictionaryLoader dictionaryFromFile = new DictionaryFromFileLoader();
        WordchainsCreator wordchain = new WordchainsCreator(dictionaryFromFile.loadDictionary());
        //When
        String[] chain = wordchain.getChain("cat", "dog");
        //Then
        printChain(chain);
        long end = System.currentTimeMillis();
        assertArrayEquals(new String[]{"cat", "cot", "cog", "dog"}, chain);
        System.out.println("Finding word chain has taken: " + (end - begin) + "ms");
    }

    @Test
    public void testTimeOfGetChainWithDictionaryFromLink() {
        //Given
        long begin = System.currentTimeMillis();
        DictionaryLoader dictionaryFromLink = new DictionaryFromLinkLoader();
        WordchainsCreator wordchain = new WordchainsCreator(dictionaryFromLink.loadDictionary());
        //When
        String[] chain = wordchain.getChain("cat", "dog");
        //Then
        printChain(chain);
        long end = System.currentTimeMillis();
        assertArrayEquals(new String[]{"cat", "cot", "cog", "dog"}, chain);
        System.out.println("Finding word chain has taken: " + (end - begin) + "ms");
    }

    private void printChain(String[] words) {
        for (String word : words) {
            System.out.println(word);
        }
    }
}
