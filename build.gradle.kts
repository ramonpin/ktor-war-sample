val kotlin_version: String by project
val logback_version: String by project

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.gretty") version "4.0.3"
    id("io.ktor.plugin")
    id("war")
}

gretty {
    servletContainer = "jetty11"
    contextPath = "/"
    logbackConfigFile = "src/main/resources/logback.xml"
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    testImplementation("org.jetbrains.kotlin:$kotlin_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-servlet-jakarta")
    implementation("io.ktor:ktor-server-status-pages")
    testImplementation("io.ktor:ktor-server-test-host")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    jvmTargetValidationMode.set(org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode.IGNORE)
}

afterEvaluate {
    tasks.getByName("run") {
        dependsOn("appRun")
    }
}
