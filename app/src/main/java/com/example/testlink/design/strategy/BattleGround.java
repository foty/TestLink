package com.example.testlink.design.strategy;

/**
 * Created by lxx on 2019/10/25.
 * Use by
 */

public class BattleGround {

    private AbsStrategy strategy;

    public AbsStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(AbsStrategy strategy) {
        this.strategy = strategy;
    }

    public void startFight() {
        strategy.fight();
    }
}
