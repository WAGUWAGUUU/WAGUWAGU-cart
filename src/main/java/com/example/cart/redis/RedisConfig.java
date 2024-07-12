package com.example.cart.redis;

import com.example.cart.dto.CartDTO;
import io.lettuce.core.RedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.UUID;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, CartDTO> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, CartDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CartDTO.class));

        return template;
    }
}
