package com.games.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class FileReader {

    private static BufferedReader reader;

    //READS .txt FILES CONTAINING Planets
    public static ArrayList<String> readMapFile(String filename) {

        ArrayList<String> strings = new ArrayList<String>();
        try{
            reader = new BufferedReader(new java.io.FileReader(filename));
        }catch (FileNotFoundException e) {
            System.out.println(filename+" not found!");
        }
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
