package com.kolesnyk.blockchain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Block {
    private int index;
    private int proof;
    private String previousHash;
    private List<Transaction> transactions;

    private long timestamp;

    public Block(int index, int proof, String previousHash, List<Transaction> transactions) {
        this.index = index;
        this.proof = proof;
        this.previousHash = previousHash;
        this.transactions = transactions;
    }
}
