package com.oop;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int bookNum;

        for (int i = 0; i < 2; i++) {
        	//get contents of files

            bookNum = i + 1;

            File fCaption;

            if (bookNum < 10) {
                fCaption = new File("bible_books_KJV/0" + bookNum + ".txt");
            } else {
                fCaption = new File("bible_books_KJV/" + bookNum + ".txt");
            }

            String contents = "";

            File inputFile = fCaption;
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;

            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document doc = null;

            try {
                doc = dBuilder.parse(inputFile);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            doc.getDocumentElement().normalize();
            
            //reformat contents to xml

            NodeList nlChapter = doc.getElementsByTagName("Chapter");
            contents = contents + "Bible, King James Version\n";
            for (int j = 0; j < nlChapter.getLength(); j++){
                Node nChapter = nlChapter.item(j);
                Element eChapter = (Element) nChapter;
                contents = contents + "Chapter " + eChapter.getAttribute("number") + "\n";
                NodeList nlLine = eChapter.getElementsByTagName("Line");
                for (int k = 0; k < nlLine.getLength(); k++) {
                    Node nLine = nlLine.item(k);
                    Element eLine = (Element) nLine;
                    contents = contents + "[" + eLine.getAttribute("number") + "]" + eLine.getTextContent() + "\n";
                }
            }

            String result = "Result Files/";
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
