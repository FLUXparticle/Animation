package de.fluxparticle.animation.servlet;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.EnumSet;

import static javax.servlet.DispatcherType.REQUEST;

/**
 * Created by sreinck on 26.04.18.
 */
public class AnimationTestServer {

    public static void main(String[] args) throws Exception {
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        for (URL url : classLoader.getURLs()) {
            System.out.println(url);
        }

        ServletContextHandler context = new ServletContextHandler();
        context.addEventListener(new AnimationServletTestConfig());
        context.addFilter(GuiceFilter.class, "/*", EnumSet.of(REQUEST));

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        server.join();
    }

}
