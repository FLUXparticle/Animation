package de.fluxparticle.animation.graph;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Integer.compare;

public class GraphLevelSolver {

	private static class Range {

		private int min;

		private int max;

		public Range(int min, int max) {
			super();
			this.min = min;
			this.max = max;
		}

		public int getMin() {
			return min;
		}

		public void setMin(int min) {
			if (min > this.min) {
				this.min = min;
			}
		}

		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			if (max < this.max) {
				this.max = max;
			}
		}

		public boolean isEmpty() {
			return min > max;
		}

		public int getCenter() {
			return (min + max) / 2;
		}

	}

	private static final Comparator<GraphNode> COMPARATOR = (o1, o2) -> compare(o1.getPreferredPos(), o2.getPreferredPos());

	private final GraphNode[] nodes;

	private final int maxK;

	private final int min;

	private final int max;

	public GraphLevelSolver(GraphNode[] nodes) {
		this.nodes = nodes;

		Arrays.sort(nodes, COMPARATOR);

		int maxK = 0;
		for (GraphNode graphNode : nodes) {
			maxK += graphNode.getSize();
		}
		this.maxK = maxK;

		min = nodes[0].getPreferredPos() - maxK;
		max = nodes[nodes.length-1].getPreferredPos() + maxK;
	}

	public void solve() {
		int k = findK();

		Range[] ranges = mkRanges(k);

		for (int i = 0; i < ranges.length; i++) {
			nodes[i].setPos(ranges[i].getCenter());
		}
	}

	private int findK() {
		int lo = 0;
		int hi = maxK;

		while (lo < hi) {
			int k = (lo + hi) / 2;
			if (check(k)) {
				hi = k;
			} else {
				lo = k + 1;
			}
		}

		return hi;
	}

	private boolean check(int k) {
		Range[] ranges = mkRanges(k);

		for (int i = 0; i < ranges.length; i++) {
			if (ranges[i].isEmpty()) {
				return false;
			}
		}

		return true;
	}

	private Range[] mkRanges(int k) {
		Range[] ranges = new Range[nodes.length];

		for (int i = 0; i < ranges.length; i++) {
			ranges[i] = new Range(min, max);
		}

		for (int i = 0; i < ranges.length; i++) {
			ranges[i].setMin(nodes[i].getPreferredPos() - k);
			ranges[i].setMax(nodes[i].getPreferredPos() + k);
		}

		for (int i = 1; i < ranges.length; i++) {
			ranges[i].setMin(ranges[i-1].getMin() + nodes[i-1].getSize());
		}

		for (int i = ranges.length - 2; i >= 0; i--) {
			ranges[i].setMax(ranges[i+1].getMax() - nodes[i].getSize());
		}

		return ranges;
	}

}
