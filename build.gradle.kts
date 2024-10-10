plugins {
    alias(libs.plugins.ktfmt)
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}

allprojects {
    group = "com.castlelecs.petprofile"

    configureLinter()
}

fun Project.configureLinter() {
    apply(plugin = "com.ncorti.ktfmt.gradle")

    ktfmt {
        kotlinLangStyle()

        maxWidth.set(80)
        manageTrailingCommas.set(true)
    }
}
