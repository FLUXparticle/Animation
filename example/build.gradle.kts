plugins {
    id("kotlin-platform-common")
}

repositories {
    mavenCentral()
}

dependencies {
    compile(project(":common"))
    
    compile(kotlin("stdlib-common"))
    
    testCompile(kotlin("test-common"))
    testCompile(kotlin("test-annotations-common"))
}
