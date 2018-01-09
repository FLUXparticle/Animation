package de.fluxparticle.animation.object;

public interface ElementNodeVisitor<R> {

	public R visitPath(ElementPath path);

	public R visitRectangle(ElementRectangle rectangle);

	public R visitObject(ElementObject object);

}
