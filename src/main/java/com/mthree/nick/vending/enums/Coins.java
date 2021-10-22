package com.mthree.nick.vending.enums;

import java.math.BigDecimal;

public enum Coins {
    ONE_PENCE(new BigDecimal("0.01"), "pence"),
    TWO_PENCE(new BigDecimal("0.02"), "two pence"),
    FIVE_PENCE(new BigDecimal("0.05"), "five pence"),
    TEN_PENCE(new BigDecimal("0.10"), "ten pence"),
    TWENTY_PENCE(new BigDecimal("0.20"), "twenty pence"),
    FIFTY_PENCE(new BigDecimal("0.50"), "fifty pence"),
    POUND(new BigDecimal("1.00"), "one pound"),
    TWO_POUND(new BigDecimal("2.00"), "two pound");

    private BigDecimal numVal;
    private String name;

    Coins(BigDecimal val, String name) {
        this.numVal = val;
        this.name = name;
    }

    public BigDecimal getNumVal() {
        return this.numVal;
    }

    public String getName() {
        return name;
    }
}
