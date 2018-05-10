package de.fluxparticle.animation.docsgen.servlet.config;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import de.fluxparticle.animation.deps.AnimationDepsModule;

import static com.google.inject.Guice.createInjector;

/**
 * Created by sreinck on 26.04.18.
 */
public class AnimationServletConfig extends GuiceServletContextListener {

    @Override
    public Injector getInjector() {
        return createInjector(new AnimationServletModule(true), new AnimationDepsModule());
    }

}
