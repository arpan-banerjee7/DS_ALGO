/*
package google.recursion;


import java.util.ArrayList;
import java.util.*;

public class MiniParser {
   int idx;
   private void tokenize(String s, List<String> tokens){
       int n = s.length();
       for(int i=0;i<n;i++){
           char ch = s.charAt(i);
           if(ch=='[' || ch==']'){
               tokens.add(String.valueOf(ch));
           }else if(Character.isDigit(ch) || ch=='-'){
               int number=0;
               int sign=1;
               if(ch=='-'){
                   sign=-1;
                   i++;
               }
               while(i<n && Character.isDigit(s.charAt(i))){
                   number=number*10+(s.charAt(i)-'0');
                   i++;
               }
               i--;
               tokens.add(String.valueOf(sign * number));
           }
       }
   }

   private NestedInteger solve(List<String> tokens){
       String ch = tokens.get(idx);
       char firstChar = ch.charAt(0);

       //base case
       if (Character.isDigit(firstChar) || (firstChar == '-' && ch.length() > 1)) {
           idx++;
           // It's an integer!
           int number = Integer.parseInt(ch);
           NestedInteger value = new NestedInteger();
           value.setInteger(number);
           return value;
       }

       NestedInteger ni = null ;
       if(ch.equals("[")){
           idx++;
           ni = new NestedInteger();
           while(idx<tokens.size() && !tokens.get(idx).equals("]")){
               ni.add(solve(tokens));
           }
           idx++;
       }
       return ni;
   }
   public NestedInteger deserialize(String s) {
       List<String> tokens = new ArrayList<>();
       System.out.println(tokens);
       tokenize(s, tokens);
       idx=0;
       return solve(tokens);
   }

}
*/
