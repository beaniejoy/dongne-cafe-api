object Plugins {
    const val JAVA = "java"

    object Spring {
        const val BOOT = "org.springframework.boot"
        const val DEPENDENCY_MANAGEMENT = "io.spring.dependency-management"
    }

    object Kotlin {
        const val KOTLIN = "kotlin"
        const val KOTLIN_SPRING = "kotlin-spring"
        const val KOTLIN_JPA = "kotlin-jpa"
        const val KOTLIN_KAPT = "kotlin-kapt"

        const val JVM = "jvm"
        const val PLUGIN_SPRING = "plugin.spring"
        const val PLUGIN_JPA = "plugin.jpa"
        const val KAPT = "kapt" // Kotlin Annotation Processing Tool
    }

    const val KTLINT = "org.jlleitschuh.gradle.ktlint"
}