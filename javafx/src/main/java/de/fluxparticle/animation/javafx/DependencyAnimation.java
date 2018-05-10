package de.fluxparticle.animation.javafx;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.fluxparticle.animation.deps.AnimationDepsModule;
import de.fluxparticle.animation.deps.DepsGraph;
import de.fluxparticle.animation.deps.Module;
import de.fluxparticle.animation.example.Algorithm;
import de.fluxparticle.animation.example.DependencyAlgorithm;
import de.fluxparticle.animation.example.DependencyGraph;
import de.fluxparticle.animation.example.JavaTestAnimation;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Created by sreinck on 10.05.18.
 */
public class DependencyAnimation extends JavaTestAnimation {

    @NotNull
    @Override
    protected String getTitle() {
        return "Dependency Animation";
    }

    @NotNull
    @Override
    protected Algorithm getAlgorithm() {
        Injector injector = Guice.createInjector(new AnimationDepsModule());
        DepsGraph depsGraph = injector.getInstance(DepsGraph.class);
        Module[] modules = depsGraph.getModules();

        Map<String, String[]> graphDescription = Stream.of(modules)
                .collect(toMap(Module::getName, Module::getDeps));

        DependencyGraph dependencyGraph = new DependencyGraph(graphDescription);
        return new DependencyAlgorithm(dependencyGraph);
    }

    public static void main(String[] args) {
        launch(args);
    }

}