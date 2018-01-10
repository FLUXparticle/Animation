package de.fluxparticle.animation.graph;

import java.util.Collection;

public interface Graph {

	Collection<? extends GraphNode> getNodes();

	Collection<? extends GraphNode> getNeighbours(GraphNode node);

}
