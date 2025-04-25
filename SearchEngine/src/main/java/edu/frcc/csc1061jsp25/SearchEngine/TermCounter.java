package edu.frcc.csc1061jsp25.SearchEngine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class TermCounter {

	private Map<String, Integer> map;
	private String url;

	// Constructors
	public TermCounter(String url) {
		this.url = url;
		this.map = new HashMap<>();
	}

	// Getters and Setters
	public String getLabel() {
		return url;
	}

	public void put(String term, int count) {
		map.put(term, count);
	}
	
	public void processElements(Elements paragraphs) {
		for(Node node: paragraphs) {
			processTree(node);
			
		}
	}
	private void processTree(Node root) {
		for(Node node: new WikiNodeIterable(root)) {
			if(node instanceof TextNode) {
				processText(((TextNode) node).text());
			}
		}
	}
	private void processText(String text) {
		String[] words = text.replace("\\pP", "").toLowerCase().split("\\s+");
		for(String term : words) {
			incrementTermCount(term);
		}
	}
	
	public void incrementTermCount(String term) {
		Integer value = map.get(term);
		if (value == null) {
			map.put(term, 1);
		}
		else {
			map.put(term, value + 1);
		}
	}

	
	public void printCounts() {
		System.out.println(url);
		for(String key: map.keySet()) {
			int count = map.get(key);
			System.out.println(key + ", " + count);
		}
	}
	public static void main(String[] args) throws IOException {
		
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		WikiFetcher wf = new WikiFetcher();
		Elements paragraphs = wf.readWikipedia(url);
		
		TermCounter counter = new TermCounter(url);
		counter.processElements(paragraphs);
		counter.printCounts();
		
	}

}
