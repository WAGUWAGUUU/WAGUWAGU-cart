package com.example.cart.redis;

import com.example.cart.dto.CartDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Long, CartDTO> redisTemplate(RedisConnectionFactory redisConnectionFactory) {


            RedisTemplate<Long, CartDTO> template = new RedisTemplate<>();
            template.setConnectionFactory(redisConnectionFactory);
            template.setKeySerializer(new Jackson2JsonRedisSerializer<>(Long.class));  // Use Jackson2JsonRedisSerializer for Long keys
            template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CartDTO.class));
            template.setHashKeySerializer(new Jackson2JsonRedisSerializer<>(Long.class));  // Use Jackson2JsonRedisSerializer for Long hash keys
            template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(CartDTO.class));
            return template;



    }
}
