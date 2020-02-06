@file:Suppress("SpellCheckingInspection", "SuspiciousCollectionReassignment")

plugins {
    kotlin("jvm") version "1.3.61"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo1.maven.org/maven2/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.3.3")
    implementation("org.seleniumhq.selenium", "selenium-java", "4.0.0-alpha-4")
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
            freeCompilerArgs += compilerArgs
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs += compilerArgs
        }
    }
    jar
}