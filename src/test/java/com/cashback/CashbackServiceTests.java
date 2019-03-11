package com.cashback;

import com.cashback.constants.Category;
import com.cashback.document.OrderEntry;
import com.cashback.document.Product;
import com.cashback.service.CashbackService;
import com.cashback.strategy.ClassicCashbackStrategy;
import com.cashback.strategy.MpbCashbackStrategy;
import com.cashback.strategy.PopCashbackStrategy;
import com.cashback.strategy.RockCashbackStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CashbackServiceTests {

    @Autowired
    private CashbackService cashbackService;

    @Autowired
    private PopCashbackStrategy popCashbackStrategy;

    @Autowired
    private MpbCashbackStrategy mpbCashbackStrategy;

    @Autowired
    private ClassicCashbackStrategy classicCashbackStrategy;

    @Autowired
    private RockCashbackStrategy rockCashbackStrategy;

    private Map<DayOfWeek, Double> percentualsForPop;

    private Map<DayOfWeek, Double> percentualsForMpb;

    private Map<DayOfWeek, Double> percentualsForClassic;

    private Map<DayOfWeek, Double> percentualsForRock;

    @Before
    public void setUp() {
        this.percentualsForPop = this.popCashbackStrategy.createPercentuals();
        this.percentualsForMpb = this.mpbCashbackStrategy.createPercentuals();
        this.percentualsForClassic = this.classicCashbackStrategy.createPercentuals();
        this.percentualsForRock = this.rockCashbackStrategy.createPercentuals();
    }

    @Test
    public void shouldCalculateCashbackForPop() {

        OrderEntry orderEntry = this.getOrderEntryFor(Category.POP, Double.valueOf(34), 67);

        Double expectedCashback = this.calculateCashbackForToday(orderEntry, percentualsForPop);

        Double actualCashback = this.cashbackService.calculate(orderEntry);

        Assert.assertEquals(expectedCashback, actualCashback);
    }

    @Test
    public void shouldCalculateCashbackForMpb() {

        OrderEntry orderEntry = this.getOrderEntryFor(Category.MPB, Double.valueOf(29), 8);

        Double expectedCashback = this.calculateCashbackForToday(orderEntry, percentualsForMpb);

        Double actualCashback = this.cashbackService.calculate(orderEntry);

        Assert.assertEquals(expectedCashback, actualCashback);
    }

    @Test
    public void shouldCalculateCashbackForClassic() {

        OrderEntry orderEntry = this.getOrderEntryFor(Category.CLASSIC, Double.valueOf(320), 2);

        Double expectedCashback = this.calculateCashbackForToday(orderEntry, percentualsForClassic);

        Double actualCashback = this.cashbackService.calculate(orderEntry);

        Assert.assertEquals(expectedCashback, actualCashback);
    }

    @Test
    public void shouldCalculateCashbackForRock() {

        OrderEntry orderEntry = this.getOrderEntryFor(Category.ROCK, Double.valueOf(25), 10);

        Double expectedCashback = this.calculateCashbackForToday(orderEntry, percentualsForRock);

        Double actualCashback = this.cashbackService.calculate(orderEntry);

        Assert.assertEquals(expectedCashback, actualCashback);
    }

    private OrderEntry getOrderEntryFor(Category category, Double productPrice, Integer productQuantity) {

        Product product = this.getProduct(category, productPrice);

        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setProduct(product);
        orderEntry.setQuantity(productQuantity);

        return orderEntry;
    }

    private Product getProduct(Category category, Double productPrice) {

        Product product = new Product();
        product.setPrice(productPrice);
        product.setCategory(category);

        return product;
    }

    private Double calculateCashbackForToday(OrderEntry orderEntry, Map<DayOfWeek, Double> percentuals) {

        Double total = orderEntry.getProduct().getPrice() * orderEntry.getQuantity();

        Double percentualToday = percentuals.get(LocalDate.now().getDayOfWeek());

        Double cashback = BigDecimal.valueOf(percentualToday).multiply(BigDecimal.valueOf(total)).divide(BigDecimal.valueOf(100)).doubleValue();

        return cashback;
    }
}
