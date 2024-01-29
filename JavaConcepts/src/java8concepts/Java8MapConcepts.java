package java8concepts;


import java.util.HashMap;
import java.util.Map;

public class Java8MapConcepts {
    //https://stackoverflow.com/questions/48183999/what-is-the-difference-between-putifabsent-and-computeifabsent-in-java-8-map
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        // Using putIfAbsent
        String resultPut = map.putIfAbsent(1, "One");
        System.out.println("Result of putIfAbsent: " + resultPut); // null
        System.out.println("Map after putIfAbsent: " + map); // {1=One}

        // Using computeIfAbsent
        String resultCompute = map.computeIfAbsent(2, key -> "Two");
        System.out.println("Result of computeIfAbsent: " + resultCompute); // Two
        System.out.println("Map after computeIfAbsent: " + map); // {1=One, 2=Two}

        // Demonstrate that putIfAbsent doesn't overwrite existing value
        String resultPut2 = map.putIfAbsent(1, "OneUpdated");
        System.out.println("Result of putIfAbsent (overwrite): " + resultPut2); // One
        System.out.println("Map after putIfAbsent (overwrite): " + map); // {1=One, 2=Two}

        // Demonstrate that computeIfAbsent does not overwrite existing value
        String resultCompute2 = map.computeIfAbsent(2, key -> "TwoUpdated");
        System.out.println("Result of computeIfAbsent (overwrite): " + resultCompute2); // Two
        System.out.println("Map after computeIfAbsent (overwrite): " + map); // {1=One, 2=Two}

        System.out.println("-----------------------------");
        // will not put a null value if the key is absent.
        map.computeIfAbsent(10, key -> null);
        System.out.println("Map after computeIfAbsent (overwrite): " + map); // {1=One, 2=Two, 11=null}

        // will put the value if the key is absent, even if the value is null.
        map.putIfAbsent(11, null);
        System.out.println("Map after computeIfAbsent (overwrite): " + map); // {1=One, 2=Two, 11=null}


    }

}
