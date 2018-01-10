package de.fluxparticle.animation.graph;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class GraphSolver {

	private Graph graph;

    public GraphSolver(Graph graph) {
        this.graph = new GraphWrapper(graph);
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
                        .noneMatch(a -> hasPath(a, b)))
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
