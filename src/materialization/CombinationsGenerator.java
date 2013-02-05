package materialization;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 
 * @author http://rosettacode.org/wiki/Combinations#Java
 *
 */
public class CombinationsGenerator{
 
 
        private static String bitprint(int u){
                String s= "";
                for(int n= 0;u > 0;++n, u>>= 1)
                        if((u & 1) > 0) s+= n + ",";
                return s.substring(0, s.length()-1);
        }
 
        private static int bitcount(int u){
                int n;
                for(n= 0;u > 0;++n, u&= (u - 1));//Turn the last set bit to a 0
                return n;
        }
 
        public static LinkedList<String> comb(int c, int n){
                LinkedList<String> s= new LinkedList<String>();
                for(int u= 0;u < 1 << n;u++)
                        if(bitcount(u) == c) s.push(bitprint(u));
                Collections.sort(s);
                return s;
        }
}