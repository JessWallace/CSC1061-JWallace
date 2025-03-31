/*
 * SentimentAnalysisMain.java
 *
 * CSC1061 - Sentiment Analysis Assignment
 * Author: Jessika Wallace
 * Date: 03.31.2025
 *
 * Description:
 * This program performs simple sentiment analysis on user input using a custom
 * HashMap implementation. It loads a sentiment dictionary from a CSV file 
 * ("sentiments.txt") and calculates a sentiment score based on the presence
 * of one and two-word phrases. The total and average sentiment score are 
 * printed after the user finishes inputting text.
 *
 * Key Features:
 * - Custom rehashing HashMap that expands dynamically.
 * - Sentiment scores from -5 (very negative) to 5 (very positive).
 * - Handles both individual words and two-word phrases.
 * - Normalizes input by removing punctuation and converting to lower case.
 * - Displays total word count, sentiment score, and average sentiment (rounded to 2 decimal places).
 *
 * Usage:
 * Run the program, type in text, then type "END" on a new line to calculate sentiment.
 */

package edu.frcc.csc1061jsp25.SentimentAnalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SentimentAnalysisMain {

	public static void main(String[] args) {
		MyHashMap<String, Integer> map = new MyHashMap<String, Integer>();

		Scanner scnr = new Scanner(System.in);
		String input;
		String inString = "";
		String word;
		int score;
		int total = 0;
		String preKey = null;

		// Read .txt file
		try (BufferedReader reader = new BufferedReader(new FileReader("sentiments.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2) {
					word = parts[0].toLowerCase();
					score = Integer.parseInt(parts[1]);
					map.put(word, score);
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
			return;
		}

//		//checking reader
//		System.out.println("Map loaded. Sample entries: ");
//		int count = 0;
//		for (String key : map.keySet()) {
//			System.out.println(key + " -> " + map.get(key));
//			if (++count > 10)break;
//		}

		// Next, get user input from the keyboard. You should keep reading input until
		// the user types the word “END”.
		System.out.println("Input the sentiments you wish to analyzed. To finish press enter and type \"End\". ");
		input = scnr.next();
		while (!input.equalsIgnoreCase("end")) {
			inString = inString + input;
			input = scnr.nextLine();

		}
		// Remove all punctuation from the strings you read and convert them to
		// lower-case. This will allow it to match words in the input file more easily.
		// This is how to convert all the characters that are not letters to empty
		// strings, convert to lower case and separate the words on one or more spaces
		// in one line:
		String[] words = inString.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		// checking input
//		System.out.println(inString + ".");

		// Keep track of the total sentiment of the input. For each word, check if it’s
		// in the hash map. If so, add the sentiment into the running total.
		for (int i = 0; i < words.length; i++) {

			String key = words[i];

			// Checks for two word phrase
			if (preKey != null) {
				String phrase = preKey + " " + key;
				if (map.containsKey(phrase)) {
					total += map.get(phrase);
					preKey = null;
					continue;
				}
			}

//			Checking word
//			System.out.println("Checking word: " + key);

			// Checks for one-word phrase
			if (map.containsKey(key)) {
//				checking word in map
//				System.out.println("Found in map: " + key + " " + map.get(key));
				total += map.get(key);
			}

			preKey = key;
		}
		System.out.println("Number of Words: " + words.length);
		System.out.println("Total Sentiment: " + total);
		System.out.printf("Average Sentiment: %.2f%n", ((double) total / (double) words.length));

	}

}
