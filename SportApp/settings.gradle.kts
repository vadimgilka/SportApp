pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{setUrl("https://oss.sonatype.org/content/repositories/snapshots/")}
    }
}

rootProject.name = "SportApp"
include(":app")
 