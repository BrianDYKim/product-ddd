package team.me.hibernate

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.env.ConfigurableEnvironment

/**
 * @author Doyeop Kim
 * @since 2023/11/26
 */
class HibernatePropertiesValidator : EnvironmentPostProcessor {
    companion object {
        const val PRODUCTION_PROFILE_NAME = "prod"
        const val DEVELOPMENT_PROFILE_NAME = "dev"

        private const val SPRING_JPA_HIBERNATE_DDL_AUTO_PROPERTY = "spring.jpa.hibernate.ddl-auto"
        private const val SPRING_JPA_PROPERTIES_HIBERNATE_HBM2DDL_AUTO_PROPERTY =
            "spring.jpa.properties.hibernate.hbm2ddl.auto"

        val VALIDATION_PROPERTY_NAMES =
            listOf(SPRING_JPA_HIBERNATE_DDL_AUTO_PROPERTY, SPRING_JPA_PROPERTIES_HIBERNATE_HBM2DDL_AUTO_PROPERTY)
    }

    override fun postProcessEnvironment(
        environment: ConfigurableEnvironment,
        application: SpringApplication?,
    ) {
        val validationTargetProfiles = listOf(PRODUCTION_PROFILE_NAME, DEVELOPMENT_PROFILE_NAME)

        val isHibernateProtectMustBeActivated =
            validationTargetProfiles
                .map { profile -> isValidationTargetProfile(profile, environment) }
                .contains(true)

        if (isHibernateProtectMustBeActivated) {
            validate(environment)
        }
    }

    private fun isValidationTargetProfile(
        profile: String,
        environment: ConfigurableEnvironment,
    ): Boolean {
        return environment.activeProfiles.toList()
            .contains(profile)
    }

    private fun validate(environment: ConfigurableEnvironment) {
        VALIDATION_PROPERTY_NAMES.forEach { propertyName ->
            val propertyValue = environment.getProperty(propertyName)

            if (checkInvalidPropertyValue(propertyValue)) {
                val errorMessage =
                    "In production profile, $propertyName : $propertyValue must be set to none. Other values are disallowed."

                throw IllegalArgumentException(errorMessage)
            }
        }
    }

    private fun checkInvalidPropertyValue(propertyValue: String?): Boolean {
        return propertyValue != null && "none" != propertyValue
    }
}
