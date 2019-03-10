plugins {
    id("kotlin-platform-jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    expectedBy(project(":common"))
    
    compile(kotlin("stdlib-jdk8"))
    compile("org.fxmisc.easybind", "easybind", "1.0.3")
    
    testCompile(kotlin("test-junit"))
}
