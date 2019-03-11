package com.cashback.client.spotify;

import feign.Param;
import feign.RequestLine;

public interface SpotifyApiClient {

    @RequestLine("GET /search?q={query}&type=artist&limit={limit}")
    ResponseData getArtists(@Param("query") String query, @Param("limit") Integer limit);
}
