package de.fluxparticle.animation.graph;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class GraphSolver {

	private Graph<? extends GraphNode> graph;

	private final Comparator<GraphNode> comparator = (n1, n2) -> {
        int cmp = 0;
        if (hasPath(n1, n2)) {
            cmp--;
        }
        if (hasPath(n2, n1)) {
            cmp++;
        }
        if (cmp == 0) {
            return Integer.compare(n1.getId(), n2.getId());
        }
        return cmp;
    };

	public GraphSolver(Graph<? extends GraphNode> graph) {
		this.graph = graph;

		Collection<? extends GraphNode> nodes = graph.getNodes();
		GraphNode[] arrNodes = nodes.toArray(new GraphNode[nodes.size()]);

		Arrays.sort(arrNodes, comparator);

		Map<Integer, Integer> map = new LinkedHashMap<>();

		for (int i = 0; i < arrNodes.length; i++) {
			int id = arrNodes[i].getId();
			map.put(id, i);
		}

		GraphWrapper wrappedGraph = new GraphWrapper(arrNodes);

		for (GraphNode a : arrNodes) {
			int idA = a.getId();
			Collection<? extends GraphNode> neighbours = graph.getNeighbours(a);
			for (GraphNode b : neighbours) {
				int idB = b.getId();
				if (map.get(idA) < map.get(idB)) {
					wrappedGraph.connect(idA, idB);
				} else {
					wrappedGraph.connect(idB, idA);
				}
			}
		}

		this.graph = wrappedGraph;
	}

	public void solve() {
        Set<GraphNode> white;
        {
            Collection<? extends GraphNode> nodes = graph.getNodes();
            nodes.forEach(GraphNode::setBlack);
            white = nodes.stream()
                    .filter(GraphNode::isReferenced)
                    .collect(toSet());
        }

        int levelIndex = 0;
		while (!white.isEmpty()) {
			Collection<GraphNode> level = nextLevel(white);
            if (level.isEmpty()) {
                break;
            }
			white.removeAll(level);

			for (GraphNode graphNode : level) {
				graphNode.setLevel(levelIndex);
                graphNode.setWhite();
			}
			levelIndex++;

			GraphNode[] nodes = level.toArray(new GraphNode[level.size()]);

			new GraphLevelSolver(nodes).solve();
		}
	}

	private Collection<GraphNode> nextLevel(Set<GraphNode> white) {
		return white.stream()
                .filter(b -> white.stream()
                        .filter(a -> a != b)
                        .allMatch(a -> !hasPath(a, b)))
                .collect(toList());
	}

	private boolean hasPath(GraphNode a, GraphNode b) {
		Set<GraphNode> visited = new HashSet<>();

		Queue<GraphNode> queue = new LinkedList<>();
		queue.add(a);
		visited.add(a);

		while (!queue.isEmpty()) {
			GraphNode cur = queue.poll();

            Collection<? extends GraphNode> neighbours = graph.getNeighbours(cur);
            for (GraphNode neighbour : neighbours) {
				if (!visited.contains(neighbour)) {
					if (neighbour.equals(b)) {
						return true;
					}
					queue.add(neighbour);
					visited.add(neighbour);
				}
			}
		}

		return false;
	}

}
