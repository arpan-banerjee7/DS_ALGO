package google.recursion;

import java.util.Stack;

public class BasicCalculator {

    // https://www.youtube.com/watch?v=081AqOuasw0
    public int calculate(String s) {
        // there are these cases
        // number + - (  )

        // now the number can be a single digit or a multi digit number
        // so we have to handle that and form the number
        int sum=0;
        int num=0;
        int sign=1; // -1 or +1
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c= s.charAt(i);
            // number
            if(Character.isDigit(c)){
                // keep on adding the didgit and maintain the position
                // till a char other than digit is hit
                num=num*10+s.charAt(i)-'0';
            }
            if(c=='+'){
                sum+=num*sign;
                sign=1;
                num=0;
            }if(c=='-'){
                sum+=num*sign;
                sign=-1;
                num=0;
            }if(c=='('){
                st.push(sum);
                st.push(sign);
                sum=0;
                num=0;
                sign=1;
            }if(c==')'){
                sum+=num*sign;
                num=0;
                sum*=st.pop();
                sum+=st.pop();
                num=0;
            }
        }
        return sum+=(sign*num);
    }

    public static void main(String[] args) {
        BasicCalculator solver = new BasicCalculator();

        String input = "42+(2+3)-5";
        System.out.println("Input: " + input);
        System.out.println("Output: " + solver.calculate(input));
        System.out.println("Expected: 42");

        System.out.println("\n--- Debug Trace ---");
        // step-by-step trace
        int sum=0, num=0, sign=1;
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<input.length(); i++){
            char c = input.charAt(i);
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }
            if(c=='+'){
                sum += num*sign; sign=1; num=0;
            }if(c=='-'){
                sum += num*sign; sign=-1; num=0;
            }if(c=='('){
                st.push(sum); st.push(sign); sum=0; num=0; sign=1;
            }if(c==')'){
                sum += num*sign; num=0;
                sum *= st.pop();
                sum += st.pop();
            }
            System.out.println("i=" + i + " c='" + c + "' num=" + num + " sum=" + sum + " sign=" + sign + " stack=" + st);
        }
        sum += sign*num;
        System.out.println("Final: sum=" + sum);
    }
}
