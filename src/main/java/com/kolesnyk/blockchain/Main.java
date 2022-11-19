package com.kolesnyk.blockchain;

public class Main {
    public static void main(String... args) {
        Blockchain blockchain = new Blockchain();
        for (int i = 0; i < 2; i++) {
            BlockchainService.mineNewBlock(blockchain);
        }
        System.out.println(blockchain);
    }
}
