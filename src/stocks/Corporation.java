package stocks;

public class Corporation {
	
	private Stock[ ] stock;
	private int quantity;
	
	public Corporation( ) {
		quantity = ( int ) Math.pow( 10, 6 );
		stock = new Stock[ quantity ];
		for ( int k = 0; k < quantity; k++ ) {
			stock[ k ] = new Stock( );
		}
	}

	public Stock[] getStock() {
		return stock;
	}

	public void setStock(Stock[] stock) {
		this.stock = stock;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
