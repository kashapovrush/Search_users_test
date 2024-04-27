pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Search users test"
include(":app")
include(":core")
include(":features-mobile")
include(":core:network")
include(":features-mobile:search-users-feature")
include(":features-mobile:common")
include(":features-mobile:palette")
include(":core:utils")
include(":features-mobile:user-repositories-feature")
include(":core:auth")
include(":features-mobile:auth-feature")
include(":features-mobile:auth-user-feature")
