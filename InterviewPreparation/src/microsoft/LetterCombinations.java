package microsoft;

import java.util.ArrayList;
import java.util.List;

// 17. Letter Combinations of a Phone Number
// https://practice.geeksforgeeks.org/problems/possible-words-from-phone-digits-1587115620/1/
// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

// Ref: https://www.youtube.com/watch?v=0snEunUacZY Neetcode
// TC = O(n.4^n)  n = number of digits in the string

// 9999- each has 4 possibilities so 4*4*4*4-- so total 4^n*n
public class LetterCombinations {
	private void solve(String[] charMap, String digits, List<String> res, StringBuilder op, int idx){
        if(op.length()==digits.length()){
            res.add(op.toString());
            return;
        }
        
        String letters=charMap[digits.charAt(idx)-'0'];
        for(int i=0;i<letters.length();i++){
            op.append(letters.charAt(i));
            solve(charMap,digits,res,op,idx+1);
            op.setLength(op.length()-1);
        }
    }
    public List<String> letterCombinations(String digits) {
        String[] charMap={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res=new ArrayList<>();
        if(digits.length()!=0){
            solve(charMap,digits,res,new StringBuilder(),0);
        }
        return res;
    }
	public static void main(String[] args) {
		/*
		 * Input: digits = "23" Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
		 */
	}

}
