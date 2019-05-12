/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permadb.utils;

import permadb.utils.generalUtils;
import java.util.ArrayList;
import java.util.HashSet;
/**
 *
 * @author Firma
 */
public class findUnique extends generalUtils {
    public static void main(String[] args){
        String inputFile = "C:\\Users\\Firma\\Desktop\\Perma\\light.csv";
        ArrayList<String> checkDupes = ArrayListLoader(inputFile);
        
        HashSet<String> storage = new HashSet<>();
        for(String line:checkDupes){
            String[] lineSplit = line.split(" ");
            if(!storage.contains(line)){
                storage.add(line);
                System.out.println(line);
            }
        }
        
        
    }
}
