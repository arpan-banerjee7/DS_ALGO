package problems;

//https://codingbabu.com/convert-the-variable-from-c-to-java-and-vice-versa/
/*
* 2. N number of student and M number of subject
Teacher Mr. Johnson want to check and skip the subject mark which has minimum average marks.
Print the result of each student with skipping the mark of minimum avg marks
let take example
N = 4 student
M = 5 subject
student1 = {5, 8, 2, 4, 8}
student2 = {3, 6, 3, 7, 2}
student3 = {4, 8, 1, 7, 4}
student4 = {8, 8, 4, 4, 6}

here in this case subject number 3 has minimum avg marks (M1 avg = 5, M2 avg = 7.5, M3 = 2.5, M4 =5.5, M5 = 5 ) so need to skip from result and print the result skipping the minimum marks subject.
*/
public class ConvertCppToJavaAndVV {

	public static String convertVariable(String input) {
		StringBuilder res = new StringBuilder();
		int n = input.length();

		// cpp variable
		if (input.indexOf('_') != -1) {
			String[] cppArr = input.split("_");
			res.append(cppArr[0]);
			for (int i = 1; i < cppArr.length; i++) {
				res.append(Character.toUpperCase(cppArr[i].charAt(0)) + cppArr[i].substring(1));
			}
		}
		// java variable
		else {
			for (int i = 0; i < n; i++) {
				if (Character.isUpperCase(input.charAt(i))) {
					res.append("_" + Character.toLowerCase(input.charAt(i)));
				} else {
					res.append(input.charAt(i));
				}
			}
		}

		return res.toString();
	}

	public static void main(String[] args) {
		String cpp = "this_is_a_variable";
		String java = "thisIsAVariable";
		System.out.println(convertVariable(cpp));
		System.out.println(convertVariable(java));
	}

}