package avengers;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes 
 * Then, write into the output file a boolean (true or false) indicating if the graph is still connected
*/

public class PredictThanosSnap {
	 
    public static void main (String[] args) {
 
        if ( args.length < 2 ) {

            StdOut.println( "Execute: java PredictThanosSnap <INput file> <OUTput file>" );

            return;

        }

        String inputFile = args[ 0 ];
        String outputFile = args[ 1 ];

        StdIn.setFile( inputFile );
        StdOut.setFile( outputFile );

        int seed = StdIn.readInt();

        StdRandom.setSeed( seed );

        int n = StdIn.readInt();
        int [][] ADJMatrix = new int [ n ][ n ];
        int i = 0;
        int j = 0;

        while ( i < n ){

            j = 0;

            while ( j < n ){

                ADJMatrix[ i ][ j ] = StdIn.readInt();

                j++;

            }

            StdIn.readLine();

            i++;

        }

        int counter = 0;
        i = 0;

        while ( i < n ){

            if ( StdRandom.uniform() < 0.5 ){

                counter++;

                j = 0;

                while (  j < n ){

                    ADJMatrix[ i ][ j ] = 0;
                    ADJMatrix[ j ][ i ] = 0;

                    j++;
                }
            }

            i++;
        }

        int vertexLeft = n - counter;
        int total = 0;
        i = 0;

        while ( i < n ){

            j = 0;

            while ( j < n ){

                if ( ADJMatrix[ j ][ i ] == 1 ){

                    total++;

                    break;

                }

                j++;
            }

            i++;

        }

        boolean result = false;
        result = ( total == vertexLeft );
        
        StdOut.print( result );
        StdOut.close();

    }
}
