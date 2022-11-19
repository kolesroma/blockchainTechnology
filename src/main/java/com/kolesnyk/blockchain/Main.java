package com.kolesnyk.blockchain;

import com.kolesnyk.blockchain.entity.Blockchain;
import com.kolesnyk.blockchain.service.BlockchainService;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        for (int i = 0; i < 5; i++) {
            BlockchainService.mineNewBlock(blockchain);
        }
        System.out.println(blockchain);
    }
}
