package com.cashback.document;

import lombok.Data;

@Data
public class OrderEntry {

    private Product product;
    private Integer quantity;
    private Double cashback;
}
