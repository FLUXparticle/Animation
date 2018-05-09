package de.fluxparticle.animation.servlet;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;

/**
 * Created by sreinck on 26.04.18.
 */
public class AnimationPage extends WebPage {

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(new AnimationJsTestReference()));
        response.render(JavaScriptHeaderItem.forScript("this['js-test'].de.fluxparticle.animation.example.start([{name:'A',deps:['B']},{name:'B',deps:[]}])", "start"));
    }

}
