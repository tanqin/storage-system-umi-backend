package com.tanqin.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tanqin.entity.User;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    // 设置过期时间
    private static final long EXPIRE_TIME = 60 * 1000;
    // 秘钥盐值
    private static final String TOKEN_SECRET = "tanqin";

    /**
     * 签名生成
     *
     * @param user 用户对象
     * @return token 值
     */
    public static String sign(User user) {
        try {
            //过期时间
//            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Date expiresAt = DateUtils.addHours(new Date(), 1); // 一小时过期
            // HMAC256 加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username,password信息，生成签名
            return JWT.create()
                    .withIssuer("auth0") // token 发布者
                    .withIssuedAt(new Date()) // 生成签名时间
                    .withExpiresAt(expiresAt) // 签名有效期
//                    .withHeader(header) // 可以不设定，就是使用默认的
                    .withClaim("username", user.getUsername()) // 自定义声明（插入数据）
                    .withClaim("id", user.getId())
                    .sign(algorithm); // 签名
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean verify(String token) {
        /**
         * @desc 验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过！！！");
            System.out.println("username：" + jwt.getClaim("username").asString());
            System.out.println("过期时间：" + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String parseJWT(String token) {
        /**
         * @desc 解密token，返回一个map
         * @params [token]需要校验的串
         **/
        DecodedJWT decodeToken = JWT.decode(token);
        return decodeToken.getClaim("loginName").asString();
    }

    public static boolean isJwtExpired(String token) {
        /**
         * @desc 判断token是否过期
         * @author lj
         */
        try {
            DecodedJWT decodeToken = JWT.decode(token);
            return decodeToken.getExpiresAt().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}

