buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-mysql:${Version.FlywayDB.FLYWAY_CORE}")
    }
}

plugins {
    id(Plugins.FlywayDB.FLYWAY).version(Version.FlywayDB.FLYWAY_CORE)
}

dependencies {
    implementation("org.flywaydb:flyway-core:${Version.FlywayDB.FLYWAY_CORE}") // flyway
}

flyway {
    baselineDescription = "Start Flyway Migration!"
    baselineOnMigrate = true
    baselineVersion = "000"
    locations = arrayOf("filesystem:./migration", "filesystem:./seed")
    configFiles = arrayOf("conf/flyway.conf")
    cleanDisabled = false   // activate flywayClean
    ignoreMigrationPatterns = arrayOf("*:pending")  // ignore validating pending(대기) state
}