package com.kolesnyk.blockchain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private String sender;
    private String recipient;
    private int amount;
}
