package de.fluxparticle.animation.docsgen;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import de.fluxparticle.animation.deps.AnimationDepsModule;
import de.fluxparticle.animation.docsgen.servlet.AnimationPage;
import de.fluxparticle.animation.docsgen.servlet.config.AnimationServletModule;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by sreinck on 09.05.18.
 */
public class DocsGen {

    private final Path root;

    @Inject
    public DocsGen(@Named("root") Path root) {
        this.root = root;
    }

    private void run() throws IOException {
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

        Stream.of(classLoader.getURLs())
                .map(URL::toString)
                .filter(str -> str.contains("/Animation/"))
                .forEach(System.out::println);

        PageProvider pageProvider = new PageProvider(AnimationPage.class);
        String html = ComponentRenderer.renderPage(pageProvider).toString();

        Pattern pattern = Pattern.compile("([^/]*)-ver-\\d+.js");

        Path docs = root.resolve("docs");
        if (!Files.exists(docs)) {
            Files.createDirectory(docs);
        }

        StringBuffer sb = new StringBuffer();
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String group = matcher.group(1);
            String script = group + ".js";
            matcher.appendReplacement(sb, script);
            try (InputStream inputStream = classLoader.getResourceAsStream(script)) {
                if (inputStream != null) {
                    System.out.println("Copy " + script + " ...");
                    Files.copy(inputStream, docs.resolve(script), REPLACE_EXISTING);
                } else {
                    System.out.println(script + " not found");
                }
            }
        }
        matcher.appendTail(sb);

        System.out.println("Writing index.html ...");
        Path indexHtml = docs.resolve("index.html");
        Files.write(indexHtml, sb.toString().getBytes());
    }

    public static void main(String[] args) throws IOException {
        Injector injector = Guice.createInjector(new AnimationServletModule(true), new AnimationDepsModule());
        WebApplication webApplication = injector.getInstance(WebApplication.class);
        webApplication.getComponentInstantiationListeners().add(new GuiceComponentInjector(webApplication, injector));
        new WicketTester(webApplication);

        injector.getInstance(DocsGen.class).run();
    }

}
