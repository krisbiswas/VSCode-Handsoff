package Java_Projects.Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1{

    static ArrayList<String> splitComma(StringBuilder json){
        StringBuilder buff = new StringBuilder();
        ArrayList<String> kvs = new ArrayList<>();
        boolean isInArray = false;
        boolean isInObj = false;
        for(int i=0;i<json.length();i++){
            // Possibilty of comma in string value is not considered
            if(isInArray || isInObj){
                // pointer is in json value
                if(isInArray){
                    if(json.charAt(i) == ']'){
                        isInArray = false;
                    }
                    buff.append(json.charAt(i));
                }else{
                    if(json.charAt(i) == '}'){
                        isInObj = false;
                    }
                    buff.append(json.charAt(i));
                }
            }else{
                if (json.charAt(i) == '['){
                    isInArray = true;
                    buff.append(json.charAt(i));
                }else if(json.charAt(i) == '{'){
                    isInObj = true;
                    buff.append(json.charAt(i));
                }else if(json.charAt(i) == ','){
                    kvs.add(buff.toString().strip());
                    buff.replace(0, buff.length(), "");
                }else{
                    buff.append(json.charAt(i));
                }
            }
        }
        kvs.add(buff.toString().strip());
        return kvs;
    }

    static List<String> splitColon(String kv){
        int separatorColonAt = kv.indexOf(':', 0);
        String key = kv.substring(0, separatorColonAt).strip();
        key = key.substring(1,key.length()-1).strip();
        String value = kv.substring(separatorColonAt+1).strip();
        if(value.charAt(0) != '[' && value.charAt(0) != '{'){
            value = value.substring(1,value.length()-1).strip();
        }
        return Arrays.asList(key, value);
    }

    public static void main(String[] args) {
        String s = "{\"dsf\":\"asfdsfsdf\","+
            "\"dd2\" : \"[\"14\",{\"hdgfh\":\"dasfdsf\",\"css\":\"55a\"},[\"hdge\",\"fgh\"]]\"}";
        List<String> kvs = splitComma(new StringBuilder(s.substring(1, s.length()-1)));
        Map<String,String> map = new HashMap<>();
        for(String kvString : kvs){
            List<String> kv = splitColon(kvString);
            System.out.println(kv);
            map.put(kv.get(0), kv.get(1));
        }

        System.out.println();
        String jsonObj = map.get("dd2");
        kvs = splitComma(new StringBuilder(jsonObj.substring(1, jsonObj.length()-1)));
        for(int i = 0;i<kvs.size();i++){
            String temp = kvs.get(i).strip();
            if(temp.charAt(0) != '[' && temp.charAt(0) != '{'){
                temp = temp.substring(1,temp.length()-1);
            }
            kvs.set(i, temp);
            System.out.println(temp);
        }
    }
}
