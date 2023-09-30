package solid.principles;

public class LiskovSubstitutionPrincipleCorrection {

	public static class MenuItem {
		public int price;
		public String name;
		public String description;

		public MenuItem(int price, String name, String description) {
			this.price = price;
			this.name = name;
			this.description = description;
		}

		public double getPrice() {
			return this.price;
		}

		public double getDiscount() {
			return 0;
		}
	}

	public static class BeverageItem extends MenuItem {
		public BeverageItem(int price, String name, String description) {
			super(price, name, description);
		}

		public double getPrice() {
			return this.price - this.getDiscount();
		}

		// it has discounted price
		public double getPriceWithDiscount() {
			int discountPercent = 10;
			return this.price - (discountPercent * 0.1 * this.price);
		}
	}

	public static void printItemPrice(MenuItem item) {
		// here we can simply call the getPrice
		System.out.println(item.getPrice());

	}

	public static void main(String[] args) {
		MenuItem menuItem = new MenuItem(100, "Bread", "Wheat flour");
		BeverageItem bItem = new BeverageItem(60, "Coke", "Cold drinks");

		printItemPrice(menuItem);
		printItemPrice(bItem);
	}

}
