package de.fluxparticle.animation.docsgen.servlet;

import com.google.gson.Gson;
import com.google.inject.Inject;
import de.fluxparticle.animation.deps.Module;
import de.fluxparticle.animation.deps.ModuleGraph;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import java.time.LocalDate;

/**
 * Created by sreinck on 26.04.18.
 */
public class AnimationPage extends WebPage {

    @Inject
    private ModuleGraph graph;

    public AnimationPage() {
        add(new Label("created", "Created on: " + LocalDate.now()));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(new AnimationJsTestReference()));

        Module[] modules = graph.getModules();
        String json = new Gson().toJson(modules);
        response.render(JavaScriptHeaderItem.forScript("this['js-test'].de.fluxparticle.animation.example.start(" + json + ")", "start"));
    }

}
