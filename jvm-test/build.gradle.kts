plugins {
    id("kotlin-platform-jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    expectedBy(project(":example"))

    compile(project(":jvm"))

    testCompile(kotlin("test-junit"))
}
