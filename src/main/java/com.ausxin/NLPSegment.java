package com.ausxin;

import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: brucegao
 * Date: 2018-11-15
 */
public class NLPSegment {
    public static void main(String[] args) throws Exception {

        List<String> segLines = Files.readAllLines(Paths.get("data/nlp/cuishou.txt"))
                .parallelStream().map(x -> NLPTokenizer.analyze(x).toStringWithoutLabels()).collect(Collectors.toList());

        Files.write(Paths.get("data/word2vec/word2vec_corpus.txt"), segLines, StandardOpenOption.CREATE);

        //FileWriter fileWriter = new FileWriter("data/word2vec/word2vec_corpus.txt");
        //Files.readAllLines(Paths.get("data/nlp/cuishou.txt"), UTF_8).parallelStream().forEach(x ->
        //        {
        //            try {
        //                fileWriter.write(NLPTokenizer.analyze(x).toStringWithoutLabels());
        //                fileWriter.write("\r\n");
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        //        }
        //);
        //fileWriter.close();
    }
}
