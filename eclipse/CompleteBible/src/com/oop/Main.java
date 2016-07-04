package com.oop;

import java.io.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        int folderLength = new File("documents-export-2016-02-20").listFiles().length;
        int bookNum;

        for (int i = 0; i < folderLength; i++) {

            bookNum = i + 1;
            ArrayList bookContents = new ArrayList();

            File fCaption;

            if (bookNum < 10) {
                fCaption = new File("documents-export-2016-02-20/0" + bookNum + ".txt");
            } else {
                fCaption = new File("documents-export-2016-02-20/" + bookNum + ".txt");
            }

            getContents(bookContents, fCaption);

            String contents = "";
            int chapterNum = 0;
            int lineNum = 0;

            //Loop through the ArrayList and reformat the content
            for (int j = 0; j < bookContents.size(); j++) {
                String line = (String)bookContents.get(j);
                if (line.startsWith("Bible")) {
                    line = "<Book=\"" + line.substring(27) + "\">\n";
                    contents = contents + line;
                } else if (line.startsWith("Chapter") && chapterNum == 0) {
                    chapterNum ++;
                    line = "<Chapter number=\"" +  chapterNum + "\">" + "\n";
                    contents = contents + line;
                } else if (line.startsWith("Chapter") && chapterNum > 0) {
                    lineNum = 0;
                    chapterNum ++;
                    line = "</Chapter>" + "\n" + "<Chapter number=\"" +  chapterNum + "\">" + "\n";
                    contents = contents + line;
                }else if (line.startsWith("[")) {
                    lineNum ++;
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

            String result = "bible_books_KJV/";
            if (bookNum < 10) {
                result = result + "0" + bookNum + ".txt";
            } else {
                result = result + bookNum + ".txt";
            }
            writeFile(contents, result);
        } int resultLength = new File("bible_books_KJV").listFiles().length;
        System.out.print("Done! " + resultLength + " books of the bible have been reformatted!");


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
