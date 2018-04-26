package de.fluxparticle.animation;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import static com.google.inject.Guice.createInjector;

/**
 * Created by sreinck on 26.04.18.
 */
public class AnimationServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return createInjector(new AnimationServletModule(false));
    }

}
