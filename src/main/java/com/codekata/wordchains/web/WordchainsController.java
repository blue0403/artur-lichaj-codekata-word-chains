package com.codekata.wordchains.web;

import com.codekata.wordchains.DictionaryFromLinkLoader;
import com.codekata.wordchains.DictionaryLoader;
import com.codekata.wordchains.WordchainsCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/wordchains")
public class WordchainsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordchainsController.class);

    @RequestMapping(method = RequestMethod.GET, value = "getChain")
    public String[] getChain(@RequestParam String from, @RequestParam String to) {
        DictionaryLoader dictionary = new DictionaryFromLinkLoader();
        WordchainsCreator wordchain = new WordchainsCreator(dictionary.loadDictionary());
        LOGGER.info("Creating word chain between " + from + " and " + to);
        for (String word : wordchain.getChain(from, to)) {
            LOGGER.info(word);
        }
        return wordchain.getChain(from, to);
    }
}
