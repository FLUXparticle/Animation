repositories {
    mavenCentral()
}

plugins {
    `java-library`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    val guice_version: String by extra
    compile("pl.touk", "throwing-function", "1.3")
    compile("com.google.inject", "guice", guice_version)
    runtime("org.slf4j", "slf4j-simple", "1.7.21")
}
