package com.codekata.wordchains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TxtToArrayConverter {

    public String[] convertToArray(InputStreamReader inputStreamReader) {

        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
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
