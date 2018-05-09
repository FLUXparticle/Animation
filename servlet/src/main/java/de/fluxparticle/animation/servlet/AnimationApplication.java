package de.fluxparticle.animation.servlet;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.PackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Created by sreinck on 26.04.18.
 */
public class AnimationApplication extends WebApplication {

    @Override
    protected void init() {
        PackageResourceGuard packageResourceGuard = (PackageResourceGuard) getResourceSettings().getPackageResourceGuard();
        packageResourceGuard.setAllowAccessToRootResources(true);

        mountResource("/js-test.js", new AnimationJsTestReference());
        mountResource("/js.js", new AnimationJsReference());
        mountResource("/kotlin.js", new KotlinJsReference());
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return AnimationPage.class;
    }

}
