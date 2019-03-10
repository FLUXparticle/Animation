plugins {
    id("kotlin-platform-js")
}

repositories {
    mavenCentral()
}

dependencies {
    expectedBy(project(":common"))
    
    compile(kotlin("stdlib-js"))
    
    testCompile(kotlin("test-js"))
}
