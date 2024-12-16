package google;

import java.util.*;

public class CalculateScoreOfDomains {


    public static void main(String[] args) {
        // Example input
        String[] domains = {
                "test.mydomain.com 10",
                "mail.test.mydomain.com 15",
                "test.com -10",
                "com 20",
                "mydomain.com 5",
                "www.mydomain.com 10",
                "mail.test.com 10",
                "www.test.com -5"
        };

        // Parse input and build domain scores map
        Map<String, Integer> domainScores = new HashMap<>();
        for (String entry : domains) {
            String[] parts = entry.split(" ");
            String domain = parts[0];
            int score = Integer.parseInt(parts[1]);
            domainScores.put(domain, domainScores.getOrDefault(domain, 0) + score);
        }

        // Create a list of domains sorted by specificity (number of labels)
        List<String> sortedDomains = new ArrayList<>(domainScores.keySet());
        sortedDomains.sort((a, b) -> {
            int countA = a.split("\\.").length;
            int countB = b.split("\\.").length;
            return countB - countA; // Descending order
        });

        // Map to store absolute parent domains and their total scores
        Map<String, Integer> absoluteParents = new LinkedHashMap<>();
        Set<String> parents = new HashSet<>();

        // Memoization cache for total scores
        Map<String, Integer> totalScoreCache = new HashMap<>();

        for (String domain : sortedDomains) {
            if (!isSubdomainOfAny(domain, parents)) {
                // Calculate total score for this domain with memoization
                int totalScore = calculateTotalScore(domain, domainScores, totalScoreCache);
                absoluteParents.put(domain, totalScore);
                parents.add(domain);
            }
        }

        // Print only the absolute parent domains
        absoluteParents.forEach((domain, score) -> {
            System.out.println(domain + " " + score);
        });
    }

    // Corrected isSubdomainOfAny method
    private static boolean isSubdomainOfAny(String domain, Set<String> parents) {
        for (String parent : parents) {
            if (isSubdomainOf(domain, parent)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if 'domain' is a subdomain of 'parent'
    private static boolean isSubdomainOf(String domain, String parent) {
        if (domain.equals(parent)) {
            return false; // A domain is not a subdomain of itself
        }
        return domain.endsWith("." + parent);
    }

    // Optimized calculateTotalScore method with memoization
    private static int calculateTotalScore(String domain, Map<String, Integer> domainScores, Map<String, Integer> totalScoreCache) {
        if (totalScoreCache.containsKey(domain)) {
            return totalScoreCache.get(domain);
        }

        int totalScore = domainScores.getOrDefault(domain, 0);
        String[] parts = domain.split("\\.");
        if (parts.length > 1) {
            String parentDomain = String.join(".", Arrays.copyOfRange(parts, 1, parts.length));
            totalScore += calculateTotalScore(parentDomain, domainScores, totalScoreCache);
        }

        totalScoreCache.put(domain, totalScore);
        return totalScore;
    }
}