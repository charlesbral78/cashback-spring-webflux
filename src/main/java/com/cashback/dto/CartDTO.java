package com.cashback.dto;

import lombok.Data;
import java.util.Collection;

@Data
public class CartDTO {

    private Collection<CartEntryDTO> entries;
}
