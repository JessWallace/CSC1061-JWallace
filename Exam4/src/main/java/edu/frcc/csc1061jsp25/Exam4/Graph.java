package edu.frcc.csc1061jsp25.Exam4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Graph<E> {
	public List<Vertex> vertices = new ArrayList<>();

	private class Vertex {
		private E elem;
		private List<Edge> neighbors = new ArrayList<>();

		public Vertex(E elem) {
			this.elem = elem;
		}

		public E getKey() {
			return elem;
		}

		@Override
		public boolean equals(Object other) {
			if (!(other instanceof Graph.Vertex))
				return false;

			if (elem.equals(((Vertex) other).elem)) {
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			return elem.toString();
		}
	}

	private class Edge implements Comparable<Edge> {
		private Vertex s;
		private Vertex d;
		private int weight;

		public Edge(Vertex s, Vertex d, int weight) {
			this.s = s;
			this.d = d;
			this.weight = weight;
		}

		public boolean equals(Object edge) {
			return s.equals(((Edge) edge).s) && d.equals(((Edge) edge).d);
		}

		@Override
		public int compareTo(Graph<E>.Edge o) {
			return (int) (weight - o.weight);
		}
	}

	public Graph(List<Vertex> vertices) {
		for (Vertex vertex : vertices) {
			addVertex(new Vertex(vertex.getKey()));
		}
	}

	public Graph(List<E> vertices, E[][] edges) {
		for (E ver : vertices) {
			addVertex(new Vertex(ver));
		}
		createAdjacencyLists(edges);
	}

	public boolean addVertex(Vertex vertex) {
		if (!vertices.contains(vertex)) {
			vertices.add(vertex);
			return true;
		} else {
			return false;
		}

	}

	public boolean addEdge(Edge edge) {

		List<Edge> neighbors = edge.s.neighbors;
		if (!neighbors.contains(edge)) {
			neighbors.add(edge);
			return true;
		} else {
			return false;
		}
	}

	private Vertex findVertex(E key) {
		for (Vertex v : vertices) {
			if (v.elem.equals(key)) {
				return v;
			}
		}
		return null;
	}

	private void createAdjacencyLists(E[][] edges) {
		for (int i = 0; i < edges.length; i++) {
			addEdge(new Edge(findVertex(edges[i][0]), findVertex(edges[i][1]), (int) edges[i][2]));
		}
	}

	public void printEdges() {
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print("Vertex: " + vertices.get(i).toString() + ":");
			List<Edge> neighbors = vertices.get(i).neighbors;
			for (Edge edge : neighbors) {
				System.out.print("(" + edge.s + ", " + edge.d + ", " + edge.weight + ")");
			}
			System.out.println();
		}
	}

	public List<Vertex> getChildNodes(Vertex vertex) {
		List<Vertex> childNodes = new ArrayList<>();
		List<Edge> neighbors = vertex.neighbors;
		for (Edge edge : neighbors) {
			childNodes.add(edge.d);
		}
		return childNodes;
	}

	/*
	 * TODO: Implement the DFS algorithm for a graph either recursively or
	 * iteratively using a stack. It should return a list of all the vertices in the
	 * pre-order depth-first traversal.
	 */
	public List<Vertex> dfs(Vertex root) {
		List<Vertex> seen = new ArrayList<>();
		Deque<Vertex> stack = new ArrayDeque<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Vertex current = stack.pop();
			if (!seen.contains(current)) {
				seen.add(current);
				List<Vertex> ch = getChildNodes(current);
				// Push in reverse order to maintain consistent pre-order
				for (int i = ch.size() - 1; i >= 0; i--) {
					stack.push(ch.get(i));
				}
			}
		}

		return seen;
	}

	/*
	 * TODO: Implement the BFS algorithm for a graph. It should return a list of all
	 * the vertices in the breadth-first traversal.
	 */
	public List<E> bfs() {
		List<E> result = new ArrayList<>();
		if (vertices.isEmpty())
			return result;

		List<Vertex> seen = new ArrayList<>();
		Deque<Vertex> queue = new ArrayDeque<>();

		Vertex root = vertices.get(0); // start from the first vertex
		queue.offer(root);
		seen.add(root);

		while (!queue.isEmpty()) {
			Vertex current = queue.poll();
			result.add(current.getKey());

			for (Vertex neighbor : getChildNodes(current)) {
				if (!seen.contains(neighbor)) {
					seen.add(neighbor);
					queue.offer(neighbor);
				}
			}
		}

		return result;
	}

	/*
	 * TODO: Create a spanning tree using Kruskal's Algorithm and return it. The
	 * spanning tree will be a new graph
	 */
	public Graph<E> findMinimumSpanningTree() {
		Graph<E> mst = new Graph<>(new ArrayList<>()); // start with empty graph

		// Add all vertices to the new graph
		for (Vertex v : vertices) {
			mst.addVertex(new Vertex(v.getKey()));
		}

		// Step 1: Gather all unique edges
		List<Edge> allEdges = new ArrayList<>();
		for (Vertex v : vertices) {
			for (Edge e : v.neighbors) {
				if (!allEdges.contains(e)) {
					allEdges.add(e);
				}
			}
		}

		// Step 2: Sort edges by weight
		Collections.sort(allEdges);

		// Step 3: Create disjoint sets (union-find)
		List<List<Vertex>> sets = new ArrayList<>();
		for (Vertex v : vertices) {
			List<Vertex> set = new ArrayList<>();
			set.add(v);
			sets.add(set);
		}

		// Step 4: Build MST
		for (Edge e : allEdges) {
			Vertex u = e.s;
			Vertex v = e.d;

			List<Vertex> setU = null;
			List<Vertex> setV = null;

			for (List<Vertex> set : sets) {
				if (set.contains(u))
					setU = set;
				if (set.contains(v))
					setV = set;
			}

			if (setU != setV) {
				// Add edge to MST (use copies of u and v from mst)
				Vertex copyU = mst.findVertex(u.getKey());
				Vertex copyV = mst.findVertex(v.getKey());
				mst.addEdge(mst.new Edge(copyU, copyV, e.weight));
				mst.addEdge(mst.new Edge(copyV, copyU, e.weight));

				// Union the sets
				setU.addAll(setV);
				sets.remove(setV);
			}
		}

		return mst;
	}
}
