package Model;

public class Orderlist {
	
	private String food; // 제품
	private int food_num; // 재고량
	private int price; // 가격
	
	public Orderlist(String food, int food_num, int price) {
		this.food = food;
		this.food_num = food_num;
		this.price = price;
	}

	
	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public int getFood_num() {
		return food_num;
	}

	public void setFood_num(int food_num) {
		this.food_num = food_num;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public Orderlist(String food, String food_num, String price) {
		this.food = food;
		this.food_num = Integer.parseInt(food_num);
		this.price = Integer.parseInt(price);
	}
	
	
}
