include(":pet-profile-utils")
include(":pet-profile-core")

include(":pet-profile-android")

rootProject.name = "PetProfile"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
