package com.codekata.wordchains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryFromFileLoader implements DictionaryLoader {

    @Override
    public String[] loadDictionary() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("file/wordlist.txt").getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> words = new ArrayList<>();
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word);
            }
            br.close();
            return words.toArray(new String[words.size()]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
