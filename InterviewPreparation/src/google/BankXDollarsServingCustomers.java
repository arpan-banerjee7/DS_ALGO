package google;
//https://leetcode.com/discuss/interview-question/4672208/Google-EliminationorPhone-Interview-Round-2024/

public class BankXDollarsServingCustomers {

    public static int getMaxCustomersServedBrute(int initialFunds, int[] transactions) {
        int maxServed = 0; // Maximum customers served

        for (int start = 0; start < transactions.length; start++) {
            int currentFunds = initialFunds;
            int currentServed = 0;

            for (int i = start; i < transactions.length; i++) {
                if (transactions[i] > 0) { // Withdrawal
                    if (currentFunds >= transactions[i]) {
                        currentFunds -= transactions[i];
                        currentServed++;
                    } else {
                        break; // Stop if funds are insufficient
                    }
                } else { // Deposit
                    currentFunds += Math.abs(transactions[i]);
                    currentServed++;
                }
            }

            maxServed = Math.max(maxServed, currentServed);
        }

        return maxServed;
    }

    public static int getMaxCustomersServed(int initialFunds, int[] transactions) {
        int maxServed = 0; // Maximum customers served
        int currentFunds = initialFunds; // Current funds of the bank
        int count = 0; // Current number of customers served

        for (int i = 0; i < transactions.length; i++) {
            if ((currentFunds - transactions[i]) < 0) {
                currentFunds = initialFunds;
                count = 0;
                if (currentFunds - transactions[i] >= 0) {
                    i--;
                }

            } else {
                currentFunds -= transactions[i];
                count++;
                maxServed = Math.max(count, maxServed);
            }
        }

        return maxServed;
    }

    public static void main(String[] args) {
        int initialFunds1 = 500;
        int[] transactions1 = {300, 300, -200, -400, 500, -30, 950, -1, 1, 6543};

        // Call the function to find the maximum number of customers served
        int maxServed1 = getMaxCustomersServed(initialFunds1, transactions1);
        int maxServed1Brute = getMaxCustomersServedBrute(initialFunds1, transactions1);

        // Print the result
        System.out.println("Maximum number of customers the bank can serve: " + maxServed1);
        System.out.println("Maximum number of customers the bank can serve: " + maxServed1Brute);

        int initialFunds2 = 4;
        int[] transactions2 = {3, 1, 1, 1, 1, 1};

        int maxServed2 = getMaxCustomersServed(initialFunds2, transactions2);
        int maxServed1Brute2 = getMaxCustomersServedBrute(initialFunds2, transactions2);

        // Print the result
        System.out.println("Maximum number of customers the bank can serve: " + maxServed2);
        System.out.println("Maximum number of customers the bank can serve: " + maxServed1Brute2);


        int initialFunds3 = 0;
        int[] transactions3 = {-2, -6, 200, -400, 100, 30, 20, 50, -1, 1, 6543};
        int maxServed3 = getMaxCustomersServed(initialFunds3, transactions3);
        int maxServed1Brute3 = getMaxCustomersServedBrute(initialFunds3, transactions3);

        // Print the result
        System.out.println("Maximum number of customers the bank can serve: " + maxServed3);
        System.out.println("Maximum number of customers the bank can serve: " + maxServed1Brute3);


    }


}


