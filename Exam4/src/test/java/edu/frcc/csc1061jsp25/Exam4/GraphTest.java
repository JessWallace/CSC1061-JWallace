package edu.frcc.csc1061jsp25.Exam4;

import java.util.ArrayList;
import java.util.List;

import edu.frcc.csc1061jsp25.Exam4.Graph;
import edu.frcc.csc1061jsp25.Exam4.Graph.Vertex;

public class GraphTest {

	public static void main(String[] args) {
		List<Integer> vertices = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vertices.add(i);
		}

		// Edges for MST
		Integer[][] edges = {
				{0, 1, 5}, {0, 2, 4}, 
				{1, 0, 5}, {1, 2, 2}, {1, 5, 3},
				{2, 0, 4}, {2, 1, 2}, {2, 5, 4}, {2, 3, 5},
				{3, 2, 5}, {3, 5, 6}, {3, 4, 7},
				{4, 3, 7}, {4, 5, 8},
				{5, 1, 3}, {5, 2, 4}, {5, 3, 6}, {5, 4, 8}
		};


		System.out.println();

		Graph<Integer> graph = new Graph<>(vertices, edges);
		System.out.println("Original Graph:");
		graph.printEdges();
		
		System.out.println("Minimum Spanning Tree:");
		graph.findMinimumSpanningTree().printEdges();
		
		//DFS
		System.out.println("DFS Traversal starting from node 0:");
		List<Graph<Integer>.Vertex> dfsResult = graph.dfs(graph.findVertex(0));
		for (Graph<Integer>.Vertex v : dfsResult) {
		    System.out.print(v.getKey() + " ");
		}
		System.out.println();

		// BFS
		System.out.println("BFS Traversal starting from node 0:");
		List<Integer> bfsResult = graph.bfs();
		for (Integer val : bfsResult) {
			System.out.print(val + " ");
		}
		System.out.println();
		
		
	}


}

