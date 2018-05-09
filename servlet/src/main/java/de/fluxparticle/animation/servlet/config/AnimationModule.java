package de.fluxparticle.animation.servlet.config;

import com.google.inject.AbstractModule;
import de.fluxparticle.animation.servlet.graph.DefaultGraph;
import de.fluxparticle.animation.servlet.graph.Graph;

/**
 * Created by sreinck on 09.05.18.
 */
public class AnimationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Graph.class).to(DefaultGraph.class);
    }

}
