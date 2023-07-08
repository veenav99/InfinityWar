package avengers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a starting event and an Adjacency Matrix representing a graph of all possible events once Thanos 
 * arrives on Titan, determine the total possible number of timelines that could occur AND the number of 
 * timelines with a total Expected Utility (EU) at least the threshold value
 */

public class UseTimeStone {

    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java UseTimeStone <INput file> <OUTput file>");
            return;
        }

        String inputFile = args[ 0 ];
        String outputFile = args[ 1 ];

        StdIn.setFile( inputFile );
        StdOut.setFile( outputFile );

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


    public static void printPath ( int u , int d , boolean[] isVisited , List<Integer> localPathList , ArrayList<ArrayList<Integer>> adjacencyList , 
                                  int[] functionalityArray , int[] resultArray , int threshold ){

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



    public static void printPathFromZero( int n , int[] functionalityArray , ArrayList<ArrayList<Integer>> adjacencyList , 
                                         int[] resultArray , int threshold ){

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
