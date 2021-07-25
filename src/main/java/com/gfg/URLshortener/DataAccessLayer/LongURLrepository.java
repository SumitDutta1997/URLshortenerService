package com.gfg.URLshortener.DataAccessLayer;

import com.gfg.URLshortener.Entities.LongURL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongURLrepository extends CrudRepository<LongURL, Long> {
}
