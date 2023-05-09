package com.niepan;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

//@SpringBootTest
class TliasApplicationTests {

    @Test
    void contextLoads() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username","Tom");
        claims.put("password","123");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"zhanzexi") // 签名算法
                .setClaims(claims)// 自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置 1h 有效期
                .compact();
        System.out.println(jwt);
    }
    @Test
    public void testParseJwt(){
        final Claims claims = Jwts.parser()
                .setSigningKey("zhanzexi")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMyIsImV4cCI6MTY4MzYzNzA1MCwidXNlcm5hbWUiOiJUb20ifQ.OsiQsWjy5cWaWyQR4PpuktR9ODzLWS_p6v_o-H1wGdw")
                .getBody();
        System.out.println(claims);
    }

}
