package module1.examples;

import java.util.HashMap;
import java.util.Map;

public class ExampleHashMap {
    
    public static void main(String[] args){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));

        HashMap<String, String> countries = new HashMap<String, String>();
        countries.put("USA", "United States of America");
        countries.put("SGP", "Singapore");
        countries.put("MYR", "Malaysia");
        countries.put("MEX", "Mexico");
        
        System.out.println(countries.get("USA")); 
        System.out.println(countries.get("SGP"));
        System.out.println(countries.get("MYR"));

        //loop thru countries
        for(Map.Entry<String, String> entry : countries.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        //let's change and override the value for SGP
        countries.put("SGP", "Republic of Singapore");
        System.out.print("\nAfter changing the value for SGP: \n");

        //loop thru countries
        for(Map.Entry<String, String> entry : countries.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        countries.remove("MYR");
        System.out.println("\nAfter removing MYR: \n");
        //loop thru countries
        for(Map.Entry<String, String> entry : countries.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
