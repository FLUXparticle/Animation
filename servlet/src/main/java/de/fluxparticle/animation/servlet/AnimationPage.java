package de.fluxparticle.animation.servlet;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;

/**
 * Created by sreinck on 26.04.18.
 */
public class AnimationPage extends WebPage {

    @Override
    public void renderHead(IHeaderResponse response) {
        AnimationJsTestReference reference = new AnimationJsTestReference();
        JavaScriptReferenceHeaderItem headerItem = JavaScriptHeaderItem.forReference(reference);
        response.render(headerItem);
    }

}
