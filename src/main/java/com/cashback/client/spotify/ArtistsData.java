package com.cashback.client.spotify;

import lombok.Data;

import java.util.List;

@Data
public class ArtistsData {

    private List<ArtistData> items;
    private Integer total;
}
