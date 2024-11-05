package google;

import java.util.*;

// Leetcode Premium: 1152. Analyze User Website Visit Pattern
public class AnalyzeUserWebsiteVisitPattern {
    static class Visit implements Comparable<Visit> {
        String site;
        int time;

        public Visit(String site, int time) {
            this.site = site;
            this.time = time;
        }

        public int compareTo(Visit v) {
            if (v.time == this.time) {
                return 0;
            } else {
                return this.time > v.time ? 1 : -1;
            }
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        // Step 1: Create user to list of visits map(visits can come in jumbled order)
        Map<String, ArrayList<Visit>> userToVisitsMap = new HashMap<>();
        for (int i = 0; i < username.length; ++i) {
            if (!userToVisitsMap.containsKey(username[i])) {
                userToVisitsMap.put(username[i], new ArrayList<Visit>());
            }
            Visit visit = new Visit(website[i], timestamp[i]);
            ArrayList<Visit> tmp = userToVisitsMap.get(username[i]);
            tmp.add(visit);
        }

        // Step 2: Sort the visits of each user in ascending order of their timestamps
        sort(userToVisitsMap);

        // Step 3: Create a map of 3 sequence of visits as key and set of users as values(for eg: home-about-career-->[user1,user2]
        // creating a set to prevent double counts if the same user visits the same 3 sequence again
        Map<String, HashSet<String>> sequences = generateSequences(userToVisitsMap);

        // Step 4: Find the sequence which has max number of users in its subset, also sort it lexicographically
        List<String> threeSequenceAsArray = pickMaxSequenceAndReformat(sequences);
        return threeSequenceAsArray;
    }

    private List<String> pickMaxSequenceAndReformat(Map<String, HashSet<String>> sequences) {
        String maxSequence = "";
        int max = 0;
        for (String sequence : sequences.keySet()) {
            int totalUsers = sequences.get(sequence).size();
            if (totalUsers > max) {
                max = totalUsers;
                maxSequence = sequence;
            } else if (totalUsers == max) {
                if (sequence.compareTo(maxSequence) < 0) {
                    maxSequence = sequence;
                }
            }
        }
        List<String> threeSequenceAsArray = new ArrayList<>();
        String[] splitSequence = maxSequence.split(" ");
        for (String s : splitSequence) {
            threeSequenceAsArray.add(s);
        }
        return threeSequenceAsArray;
    }

    private Map<String, HashSet<String>> generateSequences(Map<String, ArrayList<Visit>> map) {
        Map<String, HashSet<String>> sequences = new HashMap<>();
        for (String user : map.keySet()) {
            ArrayList<Visit> current = map.get(user);
            for (int i = 0; i < current.size() - 2; i++) {
                for (int j = i + 1; j < current.size() - 1; j++) {
                    for (int k = j + 1; k < current.size(); k++) {
                        String threeSequence = current.get(i).site + " " + current.get(j).site + " " + current.get(k).site;
                        if (!sequences.containsKey(threeSequence)) {
                            HashSet<String> tmp = new HashSet<>();
                            tmp.add(user);
                            sequences.put(threeSequence, tmp);
                        } else {
                            HashSet<String> tmp = sequences.get(threeSequence);
                            tmp.add(user);
                            sequences.put(threeSequence, tmp);
                        }
                    }
                }
            }
        }
        return sequences;
    }

    public void sort(Map<String, ArrayList<Visit>> map) {
        for (String user : map.keySet()) {
            ArrayList<Visit> visits = map.get(user);
            Collections.sort(visits);
        }
    }


    public static void main(String[] args) {
        String[] username = {"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
        int[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] website = {"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"};
        AnalyzeUserWebsiteVisitPattern ab = new AnalyzeUserWebsiteVisitPattern();
        List<String> res = ab.mostVisitedPattern(username, timestamp, website);
        res.forEach(e -> System.out.println(e));
    }
}
/*
You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].

A pattern is a list of three websites (not necessarily distinct).

For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.

For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.
Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.

Note that the websites in a pattern do not need to be visited contiguously, they only need to be visited in the order they appeared in the pattern.



Example 1:

Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: The tuples in this example are:
["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
The pattern ("home", "about", "career") has score 2 (joe and mary).
The pattern ("home", "cart", "maps") has score 1 (james).
The pattern ("home", "cart", "home") has score 1 (james).
The pattern ("home", "maps", "home") has score 1 (james).
The pattern ("cart", "maps", "home") has score 1 (james).
The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).
Example 2:

Input: username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
Output: ["a","b","a"]
 */