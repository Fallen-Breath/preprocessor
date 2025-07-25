/* Copyright (C) 2019 Jonas Herzig <me@johni0702.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

plugins {
    `kotlin-dsl`
    `maven-publish`
    groovy
}

group = "com.github.replaymod"
version = "SNAPSHOT"

val kotestVersion: String by project.extra

gradlePlugin {
    plugins {
        register("preprocess") {
            id = "com.replaymod.preprocess"
            implementationClass = "com.replaymod.gradle.preprocess.PreprocessPlugin"
        }
        register("preprocess-root") {
            id = "com.replaymod.preprocess-root"
            implementationClass = "com.replaymod.gradle.preprocess.RootPreprocessPlugin"
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://maven.fabricmc.net")
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.github.Fallen-Breath:remap:2ae23d72bb")
    implementation("net.fabricmc:mapping-io:0.6.1")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
}
