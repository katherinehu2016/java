package com.oop;

import java.io.*;
import java.util.ArrayList;

public class Main {

	public static void main(String args[]) {
		/*
		 * String x = "Hello word";
		 * 
		 * if (x.startsWith("hello")) { System.out.println("haha, i am studpid"
		 * ); }
		 * 
		 * 
		 * System.exit(-1);
		 * 
		 * try(BufferedReader br = new BufferedReader(new
		 * FileReader("50-o.txt"))) { StringBuilder sb = new StringBuilder();
		 * String line = br.readLine();
		 * 
		 * </Line> while (line != null) { sb.append(line);
		 * sb.append(System.lineSeparator()); line = br.readLine(); } String
		 * everything = sb.toString(); System.out.print(everything); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

		// Load txt file into an ArrayList
		ArrayList bookContents = new ArrayList();
		File fCaption = new File("files\\oldformat59.txt");
		FileUtil.getContents(bookContents, fCaption);

		String contents = "";
		int chapterNum = 0;
		int lineNum = 0;

		// Loop through the ArrayList and reformat the content
		for (int i = 0; i < bookContents.size(); i++) {
			String line = (String) bookContents.get(i);
			if (line.equals("Philippians")) {
				line = "<Book>" + "\n";
				contents = contents + line;
			} else if (line.startsWith("Chapter") && chapterNum == 0) {
				chapterNum++;
				line = "<Chapter number=\"" + chapterNum + "\">" + "\n";
				contents = contents + line;
			} else if (line.startsWith("Chapter") && chapterNum > 0) {
				lineNum = 0;
				chapterNum++;
				line = "</Chapter>" + "\n" + "<Chapter number=\"" + chapterNum + "\">" + "\n";
				contents = contents + line;
			} else if (line.startsWith("[")) {
				lineNum++;
				if (lineNum < 10) {
					line = line.substring(3).trim();
				} else if (lineNum > 10 || lineNum == 10) {
					line = line.substring(4).trim();
				}
				line = "<Line number=\"" + lineNum + "\">" + line + "</Line>" + "\n";
				contents = contents + line;
			} else if (line.equals("")) {
			}
		}

		String end = "</Chapter>\n" + "</Book>";
		contents = contents + end;

		writeFile(contents, "Result.txt");
		System.out.print("Done!");
	}

	public static void writeFile(String content, String filename) {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(filename));

			out.print(content);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}

	/*
	 * Get each line from the text file and add it to the ArrayList passed in.
	 */
	public static ArrayList getContents(ArrayList lines, File aFile) {
		StringBuilder contents = new StringBuilder();
		if (lines == null) {
			lines = new ArrayList();
		}

		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null; // not declared within while loop

				while ((line = input.readLine()) != null) {
					lines.add(line);
				}
			} finally {
				input.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return lines;
	}

}