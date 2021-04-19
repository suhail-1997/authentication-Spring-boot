package com.example.authentication.Common;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.example.authentication.Model.User;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utils {

    public String secretkey = "your secret key";


    public JSONObject generateResponse(Object data,String fullError,String message){

        JSONObject response = new JSONObject();
        response.put("message", message);
        response.put("fullError", fullError);
        response.put("data", data);
        return response;
    }

    public HttpHeaders setHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public String encryptPassword(String password) throws NoSuchAlgorithmException,InvalidKeySpecException{
        PBEKeySpec encoder = new PBEKeySpec(password.toCharArray(),secretkey.getBytes(),1000,512);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = skf.generateSecret(encoder).getEncoded();
        return Base64.getMimeEncoder().encodeToString(hash);
    }

    public String generateWebToken(User user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userid", user.getUserId());
        claims.put("name", user.getName());
        claims.put("phone", user.getPhone());
        claims.put("email", user.getEmail());
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+ 2))
        .signWith(SignatureAlgorithm.HS256, secretkey).compact();
    }
    public Claims getAllClaims(String token) {
    	return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
    }
    
    public Boolean verifyToken(String token) {
    	try {
    		Claims claim = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
        	if(!claim.getExpiration().before(new Date())) {
        		return true;
        	}
        	else {
        		return false;
        	}
    	}
    	catch(Exception e) {
    		return false;
    	}
    	
    }
    
}