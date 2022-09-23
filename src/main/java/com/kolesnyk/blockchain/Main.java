package com.kolesnyk.blockchain;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String... args) {
        Blockchain blockchain = new Blockchain();
        System.out.println(Blockchain.hash(blockchain.lastBlock()));
        System.out.println("blockchain.proofOfWork(1) = " + blockchain.proofOfWork(1));

        System.out.println(Hashing.sha256()
                .hashString("1" + "20569008", StandardCharsets.UTF_8)
                .toString());
    }
}
