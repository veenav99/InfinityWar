package avengers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a starting event and an Adjacency Matrix representing a graph of all possible 
 * events once Thanos arrives on Titan, determine the total possible number of timelines 
 * that could occur AND the number of timelines with a total Expected Utility (EU) at 
 * least the threshold value.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * UseTimeStoneInputFile name is passed through the command line as args[0]
 * Read from UseTimeStoneInputFile with the format:
 *    1. t (int): expected utility (EU) threshold
 *    2. v (int): number of events (vertices in the graph)
 *    3. v lines, each with 2 values: (int) event number and (int) EU value
 *    4. v lines, each with v (int) edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note 1: the last v lines of the UseTimeStoneInputFile is an ajacency matrix for a directed
 * graph. 
 * The rows represent the "from" vertex and the columns represent the "to" vertex.
 * 
 * The matrix below has only two edges: (1) from vertex 1 to vertex 3 and, (2) from vertex 2 to vertex 0
 * 0 0 0 0
 * 0 0 0 1
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * UseTimeStoneOutputFile name is passed through the command line as args[1]
 * Assume the starting event is vertex 0 (zero)
 * Compute all the possible timelines, output this number to the output file.
 * Compute all the posssible timelines with Expected Utility higher than the EU threshold,
 * output this number to the output file.
 * 
 * Note 2: output these number the in above order, one per line.
 * 
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut:
 *     StdOut.setFile(outputfilename);
 *     //Call StdOut.print() for total number of timelines
 *     //Call StdOut.print() for number of timelines with EU >= threshold EU 
 * 
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/UseTimeStone usetimestone.in usetimestone.out
 * 
 * @author Yashas Ravi
 * 
 */

public class UseTimeStone {

    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java UseTimeStone <INput file> <OUTput file>");
            return;
        }

        // WRITE YOUR CODE HERE

        String inputFile = args[0];
        String outputFile = args[1];

        StdIn.setFile(inputFile);
        StdOut.setFile(outputFile);

        int threshold = StdIn.readInt();
        StdIn.readLine();

        int n = 0;

        n = StdIn.readInt();

        System.out.println( n );

        StdIn.readLine();

        int [] functionalityArray = new int[ n ];
        int[][] adjacencyMatrix = new int[ n ][ n ];

        ArrayList<ArrayList<Integer>> adjacencyList;
        adjacencyList = new ArrayList<ArrayList<Integer>>();

        for ( int i = 0; i < n; i++ ){

            int index = StdIn.readInt();
            int functionality = StdIn.readInt();

            functionalityArray[ index ] = functionality;

            StdIn.readLine();

        }

        for ( int i = 0; i < n; i++ ){

            System.out.println( functionalityArray[ i ]);

        }

        for ( int i = 0; i < n; i++ ){

            adjacencyList.add( new ArrayList<Integer>() );

            for ( int j = 0; j < n; j++ ){

                int weight = StdIn.readInt();

                adjacencyMatrix[ i ][ j ] = weight;

                if ( weight == 1 ){

                    adjacencyList.get( i ).add( j );

                }
            }

            StdIn.readLine();

        }

        System.out.println(  );

        for ( int i = 0; i < n; i++ ){

            for ( int j = 0; j < n; j++ ){

                System.out.print( adjacencyMatrix[ i ][ j ] + "\t" );

            }

            System.out.println(  );

        }

        System.out.println( " n = " + n);

        for ( int i = 0 ; i < n; i++){

            for ( int j = 0; j < adjacencyList.get(i).size(); j++){

                System.out.print( i + " ->" + adjacencyList.get( i ).get( j ) + "\t" );
            }

            System.out.println();
        }

        ArrayList<Integer> vertexInPath = new ArrayList<Integer>();
        ArrayList<String> vertexPath = new ArrayList<String>();
        ArrayList<Integer> pathWeight = new ArrayList<Integer>();

        vertexInPath.add( 0 );
        vertexPath.add( (vertexInPath.get( 0 )).toString() );
        pathWeight.add( functionalityArray[ 0 ] );

        int source = 0;
        int sourcePosition = 0;
        int[] resultArray = new int[ 2 ];

        resultArray[ 0 ] = 0;
        resultArray[ 1 ] = 0;

        printPathFromZero( n , functionalityArray, adjacencyList , resultArray , threshold);

        StdOut.println( resultArray[ 0 ] );
        StdOut.println( resultArray[ 1 ]);
        StdOut.close();

    }




    public static void printPath ( int u , int d , boolean[] isVisited , List<Integer> localPathList , ArrayList<ArrayList<Integer>> adjacencyList , int[] functionalityArray , int[] resultArray , int threshold ){

        if ( u == d ){

            System.out.println( localPathList );

            resultArray[ 0 ]++;

            int EU = calculateEU( localPathList, functionalityArray );

            if ( EU >= threshold ){

                resultArray[ 1 ]++;

            }

            return;

        }

        isVisited[ u ] = true;

        for ( int i: adjacencyList.get( u ) ){

            if ( adjacencyList.get( u ).size() == 0 ){

                continue;

            }

            if ( !isVisited[ i ] ){

                localPathList.add( i );
                printPath( i , d , isVisited , localPathList , adjacencyList , functionalityArray , resultArray , threshold);
                localPathList.remove( Integer.valueOf(i) );

            }
        }

        isVisited[ u ] = false;

    }




    public static int calculateEU( List<Integer> path , int[] functionalityArray ){

        int sum = 0;

        for ( int i: path ){

            sum += functionalityArray[ i ];

        }

        return sum;

    }







    public static void printPathFromZero( int n , int[] functionalityArray , ArrayList<ArrayList<Integer>> adjacencyList , int[] resultArray , int threshold ){

        for ( int i = 0; i < n; i++ ){

            boolean[] isVisited = new boolean[ n ];
            ArrayList<Integer> pathList = new ArrayList<Integer>();

            pathList.add( 0 );
            printPath( 0 , i , isVisited , pathList , adjacencyList , functionalityArray , resultArray , threshold);

        }
    }




    public static boolean vertexInPath ( int v , ArrayList<Integer> vertexInPath ){

        for ( int i: vertexInPath ){

            if ( i == v ){

                return true;

            }
        }

        return false;
        
    }
} 