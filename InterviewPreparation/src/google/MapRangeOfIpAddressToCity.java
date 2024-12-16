package google;

import java.util.*;
// https://leetcode.com/discuss/interview-question/2058209/google-phone-screening-mountain-view-ca
/*
I have a file with the following format each line: startIP, endIP, cityName.
Question: Write a function that takes as input an IP address and outputs its associated cityName.
Example: File format: startIP, endIP, cityName 1.0.1.1, 1.0.1.10, NYC 1.0.1.20, 1.0.1.30, SF ...
If the input is 1.0.1.9, the output should be NYC.
 Write code for the function.

 */
//https://stackoverflow.com/questions/11548273/convert-an-ip-address-to-its-decimal-equivalent-in-java
public class MapRangeOfIpAddressToCity {
    static class IPRange {
        long endIP;       // The end IP as an integer
        String cityName;  // Associated city name

        IPRange(long endIP, String cityName) {
            this.endIP = endIP;
            this.cityName = cityName;
        }
    }

    private final TreeMap<Long, IPRange> ipRanges = new TreeMap<>();

    // Adds an IP range to the TreeMap
    public void addIPRange(String startIP, String endIP, String cityName) {
        long start = ipToLong(startIP);
        long end = ipToLong(endIP);
        ipRanges.put(start, new IPRange(end, cityName));
    }

    // Looks up the city name for a given IP
    public String findCityForIP(String ip) {
        long ipAsLong = ipToLong(ip);
        Map.Entry<Long, IPRange> entry = ipRanges.floorEntry(ipAsLong);
        ipRanges.ceilingEntry(ipAsLong); // for knowledge

        if (entry != null) {
            IPRange range = entry.getValue();
            if (ipAsLong <= range.endIP) {
                return range.cityName;
            }
        }
        return null; // No city found for this IP
    }

    // Converts an IP address to a long integer
    private long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        long result = 0;

        for (int i = 0; i < parts.length; i++) {
            int power = 3 - i; // The highest power is for the first octet (256^3)
            result += (Integer.parseInt(parts[i]) % 256) * Math.pow(256, power); // %256 is a defensive coding in case an invalid ip address comes
        }

        return result;
    }
//    private long ipToLong(String ip) {
//        String[] parts = ip.split("\\.");
//        long result = 0;
//        for (String part : parts) {
//            result = result * 256 + Integer.parseInt(part);
//        }
//        return result;
//    }

    public static void main(String[] args) {
        MapRangeOfIpAddressToCity ipLookup = new MapRangeOfIpAddressToCity();

        // Adding IP ranges
        ipLookup.addIPRange("1.0.1.1", "1.0.1.10", "NYC");
        ipLookup.addIPRange("1.0.1.20", "1.0.1.30", "SF");

        // Querying for IPs
        System.out.println(ipLookup.findCityForIP("1.0.1.9"));  // Output: NYC
        System.out.println(ipLookup.findCityForIP("1.0.1.25")); // Output: SF
        System.out.println(ipLookup.findCityForIP("1.0.1.15")); // Output: null
    }
}

/*
Why Use % 256?
IP Address Octet Validity:

An IPv4 address is composed of 4 octets separated by dots, e.g., 192.168.1.10.
Each octet represents an 8-bit number, which must fall within the range 0-255.
Prevent Overflow or Invalid Input:

If the input IP contains values outside the range 0-255 due to invalid input (e.g., 192.300.1.10), the % 256 operation ensures that the resulting value is within the valid range for each octet.
For example:
If an octet is 300, 300 % 256 = 44. This wraps the value to fit within the 8-bit range.
Without this mod operation, the calculation might yield an invalid or unintended value.
 */