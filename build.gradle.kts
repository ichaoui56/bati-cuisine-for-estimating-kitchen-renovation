plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "src.main.java.org.BatiCuisine"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.postgresql:postgresql:42.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    manifest {
        attributes(
            "Main-Class" to "org.BatiCuisine.Main"
        )
    }
}
