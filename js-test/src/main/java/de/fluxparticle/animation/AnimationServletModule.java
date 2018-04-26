package de.fluxparticle.animation;

import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import org.apache.wicket.guice.GuiceWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import java.util.HashMap;

/**
 * Created by sreinck on 26.04.18.
 */
public class AnimationServletModule extends ServletModule {

    private final boolean deployment;

    public AnimationServletModule(boolean deployment) {
        this.deployment = deployment;
    }

    @Override
    protected void configureServlets() {
        filter("/*").through(WicketFilter.class, new HashMap<String, String>() {{
            put("injectorContextAttribute", Injector.class.getName());
            if (deployment) {
                put("configuration", "deployment");
            }
            put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
            put(WicketFilter.APP_FACT_PARAM, GuiceWebApplicationFactory.class.getName());
        }});
        bind(WicketFilter.class).in(Singleton.class);

        bind(WebApplication.class).to(AnimationApplication.class);
    }

}
