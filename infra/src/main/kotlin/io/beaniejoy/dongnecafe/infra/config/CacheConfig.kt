package io.beaniejoy.dongnecafe.infra.config

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.cache.annotation.CachingConfigurer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.CacheErrorHandler
import org.springframework.cache.interceptor.LoggingCacheErrorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration
import java.time.temporal.ChronoUnit

@Configuration
@EnableCaching
class CacheConfig : CachingConfigurer {

    // TODO: 우선 테스트 용도로 TTL 짧게 설정 (시간 단위에 대해서 타입화 필요)
    @Bean
    fun redisCacheManager(redisConnectionFactory: LettuceConnectionFactory): RedisCacheManager {
        val redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.of(5L, ChronoUnit.MINUTES))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair
                    .fromSerializer(
                        GenericJackson2JsonRedisSerializer(createRedisObjectMapper())
                    )
            )
            .disableCachingNullValues() // Disable caching null values. (null value 대해서 caching X)

        return RedisCacheManager
            .builder(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .build()
    }

    /**
     * Redis 전용 serialization/deserialization 용도의 ObjectMapper 생성
     * [JsonTypeInfo.Id.CLASS] > deserialize 대상 class package 변경시 에러 발생(TODO 이부분 고려 필요)
     * [JsonTypeInfo.As.PROPERTY] > JSON field 내부에 @class 명시
     * class package 변경시 발생하는 에러에 대한 handling 필요
     * @return ObjectMapper
     */
    private fun createRedisObjectMapper(): ObjectMapper {
        val typeValidator = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType(Any::class.java)
            .build()

        return jacksonObjectMapper().activateDefaultTyping(
            typeValidator,
            ObjectMapper.DefaultTyping.EVERYTHING,
            JsonTypeInfo.As.PROPERTY
        )
    }

    override fun errorHandler(): CacheErrorHandler {
        // for not throwing exception to client
        return LoggingCacheErrorHandler()
    }
}
