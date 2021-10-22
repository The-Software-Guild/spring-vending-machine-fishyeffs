package com.mthree.nick.vending.enums;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ChangeTest {
    @Test
    void Change() {
        Change change = new Change(new BigDecimal("0.01"));
    }
}