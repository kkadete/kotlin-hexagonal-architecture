plugins {
    alias(libraries.plugins.kotlin.jvm)
    alias(libraries.plugins.kotlin.plugin.allopen)
    alias(libraries.plugins.io.quarkus)
}

dependencies {
    api(project(":application"))
    api(project(":inmemory-store-adapter"))

    api(enforcedPlatform("io.quarkus.platform:quarkus-bom:3.15.1"))
    api("io.quarkus:quarkus-kotlin")
    api("io.quarkus:quarkus-rest")
    api("io.quarkus:quarkus-arc")
    api("io.quarkus:quarkus-rest-jackson")
    api("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation(libraries.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    sourceSets {
        main {
            kotlin {
                srcDir("src/main/kotlinX")
            }
        }
    }
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks {
    named<Test>("test") {
        useJUnitPlatform()
    }
}
