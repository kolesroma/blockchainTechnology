package com.kolesnyk.blockchain.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kolesnyk.blockchain.entity.Block;
import com.kolesnyk.blockchain.entity.Blockchain;
import com.kolesnyk.blockchain.entity.Transaction;
import spark.Spark;

import java.util.UUID;

import static spark.Spark.*;

public class BlockchainController {
    public static void main(String[] args) {
        Spark.port(4568);

        Blockchain blockchain = new Blockchain();
        Gson gson = new Gson();

        get("/mine", (req, res) -> {
            blockchain.newTransaction("0",
                    UUID.randomUUID().toString().replace("-", ""), 27);
            Block lastBlock = blockchain.lastBlock();
            int proofOfWork = Blockchain.findProof(lastBlock);
            lastBlock.setProof(proofOfWork);
            Block newBlock = blockchain.newBlock(-1, Blockchain.hash(lastBlock));
            String json = gson.toJson(newBlock);
            res.status(200);
            return json;
        });

        post("/transactions/new", (req, res) -> {
            try {
                Transaction tran = gson.fromJson(req.body(), Transaction.class);
                int index = blockchain.newTransaction(tran.getSender(), tran.getRecipient(), tran.getAmount());
                res.status(201);
                return "Transaction will be added to Block " + index;
            } catch (JsonSyntaxException e) {
                res.status(400);
                return "Invalid JSON";
            } catch (Exception e) {
                res.status(400);
                return "Error during parsing";
            }
        });

        get("/chain", (req, res) -> {
            String json = gson.toJson(blockchain);
            res.status(200);
            return json;
        });
    }
}
