package com.codekata.wordchains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DictionaryFromLinkLoader implements DictionaryLoader {

    @Override
    public String[] loadDictionary() {
        try (InputStream inputStream = new URL("http://codekata.com/data/wordlist.txt").openConnection().getInputStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            List<String> dict = new ArrayList<>();
            String word;
            while ((word = reader.readLine()) != null) {
                dict.add(word);
            }
            reader.close();
            return dict.toArray(new String[dict.size()]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
