package de.fluxparticle.animation.servlet;

import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptPackageResource;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Created by sreinck on 26.04.18.
 */
class KotlinJsReference extends ResourceReference {

    public KotlinJsReference() {
        super("kotlin.js");
    }

    @Override
    public IResource getResource() {
        return new JavaScriptPackageResource(getClass(), "/kotlin.js", null, null, null);
    }

}
