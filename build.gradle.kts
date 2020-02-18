@file:Suppress("SpellCheckingInspection")

plugins {
    kotlin("jvm") version "1.3.61"
    `java-library`
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo1.maven.org/maven2/")
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    api("org.seleniumhq.selenium", "selenium-java", "4.0.0-alpha-4")
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.3.3")
}

val compilerArgs = listOf(
    "-XXLanguage:+NewInference",
    "-Xuse-experimental=kotlin.time.ExperimentalTime",
    "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
)

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = compilerArgs
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = compilerArgs
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}