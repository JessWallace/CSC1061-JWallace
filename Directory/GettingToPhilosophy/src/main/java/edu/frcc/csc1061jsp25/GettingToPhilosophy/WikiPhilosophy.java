package edu.frcc.csc1061jsp25.GettingToPhilosophy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class WikiPhilosophy {

	final static List<String> visited = new ArrayList<String>();
	final static WikiFetcher wf = new WikiFetcher();

	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 *
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 *
	 * 1. Clicking on the first non-parenthesized, non-italicized link 2. Ignoring
	 * external links, links to the current page, or red links 3. Stopping when
	 * reaching "Philosophy", a page with no links or a page that does not exist, or
	 * when a loop occurs
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String destination = "https://en.wikipedia.org/wiki/Philosophy";
		String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";

		testConjecture(destination, source, 20);
	}

	/**
	 * Starts from given URL and follows first link until it finds the destination
	 * or exceeds the limit.
	 *
	 * @param destination
	 * @param source
	 * @throws IOException
	 */
	public static void testConjecture(String destination, String source, int limit) throws IOException {
		Document doc = null;
		Connection conn = Jsoup.connect(source);
		try {
			doc = conn.get();
		} catch (Exception e) {
			System.out.println("Could not open page. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}

		// select the content text and pull out the paragraphs.
		Element content = doc.getElementById("mw-content-text");
		Elements paragraphs = content.select("p");
		// track open/close parenthesis while parsing
		int parenDepth = 0;

		for (Element para : paragraphs) {
			Iterable<Node> iter = new WikiNodeIterable(para);
			for (Node node : iter) {
				// TODO: FILL THIS IN!
				// If this node is a text node make sure you are not within parentheses
				if (node instanceof TextNode) {
					String text = ((TextNode) node).text();
					for (char c : text.toCharArray()) {
						if (c == '(')
							parenDepth++;
						else if (c == ')')
							parenDepth = Math.max(parenDepth - 1, 0);

					}
				}
				// If this node has a link you can get it by accessing the href attribute in the
				// node
				if (node instanceof Element) {
					if (node.nodeName().equals("a") && parenDepth == 0) {
						if (isItalic((Element) node)) {
							continue;
						}
						String link = node.attr("href");
						// If the link is not null and not an empty string and does not start with a #
						// sign
						if (link == null || link.isEmpty() || link.startsWith("#") || link.startsWith("/wiki/Help:")
								|| node.attr("class").contains("new")) {
							continue;
						}
						String absUrl = "https://en.wikipedia.org" + link;

						if (visited.contains(absUrl)) {
							System.out.println("already visited: " + absUrl);
							return;
						}

						if (absUrl.equals(destination)) {
							visited.add(absUrl);
							System.out.println("Successfully reached Philosophy.");
							for (String v : visited) {
								System.out.println(v);
							}
							return;
						}
						// and is not within parentheses, follow the link recursively by calling
						// testConjecture()
						// until you reach your objective or run past the limit.
						testConjecture(destination, absUrl, limit - 1);
						return;
					}
				}

			}

		}
		System.out.println("No valid link found. Exiting.");
	}

	private static boolean isItalic(Element node) {
		for (Element parent : node.parents()) {
			if (parent.tagName().equals("i") || parent.tagName().equals("em")) {
				return true;
			}
		}
		return false;
	}

}
