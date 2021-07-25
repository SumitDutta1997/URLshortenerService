package com.gfg.URLshortener.Entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String domain="localhost:8080";
    @Column(nullable = false)
    private String protocol="http";
    private LocalDateTime createdAt;
    @OneToOne(cascade = CascadeType.ALL)
    private LongURL longURL;
}
