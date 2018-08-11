package com.codekata.wordchains;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class DictionaryFromLinkLoader implements DictionaryLoader {

    @Override
    public String[] loadDictionary() {

        try (InputStream inputStream = new URL("http://codekata.com/data/wordlist.txt").openConnection().getInputStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            TxtToArrayConverter converter = new TxtToArrayConverter();
            return converter.convertToArray(inputStreamReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
