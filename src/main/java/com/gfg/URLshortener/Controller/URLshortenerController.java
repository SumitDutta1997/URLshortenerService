package com.gfg.URLshortener.Controller;

import com.gfg.URLshortener.Model.URLshortenRequest;
import com.gfg.URLshortener.ServiceLayer.URLshortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class URLshortenerController {

    @Autowired
    private URLshortenerService urLshortenerService;

    @PostMapping("/v1/urlshortener/longURL")
    ResponseEntity createShortURL(@RequestBody URLshortenRequest urlShortenRequest) {
        return ResponseEntity.ok(urLshortenerService.shorten(urlShortenRequest.getUrl()));
    }

    @GetMapping("/v1/urlshortener/{shortURL}")
    ResponseEntity getLongURL(@PathVariable("shortURL") String shortURL) {
        try {
            return ResponseEntity.ok(urLshortenerService.get(shortURL));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
