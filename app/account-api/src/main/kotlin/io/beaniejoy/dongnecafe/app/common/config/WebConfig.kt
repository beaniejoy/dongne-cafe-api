package io.beaniejoy.dongnecafe.app.common.config

import io.beaniejoy.dongnecafe.app.common.interceptor.RefreshTokenCookieResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    @Autowired
    lateinit var refreshTokenCookieResolver: RefreshTokenCookieResolver
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(refreshTokenCookieResolver)
    }
}
