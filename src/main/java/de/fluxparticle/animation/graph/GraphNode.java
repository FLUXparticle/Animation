package de.fluxparticle.animation.graph;

public interface GraphNode {

	public int getPreferredPos();

	public void setPos(int pos);

	public void setLevel(int level);

    public void setWhite();

    public void setBlack();

	public int getSize();

	public int getId();

    public boolean isReferenced();

}
