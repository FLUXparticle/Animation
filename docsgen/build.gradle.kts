plugins { 
    java
}

dependencies {
    val wicket_version: String by extra
    val guice_version: String by extra

    compile(project(path = ":js-test", configuration = "default"))
    compile(project(":deps"))

    compile("com.google.code.gson", "gson", "2.8.4")

    compile("org.apache.wicket", "wicket-core", wicket_version)
    compile("org.apache.wicket", "wicket-guice", wicket_version)

    compile("com.google.inject.extensions", "guice-servlet", guice_version)

    compile("javax.servlet", "javax.servlet-api", "3.1.0")

    runtime("junit:junit:4.12")
}

task("docs", JavaExec::class) {
    main = "de.fluxparticle.animation.docsgen.DocsGen"
    classpath = sourceSets["main"].runtimeClasspath
}
