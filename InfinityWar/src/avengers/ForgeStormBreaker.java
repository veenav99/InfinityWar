package avengers;
/**
 * Given the 2D array where the values are the flux intensity data of the 
 * Neutron Star, calculate the total flux that Thor has to endure. 
 */

public class ForgeStormBreaker {
	public static void main ( String [] args ) {
        
        if ( args.length < 2 ) {
            StdOut.println( "Execute: java ForgeStormBreaker <INput file> <OUTput file>" );
            return;
        }

        // read file names from command line
		
        String forgeStormbreakerInputFile = args[ 0 ];
        String forgeStormbreakerOutputFile = args[ 1 ];

	// Set the input file
		
        StdIn.setFile( forgeStormbreakerInputFile );
        
        int maxNumberOfRows = StdIn.readInt();
        int maxNumberOfColums = StdIn.readInt();

        int flux = 0;

        // Calculate the flux

       int rows = 0;
       
        while ( rows < maxNumberOfRows ){

            rows++;

            int col = 0;

            while ( col < maxNumberOfColums){
                flux += StdIn.readInt();
                col++;
            }
        }
        
        // Set the output file
        StdOut.setFile(forgeStormbreakerOutputFile);
       
        StdOut.print(flux);
    }
}
