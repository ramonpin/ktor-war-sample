pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> {
                    val kotlin_version: String by settings
                    useVersion(kotlin_version)
                }

                "io.ktor.plugin" -> {
                    val ktor_version: String by settings
                    useVersion(ktor_version)
                }
            }
        }
    }
}
