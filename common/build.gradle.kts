plugins {
    id("kotlin-platform-common")
}

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-common"))
    
    testCompile(kotlin("test-common"))
    testCompile(kotlin("test-annotations-common"))
}
