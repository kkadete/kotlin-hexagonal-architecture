plugins {
    alias(libraries.plugins.kotlin.jvm)
    alias(libraries.plugins.kotlin.plugin.allopen)
    alias(libraries.plugins.io.quarkus)
}

dependencies {
    api(project(":application"))

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

tasks {
    named<Test>("test") {
        useJUnitPlatform()
    }
}
