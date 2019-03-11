package com.cashback.dto;

import com.cashback.document.Product;
import lombok.Data;

@Data
public class CartEntryDTO {

    private Product product;
    private Integer quantity;
}
