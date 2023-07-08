package avengers;
import java.util.Arrays;

/**
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0). 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }

        String inputFile = args[ 0 ];
        String outputFile = args[ 1 ];

        StdIn.setFile(inputFile);
        StdOut.setFile(outputFile);

        int n = StdIn.readInt();

        System.out.println( n );

        StdIn.readLine();

        double [] functionalityArray = new double[ n ];

        int[][] adjacencyMatrix = new int[ n ][ n ];

        for ( int i = 0; i < n; i++ ){

            int index = StdIn.readInt();
            double functionality = StdIn.readDouble();

            functionalityArray[ index ] = functionality;

            StdIn.readLine();

        }

        for ( int i = 0; i < n; i++ ){

            System.out.println( functionalityArray[ i ]);

        }

        for ( int i = 0; i < n; i++ ){

            for ( int j = 0; j < n; j++ ){

                int weight = StdIn.readInt();

                adjacencyMatrix[ i ][ j ] = (int) (weight  / ( functionalityArray[ i ] * functionalityArray[ j ])); 

            }

            StdIn.readLine();

        }

        for ( int i = 0; i < n; i++ ){

            for ( int j = 0; j < n; j++ ){

                System.out.print( adjacencyMatrix[ i ][ j ] + "\t" );

            }
        }

        System.out.println( n);

        int[] minCost2 = dijkstra( adjacencyMatrix , 0 );

        for ( int i = 0; i < n - 1; i++){

            System.out.print( minCost2[ i ] + " \t" );

        }

        System.out.println( minCost2[ n - 1 ] );

        StdIn.setFile(outputFile);
        StdOut.print( minCost2[ n - 1 ]);
    }   

	
    public static int[] dijkstra( int[][] graph, int start ) {

        int[] space = new int[ graph.length ];

        Arrays.fill( space, Integer.MAX_VALUE );

        space[ start ] = 0;

        boolean[] isVisited = new boolean[ graph.length ];

        while ( true ) {

            int shortestDistance = Integer.MAX_VALUE;
            int shortestIndex = -1;

            for ( int i = 0; i < graph.length; i++)  {

                if ( space[ i ] < shortestDistance && !isVisited[ i ] ) {
                    
                    shortestDistance = space[ i ];
                    shortestIndex = i;

                }
            }
            
            if ( shortestIndex == -1 ) {
                
                return space;
            }

            for ( int i = 0; i < graph[shortestIndex].length; i++ ) {

                if ( graph[ shortestIndex ][ i ] != 0 && space[ i ] > space[ shortestIndex ] + graph[ shortestIndex ][ i ] ) {

                    space[ i ] = space[ shortestIndex ] + graph[ shortestIndex ][ i ];

                }
            }
            
            isVisited[ shortestIndex ] = true;

        }
    }
}
