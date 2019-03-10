plugins {
    id("kotlin-platform-js")
}

repositories {
    mavenCentral()
}

dependencies {
    expectedBy(project(":example"))
    
    compile(project(path = ":js", configuration = "default"))
    
    testCompile(kotlin("test-js"))
}
