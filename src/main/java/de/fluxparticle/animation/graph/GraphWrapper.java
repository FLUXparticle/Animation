package de.fluxparticle.animation.graph;

import java.util.*;

public class GraphWrapper implements Graph<GraphNode> {

	private final Map<Integer, GraphNode> nodes = new HashMap<>();

	private final Map<Integer, Set<Integer>> edges = new HashMap<>();

	public GraphWrapper(GraphNode... nodes) {
		for (GraphNode graphNode : nodes) {
			Integer id = graphNode.getId();
			this.nodes.put(id, graphNode);
			edges.put(id, new HashSet<>());
		}
	}

	public void connect(Integer fromId, Integer toId) {
		Set<Integer> neighbourIds = edges.get(fromId);
		neighbourIds.add(toId);
	}

	@Override
	public Collection<GraphNode> getNodes() {
		return Collections.unmodifiableCollection(nodes.values());
	}

	@Override
	public Collection<GraphNode> getNeighbours(GraphNode node) {
		int nodeId = node.getId();

		Collection<GraphNode> neighbours = new LinkedList<>();

		for (Integer neighbourId : edges.get(nodeId)) {
			neighbours.add(nodes.get(neighbourId));
		}

		return neighbours;
	}

}
