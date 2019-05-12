/*
 * Steve Rich - General Utility class, stores methods likely to be repeated.
 */
package permadb.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class generalUtils {

    public static ArrayList<String> ArrayListLoader(String inputFile) {
        ArrayList<String> output = new ArrayList<>();
        String line;
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(inputFile));
            while ((line = br.readLine()) != null) {
                output.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
