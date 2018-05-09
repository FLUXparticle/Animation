package de.fluxparticle.animation.servlet

import com.google.inject.servlet.GuiceFilter
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import java.net.URLClassLoader
import java.util.*
import javax.servlet.DispatcherType
import javax.servlet.DispatcherType.REQUEST

/**
 * Created by sreinck on 26.04.18.
 */
fun main(args: Array<String>) {
    val classLoader = ClassLoader.getSystemClassLoader() as URLClassLoader
    classLoader.urLs
            .map { it.toString() }
            .filter { it.contains("/Animation/") }
            .forEach { url ->
        println(url)
    }

    val context = ServletContextHandler()
    context.addEventListener(AnimationServletTestConfig())
    context.addFilter(GuiceFilter::class.java, "/*", EnumSet.of<DispatcherType>(REQUEST))

    val server = Server(8080)
    server.handler = context
    server.start()
    server.join()
}
