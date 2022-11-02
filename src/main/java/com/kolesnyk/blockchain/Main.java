package com.kolesnyk.blockchain;

public class Main {
    public static void main(String... args) {
        Blockchain blockchain = new Blockchain();
        System.out.println(Blockchain.hash(blockchain.lastBlock()));
        System.out.println("blockchain.proofOfWork(1) = " + blockchain.proofOfWork(1));

        System.out.println(Blockchain.encrypt("1" + "20569008"));
    }
}
