
package permadb.utils;

/**
 *
 * Steve Rich - scratchpad, syntax generator for tedious population.
 */
import java.util.ArrayList;
public class scratch extends generalUtils {
    public static void main(String[] args){
        ArrayList<String> input = ArrayListLoader("C:\\Users\\Firma\\Desktop\\Perma\\hashLoad.csv");
        for(String hash:input){
            String[] lineSplit = hash.split(" is ");
            System.out.println("rootStorage.put(\"" + lineSplit[0] + "\",\"" + lineSplit[1] + "\");");
        }
    }
}
