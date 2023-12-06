package com.example.Diplomna.services.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTServiceImpl {
    private String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSiginKey(){
        byte[] key= Decoders.BASE64.decode("413F442847284B625065536856605978337336763979244226452948404D6351");
        return Keys.hmacShaKeyFor(key);
    }
}
