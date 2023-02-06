package stocks;

public class Simulator {
	
	public Simulator( ) {
		
	}
	
	private String generateRandomTicket( ) {
		String ticket = "";
		boolean[ ] used = new boolean[ 26 ];
		for ( int k = 0; k < 26; k++ ) {
			used[ k ] = false;
		}
		for ( int i = 0; i < 4; i++ ) {
			int random = ( int ) ( 26 * Math.random( ) );
			while ( used[ random ] ) {
				random = ( int ) ( 26 * Math.random( ) );
			}
			used[ random ] = true;
			ticket += ( char ) ( 'A' + random );
		}
		int randomNumber = 1 + ( int ) ( 9 * Math.random( ) );
		ticket += String.valueOf( randomNumber );
		return ticket;
	}
	
	private void run( ) {
		final int STOCKS = 50;
		final int MONTHS = 120;
		Stock[ ] stock = new Stock[ STOCKS ];
		double[ ][ ] table = new double[ STOCKS ][ MONTHS ];
		for ( int i = 0; i < STOCKS; i++ ) {
			table[ i ][ 0 ] = 10.00;
		}
		for ( int i = 0; i < STOCKS; i++ ) {
			for ( int j = 1; j < MONTHS; j++ ) {
				int signal = ( int ) ( 2 * Math.random( ) );
				signal = signal == 0 ? 1 : -1;
				double variation = 100.00 * Math.random( );
				variation = Math.round( variation );
				variation = variation / 100.00;
				table[ i ][ j ] = table[ i ][ j - 1 ] + ( double ) ( signal * variation );
			}
		}
		for ( int i = 0; i < STOCKS; i++ ) {
			stock[ i ] = new Stock( );
			stock[ i ].setTicket( generateRandomTicket( ) );
			System.out.print( stock[ i ].getTicket( ) );
			for ( int j = 0; j < MONTHS; j++ ) {
				table[ i ][ j ] = 100.00 * table[ i ][ j ];
				table[ i ][ j ] = Math.round( table[ i ][ j ] );
				table[ i ][ j ] = table[ i ][ j ] / 100.00; 
				System.out.print( "," + table[ i ][ j ] );
			}
			System.out.println();
		}
		String bestTicket = "", worstTicket = "";
		double minimumValue = 10.0, maximumValue = 10.0;
		for ( int i = 0; i < STOCKS; i++ ) {
			if ( table[ i ][ MONTHS - 1 ] < minimumValue ) {
				worstTicket = stock[ i ].getTicket( );
				minimumValue = table[ i ][ MONTHS - 1 ];
			}
			if ( table[ i ][ MONTHS - 1 ] > maximumValue ) {
				bestTicket = stock[ i ].getTicket( );
				maximumValue = table[ i ][ MONTHS - 1 ];
			}
		}
		System.out.println( "worst ticket = " + worstTicket + ", price = " + minimumValue );
		System.out.println( "best ticket = " + bestTicket + ", price = " + maximumValue );
	}
	
	public static void main( String[ ] args ) {
		new Simulator( ).run( );
	}

}
