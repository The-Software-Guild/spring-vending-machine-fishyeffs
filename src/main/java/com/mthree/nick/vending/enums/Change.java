package com.mthree.nick.vending.enums;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Change {
    private final Coins[] coins = new Coins[8];

    public Change(BigDecimal payment) {
        fillCoinTree();
        coinChange(0, payment, coins, "");
    }

    private void fillCoinTree() {
        coins[7] = Coins.ONE_PENCE;
        coins[6] = Coins.TWO_PENCE;
        coins[5] = Coins.FIVE_PENCE;
        coins[4] = Coins.TEN_PENCE;
        coins[3] = Coins.TWENTY_PENCE;
        coins[2] = Coins.FIFTY_PENCE;
        coins[1] = Coins.POUND;
        coins[0] = Coins.TWO_POUND;
    }

    private BigDecimal coinChange(int curCoin, BigDecimal remaining,
                                 Coins[] coins, String paid) {
        if (remaining.compareTo(new BigDecimal("0.00")) == 0) {
            System.out.println(paid);
            return new BigDecimal("1");
        }
        if (remaining.compareTo(new BigDecimal("0.00")) < 0) {
            return new BigDecimal("0");
        }

        BigDecimal res = new BigDecimal(BigInteger.ZERO);
        for (int i = curCoin; i < coins.length; i++) {
            res = res.add(coinChange(i, remaining.subtract(coins[i].getNumVal()),coins,
                    paid + coins[i].getName() +", "));

        }
        return res;
    }
}
