package intuit;

/*
The code you provided calculates P by iterating through the given string and 
tracking the maximum and minimum values encountered based on the signs ('<' and '>'). 
The valid range of positive integers required to construct a valid sequence is
determined by the difference between the maximum and minimum values encountered, 
plus 1. In summary, the intuition behind calculating P is to ensure that you have a 
wide enough range of positive integers available to construct a valid sequence that 
satisfies the given signs.
 */
public class ValidRange {
	public int getValidRange(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int number = 0;
		int max = 0, min = 0;
		for (char c : s.toCharArray()) {
			if (c == '>') {
				number++;
				if (number > max) {
					max = number;
				}
			} else if (c == '<') {
				number--;
				if (number < min) {
					min = number;
				}
			}
		}
		return max - min + 1; // 1 is for the first element which was ignored before.
	}

	public static void main(String[] args) {
		ValidRange obj = new ValidRange();
		System.out.println("<<<" + " : " + obj.getValidRange("<<<"));
		System.out.println("<=>" + " : " + obj.getValidRange("<=>"));
		System.out.println(">><>" + " : " + obj.getValidRange(">><>"));
		System.out.println("<<<" + " : " + obj.getValidRange("<<<"));
		System.out.println("<>><<<>>=<<<<>" + " : " + obj.getValidRange("<>><<<>>=<<<<>"));
		System.out.println("<<====>>>" + " : " + obj.getValidRange("<<====>>>"));
		System.out.println(">><<<<<" + " : " + obj.getValidRange(">><<<<<"));
	}
}
