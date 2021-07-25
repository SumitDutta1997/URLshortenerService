package com.gfg.URLshortener.ServiceLayer;

public interface URLshortenerService {
    String shorten(String longURL);
    String get(String shortURL) throws Exception;
}
