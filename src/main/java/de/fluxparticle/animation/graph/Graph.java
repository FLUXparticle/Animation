package de.fluxparticle.animation.graph;

import java.util.Collection;

public interface Graph<T extends GraphNode> {

	public Collection<T> getNodes();

	public Collection<T> getNeighbours(GraphNode node);

}
