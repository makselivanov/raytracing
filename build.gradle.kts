plugins {
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"
val glmVersion: String by project

repositories {
    mavenCentral()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("kotlin.graphics:glm:$glmVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "MainKt"
}