package com.kolesnyk.blockchain;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final List<Block> chain = new ArrayList<>();
    private final List<Transaction> currentTransactions = new ArrayList<>();

    public Blockchain() {
        newBlock(271102, "kolesnyk");
        newBlock(271102, "kolesnyk");
    }

    /**
     * add Block to chain
     *
     * @param proof        Докази проведенної роботи
     * @param previousHash Хеш попереднього блока
     * @return Новий блок
     */
    public Block newBlock(int proof, String previousHash) {
        List<Transaction> copyOfTransactions = new ArrayList<>(currentTransactions);
        Block newBlock = new Block(chain.size(), proof, previousHash, copyOfTransactions);
        currentTransactions.clear();
        chain.add(newBlock);
        return newBlock;
    }

    /**
     * Направляє нову транзакцію в наступний блок
     *
     * @param sender    Адреса відправника
     * @param recipient Адреса отримувача
     * @param amount    Сума
     * @return Індекс блока, що буде зберігати цю транзакцію
     */
    public int newTransaction(String sender, String recipient, int amount) {
        currentTransactions.add(new Transaction(sender, recipient, amount));
        return chain.size();
    }

    public static String hash(Block block) {
        String hashingInput = block.getIndex() +
                block.getTimestamp() +
                block.getProof() +
                block.getPreviousHash();
        return encrypt(hashingInput);
    }

    public static String encrypt(String src) {
        return Hashing.sha256()
                .hashString(src, StandardCharsets.UTF_8)
                .toString();
    }

    public Block lastBlock() {
        return chain.size() > 0
                ? chain.get(chain.size() - 1)
                : null;
    }

    /**
     * Проста перевірка алгоритму: Пошук числа p`, так як hash(pp`) містить 4
     * заголовних нуля, де p — попередній p є попереднім доказом, а p` - новим
     *
     * @return int
     */
    public int proofOfWork(int lastProofOfWork) {
        int proof = 0;
        while (!isProofValid(lastProofOfWork, proof)) {
            proof++;
        }
        return proof;
    }

    /**
     * Підтвердження доказу:
     * Чи містить hash(lastProof, proof) 271102
     */
    private boolean isProofValid(int lastProof, int proof) {
        String guessString = lastProof + "" + proof;
        String guessHash = encrypt(guessString);
        return guessHash.endsWith("271102");
    }
}
