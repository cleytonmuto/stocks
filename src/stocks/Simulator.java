package stocks;

import java.util.ArrayList;

/**
 * @author Cleyton
 * 
 */
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
		int randomNumber = ( int ) ( 3 * Math.random( ) );
		switch (randomNumber) {
			case 0: ticket += "3"; break;
			case 1: ticket += "4"; break;
			case 2: ticket += "11"; break;
		}
		return ticket;
	}
	
	private void run( ) {
		final int STOCKS = 100;
		final int MONTHS = 240;
		Stock[ ] stock = new Stock[ STOCKS ];
		double[ ][ ] table = new double[ STOCKS ][ MONTHS ];
		ArrayList<String> report = new ArrayList<String>();
		for ( int i = 0; i < STOCKS; i++ ) {
			table[ i ][ 0 ] = 10.00 + 90.00 * Math.random();
		}
		for ( int i = 0; i < STOCKS; i++ ) {
			for ( int j = 1; j < MONTHS; j++ ) {
				int signal = ( int ) ( 2 * Math.random( ) );
				signal = signal == 0 ? 1 : -1;
				double variation = 100 + 200.00 * Math.random( );
				variation = Math.round( variation );
				variation = variation / 100.00;
				table[ i ][ j ] = table[ i ][ j - 1 ] + ( double ) ( signal * variation );
				if (table[ i ][ j ] < 0.00) {
					table[ i ][ j ] = 0.00;
				}
			}
		}
		for ( int i = 0; i < STOCKS; i++ ) {
			stock[ i ] = new Stock( );
			stock[ i ].setTicket( generateRandomTicket( ) );
			String line = stock[ i ].getTicket( );
			for ( int j = 0; j < MONTHS; j++ ) {
				table[ i ][ j ] = 100.00 * table[ i ][ j ];
				table[ i ][ j ] = Math.round( table[ i ][ j ] );
				table[ i ][ j ] = table[ i ][ j ] / 100.00; 
				line += "," + table[ i ][ j ];
			}
			report.add(line);
		}
		String bestTicket = "", worstTicket = "";
		double minimumValue = 10.0, maximumValue = 10.0;
		int bestIndex = 0, worstIndex = 0;
		for ( int i = 0; i < STOCKS; i++ ) {
			if ( table[ i ][ MONTHS - 1 ] < minimumValue ) {
				worstTicket = stock[ i ].getTicket( );
				minimumValue = table[ i ][ MONTHS - 1 ];
				worstIndex = i;
			}
			if ( table[ i ][ MONTHS - 1 ] > maximumValue ) {
				bestTicket = stock[ i ].getTicket( );
				maximumValue = table[ i ][ MONTHS - 1 ];
				bestIndex = i;
			}
		}
		Utils.save("report.csv", report);
		System.out.println( "cheapest ticket = " + worstTicket + ", initial price = " + table[ worstIndex ][ 0 ] + ", final price = " + table[ worstIndex ][ MONTHS - 1 ] );
		System.out.println( "most expensive ticket = " + bestTicket + ", initial price = " + table[ bestIndex ][ 0 ] + ", final price = " + table[ bestIndex ][ MONTHS - 1 ] );
		String bestProfit = "", worstProfit = "";
		double maximumProfit = 0.0, minimumProfit = 1000.0; 
		for ( int i = 0; i < STOCKS; i++ ) {
			if (table[ i ][ MONTHS - 1 ] > table[ i ][ 0 ]) {
				double diff = table[ i ][ MONTHS - 1 ] - table[ i ][ 0 ];
				double profit = 100.00 * diff / table[ i ][ 0 ];
				if (profit > maximumProfit) {
					maximumProfit = profit;
					bestProfit = stock[ i ].getTicket( );
					bestIndex = i;
				}
			}
			if (table[ i ][ MONTHS - 1 ] < table[ i ][ 0 ]) {
				double diff = table[ i ][ 0 ] - table[ i ][ MONTHS - 1 ];
				double profit = - 100.00 * diff / table[ i ][ 0 ];
				if (profit < minimumProfit) {
					minimumProfit = profit;
					worstProfit = stock[ i ].getTicket( );
					worstIndex = i;
				}
			}
		}
		minimumProfit = 100.00 * minimumProfit;
		minimumProfit = Math.ceil(minimumProfit);
		minimumProfit = minimumProfit / 100.00;
		maximumProfit = 100.00 * maximumProfit;
		maximumProfit = Math.floor(maximumProfit);
		maximumProfit = maximumProfit / 100.00;
		System.out.println( "worst profit = " + worstProfit + ", lost = " + minimumProfit + "%, initial price = " + table[ worstIndex ][ 0 ] + ", final price = " + table[ worstIndex ][ MONTHS - 1 ] );
		System.out.println( "best profit = " + bestProfit + ", gained = " + maximumProfit + "%, initial price = " + table[ bestIndex ][ 0 ] + ", final price = " + table[ bestIndex ][ MONTHS - 1 ] );
	}
	
	public static void main( String[ ] args ) {
		new Simulator( ).run( );
	}

}
