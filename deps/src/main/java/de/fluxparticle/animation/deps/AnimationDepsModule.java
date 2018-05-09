package de.fluxparticle.animation.deps;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import de.fluxparticle.animation.servlet.graph.Graph;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by sreinck on 09.05.18.
 */
public class AnimationDepsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Graph.class).to(DepsGraph.class);
    }

    @Provides @Named("root")
    Path root() {
        Path root = Paths.get("").toAbsolutePath();
        while (!Files.exists(root.resolve("settings.gradle"))) {
            root = root.getParent();
        }
        return root;
    }

}
