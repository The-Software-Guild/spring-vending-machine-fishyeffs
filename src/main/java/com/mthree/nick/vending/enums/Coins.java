package com.mthree.nick.vending.enums;

import java.math.BigDecimal;

public enum Coins {
    ONE_PENCE(new BigDecimal("0.01")),
    TWO_PENCE(new BigDecimal("0.02")),
    FIVE_PENCE(new BigDecimal("0.05")),
    TEN_PENCE(new BigDecimal("0.10")),
    TWENTY_PENCE(new BigDecimal("0.20")),
    FIFTY_PENCE(new BigDecimal("0.50")),
    POUND(new BigDecimal("1.00")),
    TWO_POUND(new BigDecimal("2.00"));

    private BigDecimal numVal;

    Coins(BigDecimal val) {
        this.numVal = val;
    }

    public BigDecimal getNumVal() {
        return this.numVal;
    }
}
