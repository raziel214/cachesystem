package com.jikkosoft.cachesystem.infraestructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@Bean
    public RedisTemplate<String, CacheEntry> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, CacheEntry> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Configurar el serializador de Jackson para manejar CacheEntry y Java 8 Time API
        Jackson2JsonRedisSerializer<CacheEntry> serializer = new Jackson2JsonRedisSerializer<>(CacheEntry.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // Para manejar LocalDateTime
        serializer.setObjectMapper(objectMapper);

        // Usar StringRedisSerializer para las claves y Jackson2JsonRedisSerializer para CacheEntry
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        return template;
    }

}

