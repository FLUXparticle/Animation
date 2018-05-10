package de.fluxparticle.animation.docsgen.servlet;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import de.fluxparticle.animation.deps.AnimationDepsModule;
import de.fluxparticle.animation.docsgen.servlet.config.AnimationServletModule;

import static com.google.inject.Guice.createInjector;

/**
 * Created by sreinck on 28.04.18.
 */
public class AnimationServletTestConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return createInjector(new AnimationServletModule(false), new AnimationDepsModule());
    }

}
