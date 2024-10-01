pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.PREFER_PROJECT

    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    versionCatalogs {
        create("libraries") {
            from(layout.rootDirectory.files("gradle/catalogs/libraries.versions.toml"))
        }
    }
}

rootProject.name = "kotlin-hexagonal-architecture"

fun Settings.includeModule(group: String, name: String) {
    require(name.isNotBlank())

    include(name)

    project(":${name}").projectDir = rootDir.resolve("$group/$name")
}

includeModule("modules", "application")
includeModule("modules", "domain")
includeModule("modules", "framework")
includeModule("adapters", "quarkus-rest")
includeModule("adapters", "inmemory-store-adapter")
includeModule("adapters", "database-store-adapter")
