package io.beaniejoy.dongnecafe.infra.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableRedisRepositories(basePackages = ["io.beaniejoy.dongnecafe.infra.redis.repository"])
class RedisConfig
