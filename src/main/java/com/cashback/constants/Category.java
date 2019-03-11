package com.cashback.constants;

import com.cashback.strategy.*;
import org.springframework.context.ApplicationContext;

public enum Category {

    POP {
        @Override
        public CashbackStrategy cashbackStrategy(ApplicationContext context) {
            return context.getBean(PopCashbackStrategy.class);
        }
    },
    MPB {
        @Override
        public CashbackStrategy cashbackStrategy(ApplicationContext context) {
            return context.getBean(MpbCashbackStrategy.class);
        }
    },
    CLASSIC {
        @Override
        public CashbackStrategy cashbackStrategy(ApplicationContext context) {
            return context.getBean(ClassicCashbackStrategy.class);
        }
    },
    ROCK {
        @Override
        public CashbackStrategy cashbackStrategy(ApplicationContext context) {
            return context.getBean(RockCashbackStrategy.class);
        }
    };

            /*
    private BiConsumer<ApplicationContext, OrderEntry> updateCashbackFunction;

    Category(BiConsumer<ApplicationContext, OrderEntry> updateCashbackFunction) {
        this.updateCashbackFunction = updateCashbackFunction;
    }

    public void updateCashback(ApplicationContext context, OrderEntry orderEntry) {
        updateCashbackFunction.accept(context, orderEntry);
    }
    */

    public abstract CashbackStrategy cashbackStrategy(ApplicationContext context);
}
