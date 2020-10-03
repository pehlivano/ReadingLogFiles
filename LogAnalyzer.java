
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>(); 
    }
        
    public void readFile(String filename) {
        // Create a file resource and to iterate over all the lines in file.
        // For each line, create a LogEntry and store it in the ArrayList.
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
             LogEntry currLog = WebLogParser.parseEntry(line);
             records.add(currLog);
        }
    }
        
    public void printAll() {
        // Iterate ArrayList and print each LogEntry.
        for (LogEntry le : records) {
             System.out.println(le);
        }
    }
     
    public int countUniqueIps() {
        //Create new ArrayList for unique IP adress
        ArrayList<String> IpAddress = new ArrayList<String>();
        for(LogEntry le : records) {
            // Get Ip Adress of current LogEntry.
            String currIpAddress = le.getIpAddress();
            // If list doesn't contain the current Ip, add it to the list.
            if(!IpAddress.contains(currIpAddress)) {
                IpAddress.add(currIpAddress);
            }
        }
        return IpAddress.size();
    }
    
    public void printAllHigherThanNum(int num) {
        //Print log entries that status code greater than num
        for(LogEntry le : records) {
            int currStatus = le.getStatusCode();
            if(currStatus > num) {
                System.out.println(le);
            }
        } 
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        // Create a new ArrayList for unique IP adresses in given day.
        ArrayList<String> list = new ArrayList<String>();
        for(LogEntry le : records){
            String currDate = le.getAccessTime().toString();
            String currIP = le.getIpAddress();
            //If current date matches with given date(someday) and not in the list, add its IP address to list.
            if(currDate.contains(someday) && !list.contains(currIP)){
                list.add(currIP);
            }
        }
        return list;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> list = new ArrayList<String>();
        int count = 0;
        for(LogEntry le : records) {
            int currStatusCode = le.getStatusCode();
            String currIP = le.getIpAddress();
            //if current status code is in range, add its IP address to list and count by 1.
            if((currStatusCode>= low && currStatusCode <= high) && !list.contains(currIP)) {
                list.add(currIP);
                count++;
            }
        }
        return count;
    }
    
    public HashMap<String,Integer> countVisitsPerIP() {
        // Create a HashMap that maps an IP address to the number of times that IP address appears in records.
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for(LogEntry le : records) {
            String currIp = le.getIpAddress();
            if(!counts.containsKey(currIp)) {
                counts.put(currIp,1);
            }
            else{
                counts.put(currIp,counts.get(currIp)+1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer> map) {
        // Return the maximum number of visits to this website by a single IP address.
        int max = -1;
        for (Integer v : map.values()) {
            if(v > max){
                max = v;
            }
        } 
        return max;    
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map) {
        // Create an ArrayList to store the IPs that has most visited number
        ArrayList<String> mostVisitedIPs = new ArrayList<String>();
        // Determine the max number of map
        int maxNumber = mostNumberVisitsByIP(map);
        for(String s : map.keySet()) {
            // If current IP equals the max number, add it to the list
            if(map.get(s) == maxNumber){
                mostVisitedIPs.add(s);
            }
        }
        System.out.println("Visiting Number = " + maxNumber);
        return mostVisitedIPs;
    }
    
    public HashMap<String,ArrayList<String>> iPsForDays() {
        
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        //ArrayList<String> IpAddress = new ArrayList<String>();
        for(LogEntry le : records){
            //The Day must be MMM DD format, so convert date to string and substring the log entry
            String currDate = le.getAccessTime().toString().substring(4,10);
            String currIP = le.getIpAddress();
            //if map not contains the current date, add its date and ip addresses to map.
            if(!map.containsKey(currDate)) {
                //Create an Array List to store IP addresses for current day.
                ArrayList<String> IpAddress = new ArrayList<String>();
                IpAddress.add(currIP);
                map.put(currDate,IpAddress);
            }
            //if contains, add current IP address to the array list. (IP address not unique, can repeat)
            else{
                map.get(currDate).add(le.getIpAddress());
            }
        }
        return map;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        // Return the day that has the most IP address visits.
        int max = -1;
        String date = null;
        for(String currDate : map.keySet()) {
            // Get current length of ArrayList for current date.
            int currSize = map.get(currDate).size();
            // If it is bigger than max, set it as a max and update the date.
            if(currSize > max) {
                max = currSize;
                date = currDate;
            }
        }
        return date;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String date) {
        // Return an ArrayList<String> of IP addresses that had the most accesses on the given day.
        ArrayList<String> IPList = map.get(date);
        HashMap<String, Integer> counts = new HashMap<>();
        for(String s : IPList) {
            if(!counts.containsKey(s)) {
                counts.put(s,1);
            }
            else{
                counts.put(s,counts.get(s)+1);
            }
        }
        return iPsMostVisits(counts);
    }  
    
}
