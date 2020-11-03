package com.games.client;

import java.io.*;
import java.util.ArrayList;


public class FileReader {

    private static BufferedReader reader;

    //READS .txt FILES CONTAINING Planets
    public static ArrayList<String> readMapFile(String filename) {

        ArrayList<String> strings = new ArrayList<String>();
        //            URL url = FileReader.class.getResource(filename);
        //for jar
//            InputStream inputFile = this.getClass().getClassLoader().getResourceAsStream("/maps/"+filename);
        reader = new BufferedReader(new InputStreamReader(
                FileReader.class.getClassLoader().getResourceAsStream(filename)));
//            reader = new BufferedReader();
        try {
            String str = reader.readLine();
            strings.add(str);

            while(str!=null) {
                str = reader.readLine();
                strings.add(str);
            }
        }catch (IOException e) {
            System.out.println("IOException");
        }
        return strings;
    }
}
