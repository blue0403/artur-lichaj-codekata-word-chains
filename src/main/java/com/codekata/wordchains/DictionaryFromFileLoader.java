package com.codekata.wordchains;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DictionaryFromFileLoader implements DictionaryLoader {

    @Override
    public String[] loadDictionary() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("file/wordlist.txt").getFile());
            TxtToArrayConverter converter = new TxtToArrayConverter();
            return converter.convertToArray(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
