
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int count = la.countUniqueIps();
        System.out.println("Unique IP Address number is = " + count);
        
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int num = 400;
        la.printAllHigherThanNum(num);
    }
    
    public void testUniqueIpVisitsOnDay() {
        ArrayList<String> list = new ArrayList<String>();
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        list = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println(list.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int res = la.countUniqueIPsInRange(400, 499);
        System.out.println(res);
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int res = la.mostNumberVisitsByIP(counts);
        System.out.println(res);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> result = la.iPsMostVisits(counts);
        System.out.println(result);
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("");
        HashMap<String,ArrayList<String>> result = la.iPsForDays();
        for (String s: result.keySet()) {
            System.out.println(s+" maps to"+"\t"+result.get(s));
        }
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("");
        HashMap<String,ArrayList<String>> result = la.iPsForDays();
        String date = la.dayWithMostIPVisits(result);
        System.out.println(date);      
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("");
        HashMap<String,ArrayList<String>> result = la.iPsForDays();
        ArrayList<String> list = la.iPsWithMostVisitsOnDay(result,"Sep 30");
        System.out.println(list);
    }
}
