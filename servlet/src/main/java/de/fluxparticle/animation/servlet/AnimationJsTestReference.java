package de.fluxparticle.animation.servlet;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptPackageResource;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.List;

/**
 * Created by sreinck on 26.04.18.
 */
class AnimationJsTestReference extends ResourceReference {

    public AnimationJsTestReference() {
        super("js-test.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = super.getDependencies();
        dependencies.add(JavaScriptHeaderItem.forReference(new AnimationJsReference()));
        return dependencies;
    }

    @Override
    public IResource getResource() {
        return new JavaScriptPackageResource(getClass(), "/js-test.js", null, null, null);
    }

}
