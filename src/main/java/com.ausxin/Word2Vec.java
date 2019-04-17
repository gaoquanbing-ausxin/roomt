package com.ausxin;

import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;
import java.util.Map;

/**
 * User: brucegao
 * Date: 2018-11-15
 */
public class Word2Vec {

    private static final String TRAIN_FILE_NAME = "data/word2vec/搜狗文本分类语料库已分词_corpus.txt";
    private static final String MODEL_FILE_NAME = "data/word2vec/搜狗文本分类语料库已分词_model.txt";

    //private static final String TRAIN_FILE_NAME = "data/word2vec/word2vec_corpus.txt";
    //private static final String MODEL_FILE_NAME = "data/word2vec/word2vec_model.txt";

    public static void main(String[] args) throws IOException {
        WordVectorModel wordVectorModel = trainOrLoadModel();
        printNearest("还款", wordVectorModel);
        printNearest("逾期", wordVectorModel);
        printNearest("催收员", wordVectorModel);

        // 文档向量
        DocVectorModel docVectorModel = new DocVectorModel(wordVectorModel);
        String[] documents = new String[]{
                "银行贷款",
                "去美国旅游",
                "再不还钱就起诉你",
                "苹果砸到牛顿的脑袋",
                "拒绝还钱",
        };

        System.out.println(docVectorModel.similarity(documents[0], documents[1]));
        System.out.println(docVectorModel.similarity(documents[0], documents[4]));

        for (int i = 0; i < documents.length; i++) {
            docVectorModel.addDocument(i, documents[i]);
        }

        printNearestDocument("金融", documents, docVectorModel);
        printNearestDocument("农业", documents, docVectorModel);
        printNearestDocument("我要去出游", documents, docVectorModel);
        printNearestDocument("娃哈哈", documents, docVectorModel);
    }

    static void printNearest(String word, WordVectorModel model) {
        System.out.printf("\n                                                Word     Cosine\n------------------------------------------------------------------------\n");
        for (Map.Entry<String, Float> entry : model.nearest(word)) {
            System.out.printf("%50s\t\t%f\n", entry.getKey(), entry.getValue());
        }
    }

    static void printNearestDocument(String document, String[] documents, DocVectorModel model) {
        printHeader(document);
        for (Map.Entry<Integer, Float> entry : model.nearest(document)) {
            System.out.printf("%50s\t\t%f\n", documents[entry.getKey()], entry.getValue());
        }
    }

    static WordVectorModel trainOrLoadModel() throws IOException {
        if (!IOUtil.isFileExisted(MODEL_FILE_NAME)) {
            if (!IOUtil.isFileExisted(TRAIN_FILE_NAME)) {
                System.err.println("语料不存在");
                System.exit(1);
            }
            Word2VecTrainer trainerBuilder = new Word2VecTrainer();
            return trainerBuilder.train(TRAIN_FILE_NAME, MODEL_FILE_NAME);
        }

        return loadModel();
    }

    static WordVectorModel loadModel() throws IOException {
        return new WordVectorModel(MODEL_FILE_NAME);
    }

    private static void printHeader(String query) {
        System.out.printf("\n%50s          Cosine\n------------------------------------------------------------------------\n", query);
    }
}
