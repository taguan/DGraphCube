package materialization;

import java.util.LinkedList;
import java.util.ListIterator;
 
/**
 * Source : http://www.brilliantsheep.com/generic-combinations-generator-in-java/
 * @author Genk Doko
 * 
 *
 */
public class CombinationsGenerator 
{
    /**
     * Generates all combinations and returns them in a list of lists.
     */
    public static <T> LinkedList<LinkedList<T>> combinations( T[ ] array, int minLevel, int max ) 
    {
        long count = 2 << array.length - 1;
        LinkedList<LinkedList<T>> totalCombinations = new LinkedList<LinkedList<T>>( );
 
        for( int i = 0; i < count; i++ ) 
        {
            LinkedList<T> combinations = new LinkedList<T>( );
 
            for( int j = 0; j < array.length; j++ ) 
            {
                if( ( i & ( 1 << j ) ) != 0 )
                {
                    combinations.add( array[ j ] );
                }
            }
 
            totalCombinations.add( combinations );
        }
 
        return totalCombinations;
    }
 
    /**
     * Prints all combinations.
     */
    public static <T> void printCombinations( LinkedList<LinkedList<T>> totalCombinations )
    {
        ListIterator<LinkedList<T>> totalCombinationsItr = totalCombinations.listIterator( );
 
        while( totalCombinationsItr.hasNext( ) ) 
        {
            LinkedList<T> combinations = totalCombinationsItr.next( );
            ListIterator<T> combinationsItr = combinations.listIterator( );
 
            while( combinationsItr.hasNext( ) )
            {
                System.out.print( combinationsItr.next( ) + ( combinationsItr.hasNext( ) ? ", " : "\n" ));
            }
        }
    }
}
