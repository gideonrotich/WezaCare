plugins {
    id("com.android.application") version ("7.4.1") apply false
    id("com.android.library") version ("7.4.1") apply false
    id("org.jetbrains.kotlin.android") version ("1.7.20") apply false
    id("org.jetbrains.kotlin.jvm") version ("1.7.20") apply false
    id("com.google.dagger.hilt.android") version ("2.44") apply false
    id("org.jlleitschuh.gradle.ktlint") version ("11.0.0")
    id("io.gitlab.arturbosch.detekt") version ("1.22.0")
    id("com.diffplug.spotless") version ("6.14.1")
    id("org.jetbrains.dokka") version ("1.7.20")
}

allprojects {

    apply(plugin = "org.jetbrains.dokka")

    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    ktlint {
        android.set(true)
        verbose.set(true)
        ignoreFailures.set(false)
        disabledRules.set(setOf("final-newline", "no-wildcard-imports", "max-line-length"))
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
        }
        filter {
            exclude { element -> element.file.path.contains("generated/") }
        }
    }
}

buildscript {
    val jacocoVersion by extra("0.2")

    dependencies {
        classpath("com.hiya:jacoco-android:$jacocoVersion")
    }
}

subprojects {

    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        config = files("${project.rootDir}/detekt.yml")
        parallel = true
    }

    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            licenseHeaderFile(
                rootProject.file("${project.rootDir}/spotless/copyright.kt"),
                "^(package|object|import|interface)"
            )
        }
    }
}
