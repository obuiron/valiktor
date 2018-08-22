/*
 * Copyright 2018 https://www.valiktor.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.valiktor.springframework.boot.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.valiktor.springframework.config.ValiktorConfiguration
import org.valiktor.springframework.web.controller.ValiktorExceptionHandler
import org.valiktor.springframework.web.controller.ValiktorJacksonExceptionHandler

/**
 * Represents the SpringBoot Auto Configuration for [ValiktorConfiguration]
 *
 * @property properties specifies the properties used in the configuration
 *
 * @author Rodolpho S. Couto
 * @see ValiktorConfiguration
 * @see ValiktorProperties
 * @since 0.1.0
 */
@Configuration
@ConditionalOnClass(ValiktorConfiguration::class)
@EnableConfigurationProperties(ValiktorProperties::class)
class ValiktorAutoConfiguration(private val properties: ValiktorProperties) {

    /**
     * Creates a [ValiktorConfiguration] based on the properties
     *
     * @return the respective [ValiktorConfiguration]
     */
    @Bean
    @ConditionalOnMissingBean
    fun valiktorConfiguration() = ValiktorConfiguration(
        baseBundleName = properties.baseBundleName)

    /**
     * Creates a [ValiktorExceptionHandler]
     *
     * @return the respective [ValiktorExceptionHandler]
     */
    @Bean
    @ConditionalOnMissingBean
    fun valiktorExceptionHandler(valiktorConfiguration: ValiktorConfiguration): ValiktorExceptionHandler =
        ValiktorExceptionHandler(valiktorConfiguration)

    /**
     * Creates a [ValiktorJacksonExceptionHandler]
     *
     * @return the respective [ValiktorJacksonExceptionHandler]
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = ["com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException"])
    fun valiktorJacksonExceptionHandler(valiktorExceptionHandler: ValiktorExceptionHandler): ValiktorJacksonExceptionHandler =
        ValiktorJacksonExceptionHandler(valiktorExceptionHandler)
}