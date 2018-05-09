package de.fluxparticle.animation.servlet;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import de.fluxparticle.animation.servlet.config.AnimationModule;
import de.fluxparticle.animation.servlet.config.AnimationServletModule;

import static com.google.inject.Guice.createInjector;

/**
 * Created by sreinck on 28.04.18.
 */
public class AnimationServletTestConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return createInjector(new AnimationServletModule(false), new AnimationModule());
    }

}
