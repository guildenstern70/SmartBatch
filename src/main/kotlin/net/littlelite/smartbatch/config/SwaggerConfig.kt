package net.littlelite.smartbatch.config

import org.jetbrains.annotations.Contract
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.hateoas.client.JsonPathLinkDiscoverer
import org.springframework.hateoas.client.LinkDiscoverers
import org.springframework.hateoas.mediatype.hal.HalLinkDiscoverer
import org.springframework.hateoas.server.LinkRelationProvider
import org.springframework.hateoas.server.core.DelegatingLinkRelationProvider
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider
import org.springframework.plugin.core.SimplePluginRegistry
import org.springframework.plugin.core.support.PluginRegistryFactoryBean
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

/**
 * Swagger is a simple yet powerful representation of your RESTful API.
 *
 * @see [swagger doc](http://swagger.io)
 */
@Suppress("DEPRECATION")
@Configuration
@EnableSwagger2
class SwaggerConfig
{
    @Bean
    fun discoverers(): LinkDiscoverers
    {
        val plugins: MutableList<JsonPathLinkDiscoverer> = ArrayList<JsonPathLinkDiscoverer>()
        plugins.add(HalLinkDiscoverer())
        return LinkDiscoverers(SimplePluginRegistry.create(plugins))
    }

    @Bean
    fun provider(): LinkRelationProvider
    {
        return EvoInflectorLinkRelationProvider()
    }

    @Bean
    @Primary
    fun fixedPluginProvider(): PluginRegistryFactoryBean<LinkRelationProvider, LinkRelationProvider.LookupContext>
    {
        val factory: PluginRegistryFactoryBean<LinkRelationProvider, LinkRelationProvider.LookupContext> = PluginRegistryFactoryBean()
        factory.setType(LinkRelationProvider::class.java)
        val classes: Array<Class<*>?> = arrayOfNulls(1)
        classes[0] = DelegatingLinkRelationProvider::class.java
        factory.setExclusions(classes)
        return factory
    }

    /**
     * @return Docket
     * @see [swagger doc](http://swagger.io)
     */
    @Suppress("UNCHECKED_CAST")
    @Bean
    fun api(): Docket
    {
        val BASE_PACKAGE = "net.littlelite.smartbatch.controller.rest"
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo("1.0"))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, customizedResponseMessages as MutableList<ResponseMessage>?)
                .securitySchemes(listOf(apiKey()))
                .securityContexts(listOf(securityContext()))
    }

    @Contract("_ -> new")
    private fun apiInfo(version: String): ApiInfo
    {
        return ApiInfo(
                "SmartREST",
                "OpenAPI REST Project Template",
                version,
                "Terms of service",
                "Alessio Saltarin <alessiosaltarin@gmail.com",
                "GIT Repository",
                "https://github.com/guildenstern70/SmartREST")
    }

    @Contract(" -> new")
    private fun apiKey(): ApiKey
    {
        return ApiKey("apiKey", "access_token", "QUERY")
    }

    private fun securityContext(): SecurityContext
    {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build()
    }

    @get:Contract(pure = true)
    private val customizedResponseMessages: List<Any>
        get()
        = ArrayList()

    private fun defaultAuth(): List<SecurityReference>
    {
        val authorizationScope = AuthorizationScope(
                "global", "accessEverything")
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf<SecurityReference>(SecurityReference("apiKey",
                authorizationScopes))
    }
}
