
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
        maven {
            url = uri("/Users/wajidpariyani/Documents/flutter_projects/adkrity-sdk/build/host/outputs/repo")
            // This is relative to the location of the build.gradle file
            // if using a relative path.
        }
        maven {
            url = uri("https://storage.googleapis.com/download.flutter.io")
        }
        maven { url = uri("https://jitpack.io") }
        google()
        mavenCentral()
    }
}

rootProject.name = "My Application"
include(":app")