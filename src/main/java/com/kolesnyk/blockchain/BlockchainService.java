package com.kolesnyk.blockchain;

public class BlockchainService {
    /**
     * changes current proof to newProof in last block
     * so hash of this block ends with 2711;
     * adds new block to chain with proof -1
     */
    public static void mineNewBlock(Blockchain blockchain) {
        Block lastBlock = blockchain.lastBlock();
        int nonce = Blockchain.findProof(lastBlock);
        lastBlock.setProof(nonce);
        blockchain.newBlock(-1, Blockchain.hash(lastBlock));
    }
}
