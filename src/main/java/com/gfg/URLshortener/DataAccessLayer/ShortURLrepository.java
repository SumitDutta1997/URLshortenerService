package com.gfg.URLshortener.DataAccessLayer;

import com.gfg.URLshortener.Entities.ShortURL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortURLrepository extends CrudRepository<ShortURL, Short> {
    
    Optional<ShortURL> findById(Long id);
}
