package de.fluxparticle.animation.graph;

import de.fluxparticle.utils.chain.Chain;

import java.util.*;

import static de.fluxparticle.utils.chain.Chain.cons;
import static de.fluxparticle.utils.chain.Chain.emptyChain;

public class GraphWrapper implements Graph {

	private final Map<Integer, GraphNode> nodes = new HashMap<>();

	private final Map<Integer, Set<Integer>> edges = new HashMap<>();

	public GraphWrapper(Graph graph) {
        Collection<? extends GraphNode> nodes = graph.getNodes();
        for (GraphNode node : nodes) {
            depthFirst(graph, node, emptyChain());
        }
    }

    private void depthFirst(Graph graph, GraphNode node, Chain<GraphNode> chain) {
        if (nodes.containsKey(node.getId())) {
            return;
        }

        Integer id = node.getId();
        nodes.put(id, node);

        HashSet<Integer> edgeSet = new HashSet<>();
        for (GraphNode next : graph.getNeighbours(node)) {
            if (chain.asStream().noneMatch(next::equals)) {
                edgeSet.add(next.getId());
                depthFirst(graph, next, cons(next, chain));
            }
        }

        edges.put(id, edgeSet);
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
