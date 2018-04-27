package de.fluxparticle.animation;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptPackageResource;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.List;

/**
 * Created by sreinck on 26.04.18.
 */
class AnimationJsReference extends ResourceReference {

    public AnimationJsReference() {
        super("animation-js.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = super.getDependencies();
        dependencies.add(JavaScriptHeaderItem.forReference(new KotlinJsReference()));
        return dependencies;
    }

    @Override
    public IResource getResource() {
        return new JavaScriptPackageResource(getClass(), "../../../animation-js.js", null, null, null);
    }

}
