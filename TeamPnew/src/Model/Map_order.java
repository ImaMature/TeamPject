package Model;

public class Map_order {
	private String product;
	private int count;
	private int total;
	
	public Map_order() {
		
	}
	
	public Map_order(String product, int count, int total) {
		this.product = product;
		this.count = count;
		this.total = total;
	}


	public String getProduct() {
		return product;
	}



	public void setProduct(String product) {
		this.product = product;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	
}
