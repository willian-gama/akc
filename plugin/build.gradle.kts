plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    jacoco // Jacoco plugin: https://docs.gradle.org/current/userguide/jacoco_plugin.html#sec:jacoco_getting_started
}

kotlin {
    jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
}

dependencies {
    implementation("com.android.tools.build:gradle:8.3.1")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:12.1.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.6")
}

gradlePlugin {
    plugins {
        create("linting") {
            id = "com.willian.gama.plugin.LintingPlugin"
            implementationClass = "com.willian.gama.plugin.LintingPlugin"
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            // https://docs.gradle.org/current/userguide/publishing_maven.html#sec:identity_values_in_the_generated_pom
            create<MavenPublication>("release") {
                from(components["java"])
                groupId = "com.willian.gama"
                artifactId = "plugin"
                version = "1.0.0"
//                artifact("/Users/android_dev_engineer/Projects/Mobile/KtComposeApp/plugin/src/main/java/com/willian/gama/gradle/plugin/LintingPlugin.kt")
            }
        }
    }
}