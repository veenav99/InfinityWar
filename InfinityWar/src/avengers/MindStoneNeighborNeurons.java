package avengers;

/**
 * Given a Set of Edges representing Vision's Neural Network, identify all of the vertices that connect to the Mind Stone. 
 * List the names of these neurons in the output file
 */


public class MindStoneNeighborNeurons {
    
    public static void main (String [] args) {
        
    	if ( args.length < 2 ) {
            StdOut.println( "Execute: java MindStoneNeighborNeurons <INput file> <OUTput file>" );
            return;
        }

        String inputFile = args[ 0 ];
        String outputFile = args[ 1 ];
        StdIn.setFile( inputFile );

        int n = StdIn.readInt();
        String[] neurons = new String[ n ];

        for ( int i = 0; i < n; i++ ){

            neurons[ i ] = StdIn.readString();

        }

        int m = StdIn.readInt();

        String[] fromVertex = new String[ m ];
        String[] toVertex = new String[ m ];

        for ( int i = 0; i < m; i++ ){

            StdIn.readLine();

            fromVertex[ i ] = StdIn.readString();
            toVertex[ i ] = StdIn.readString();

        }

        boolean findMindStone = false;
        String mindStone = "empty";

        for ( int i = 0; i < neurons.length; i++ ) {

            int j = 0;

            for ( j = 0; j < fromVertex.length; j++ ){

                if ( neurons[ i ].equals( fromVertex[ j ] ) ) {

                    break;

                }
            }

            if ( j == fromVertex.length ){

                mindStone = neurons[ i ];
                break;
            }
        }

        System.out.println( "mindStone =" + mindStone );

        StdOut.setFile( outputFile );

        for ( int i = 0; i < toVertex.length; i++ ){

            if ( mindStone.equals( toVertex [ i ] ) ) {

                StdOut.println( fromVertex[ i ] );

            }
        }

        StdOut.close();
        
    }
}
