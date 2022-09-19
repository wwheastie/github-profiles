package io.branch.github.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;

@Configuration
public class RedisConfig {
	private static final String NAMESPACE = "github.";
	private static final Duration TTL = Duration.ofMinutes(5);

	@Bean
	public RedisStandaloneConfiguration redisStandaloneConfiguration() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("redis");
		redisStandaloneConfiguration.setPort(6379);
		return redisStandaloneConfiguration;
	}

	@Bean
	public LettuceClientConfiguration getLettuceClientConfiguration() {
		SocketOptions socketOptions = SocketOptions.builder()
				.connectTimeout(Duration.ofMillis(50))
				.build();

		ClientOptions clientOptions = ClientOptions.builder()
				.socketOptions(socketOptions)
				.build();

		return LettuceClientConfiguration.builder()
				.clientOptions(clientOptions)
				.commandTimeout(Duration.ofMillis(50))
				.build();
	}

	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory(final RedisStandaloneConfiguration redisStandaloneConfiguration,
			final LettuceClientConfiguration lettuceClientConfiguration) {
		return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
	}

	@Bean
	public RedisCacheConfiguration getDefaultRedisCacheConfiguration() {
		ObjectMapper objectMapper = createObjectMapper();

		return RedisCacheConfiguration.defaultCacheConfig()
				.prefixCacheNameWith(NAMESPACE)
				.disableCachingNullValues()
				.entryTtl(TTL)
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper)));
	}

	@Bean
	public CacheManager getRedisCacheManager(final RedisConnectionFactory redisConnectionFactory,
			final RedisCacheConfiguration redisCacheConfiguration) {
		return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
				.cacheDefaults(redisCacheConfiguration)
				.transactionAware()
				.build();
	}

	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false)
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL,
				JsonTypeInfo.As.PROPERTY);
		return objectMapper;
	}
}
