package solid.principles;

/*If class B is a subtype of class A, then we should be able to replace object A
with B without breaking the behavior of the program. Subclass should extend the
capability of the parent class not narrow it down.*/

public class LiskovSubstitutionPrinciple {

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

	}

	public static class BeverageItem extends MenuItem {
		public BeverageItem(int price, String name, String description) {
			super(price, name, description);
		}

		// it has discounted price
		public double getPriceWithDiscount(int discountPercent) {
			return this.price - (discountPercent * 0.1 * this.price);
		}
	}

	public static void printItemPrice(MenuItem item) {
		// here it violates liskovs principle as we have to check the intance before
		// calling the gePrice method
		if (item instanceof BeverageItem) {
			System.out.println("Price of beverage item");
			System.out.println(((BeverageItem) item).getPriceWithDiscount(10));
		} else {
			System.out.println("Price of menu item");
			System.out.println(item.getPrice());
		}
	}

	public static void main(String[] args) {
		MenuItem menuItem = new MenuItem(100, "Bread", "Wheat flour");
		BeverageItem bItem = new BeverageItem(60, "Coke", "Cold drinks");

		printItemPrice(menuItem);
		printItemPrice(bItem);
	}

}
