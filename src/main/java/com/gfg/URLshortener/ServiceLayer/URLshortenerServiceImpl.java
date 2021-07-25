package com.gfg.URLshortener.ServiceLayer;

import com.gfg.URLshortener.DataAccessLayer.LongURLrepository;
import com.gfg.URLshortener.DataAccessLayer.ShortURLrepository;
import com.gfg.URLshortener.Entities.LongURL;
import com.gfg.URLshortener.Entities.ShortURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class URLshortenerServiceImpl implements URLshortenerService {

    @Autowired
    private ShortURLrepository shortURLrepository;
    @Autowired
    private LongURLrepository longURLrepository;

    @Override
    public String shorten(String longUrlString) {
        ShortURL shortURL = ShortURL.builder()
                .domain("localhost:8080")
                .createdAt(LocalDateTime.now())
                .protocol("http")
                .build();
        LongURL longURL = LongURL.builder()
                .url(longUrlString)
                .createdAt(LocalDateTime.now())
                .shortURL(shortURL)
                .build();

        shortURL.setLongURL(longURL);
        longURL = longURLrepository.save(longURL);
        String path = getShortenedPath(longURL.getId());
        shortURL = longURL.getShortURL();
        shortURLrepository.save(shortURL);

        // Shortens the URL
        URI uri = URI.create(shortURL.getProtocol()+"://"+
                shortURL.getDomain()+"/"+ getShortenedPath(longURL.getShortURL().getId()));

        return uri.toString();
    }

    private String getShortenedPath(Long id) {
        String path= Base64.getEncoder().encodeToString(String.valueOf(id).getBytes());
        return path;
    }

    @Override
    public String get(String shortURLrequest) throws Exception {

        Long id = 0l;
        try {
            id = decodePath(shortURLrequest);
        } catch(Exception e) {
            throw new Exception("Invalid short URL");
        }

        ShortURL shortURL = shortURLrepository.findById(id)
                .orElseThrow(()-> new Exception("Short URL does not exists."));

        return shortURL.getLongURL().getUrl();
    }

    private Long decodePath(String shortURL) {
        byte[] bytes = Base64.getDecoder().decode(shortURL);
        String str = new String(bytes, StandardCharsets.UTF_8);
        return Long.parseLong(str);
    }
}
