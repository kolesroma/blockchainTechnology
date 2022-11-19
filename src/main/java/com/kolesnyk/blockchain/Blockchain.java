package com.kolesnyk.blockchain;

import com.google.common.hash.Hashing;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@ToString
public class Blockchain {
    private final List<Block> chain = new ArrayList<>();
    private final List<Transaction> currentTransactions = new ArrayList<>();

    public Blockchain() {
        newBlock(2711, "kolesnyk");
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

    public static int findProof(Block currentBlock) {
        int newProof = 0;
        while (!isProofValid(currentBlock, newProof)) {
            newProof++;
        }
        return newProof;
    }

    private static boolean isProofValid(Block currentBlock, int newProof) {
        String guessString = currentBlock.getIndex() +
                currentBlock.getTimestamp() +
                newProof +
                currentBlock.getPreviousHash();
        return encrypt(guessString).endsWith("2711");
    }
}
