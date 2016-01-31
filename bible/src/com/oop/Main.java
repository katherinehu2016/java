package com.oop;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        /*
        try(BufferedReader br = new BufferedReader(new FileReader("SampleText"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            System.out.print(everything);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ArrayList bookContents = new ArrayList();
        File fCaption = new File("SampleText");
        FileUtil.getContents(bookContents, fCaption);


        System.out.print("Done!");
        writeFile("Hello", "Result.txt");
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
     * Get each line from the text file and add it to the arraylist passed in.
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
