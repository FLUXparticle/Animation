package de.fluxparticle.animation.graph;

public interface GraphNode {

	int getPreferredPos();

	void setPos(int pos);

	void setLevel(int level);

    void setWhite();

    void setBlack();

	int getSize();

	int getId();

    boolean isReferenced();

}
