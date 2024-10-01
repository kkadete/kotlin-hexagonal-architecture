plugins {
    alias(libraries.plugins.kotlin.jvm)
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":framework"))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
