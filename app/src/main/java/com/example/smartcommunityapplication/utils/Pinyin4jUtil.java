package com.example.smartcommunityapplication.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Pinyin4jUtil {

    public static String getFullSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString();
    }

    public static Map<String,Object> px(List<String> list){
        Comparator com = Collator.getInstance(Locale.CHINA);
        //按字母排序
        Collections.sort(list,com);
        Map<String,Object> map = new TreeMap<String,Object>();
        for(int i=1;i<=26;i++){
            String word = String.valueOf((char)(96+i)).toUpperCase();
            //循环找出首字母一样的数据
            List<String> letter = new ArrayList<String>();
            for(String str : list){
                String zm = getFullSpell(str).substring(0,1);
                if(word.equals(zm)){
                    letter.add(str);
                }
            }
            map.put(word,letter);
        }
        return map;
    }

    public static String convertToFirstSpell(String chinese){
        StringBuffer pinyinName=new StringBuffer();
        char[] nameChar=chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat=new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c:nameChar){
            if (c>128){
                try {
                    String[] strs= PinyinHelper.toHanyuPinyinStringArray(c,defaultFormat);
                    if (strs!=null){
                        for (int i=0;i<strs.length;i++){
                            pinyinName.append(strs[i].charAt(0));
                            if (i!=strs.length-1){
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }else {
                pinyinName.append(c);
            }
            pinyinName.append(" ");
        }
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }

    public static String convertToSpell(String chinese){
        StringBuffer pinyinName=new StringBuffer();
        char[] nameChar=chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat=new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c:nameChar){
            if (c>128){
                try {
                    String[] strs= PinyinHelper.toHanyuPinyinStringArray(c,defaultFormat);
                    if (strs!=null){
                        for (int i=0;i<strs.length;i++){
                            pinyinName.append(strs[i]);
                            if (i!=strs.length-1){
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }else {
                pinyinName.append(c);
            }
            pinyinName.append("  ");
        }
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }

    public static List<String> convertToSpellList(String chinese){
        StringBuffer pinyinName=new StringBuffer();
        char[] nameChar=chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat=new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c:nameChar){
            if (c>128){
                try {
                    String[] strs= PinyinHelper.toHanyuPinyinStringArray(c,defaultFormat);
                    if (strs!=null){
                        for (int i=0;i<strs.length;i++){
                            pinyinName.append(strs[i]);
                            if (i!=strs.length-1){
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }else {
                pinyinName.append(c);
            }
            pinyinName.append("  ");
        }
        return parseTheChineseByObjectToList(discountTheChinese(pinyinName.toString()));
    }

    private static List<Map<String ,Integer>> discountTheChinese(String theStr){
        List<Map<String,Integer>> mapList=new ArrayList<Map<String, Integer>>();
        Map<String,Integer> onlyOne=null;
        String[] firsts=theStr.split(" ");
        for (String str:firsts){
            onlyOne=new Hashtable<String, Integer>();
            String[] china=str.split(",");
            for (String s:china){
                Integer count=onlyOne.get(s);
                if (count==null){
                    onlyOne.put(s,new Integer(1));
                }else {
                    onlyOne.remove(s);
                    count++;
                    onlyOne.put(s,count);
                }
            }
            mapList.add(onlyOne);
        }
        return mapList;
    }

    private static String parseTheChineseByObject(List<Map<String,Integer>> list){
        Map<String,Integer> first=null;
        for (int i=0;i<list.size();i++){
            Map<String,Integer> temp=new Hashtable<String, Integer>();
            if (first!=null){
                for (String s:first.keySet()){
                    for (String s1:list.get(i).keySet()){
                        String str=s+s1;
                        temp.put(str,1);
                    }
                }
                if (temp!=null&&temp.size()>0){
                    first.clear();
                }
            }else {
                for (String s:list.get(i).keySet()){
                    String str=s;
                    temp.put(str,1);
                }
            }
            if (temp!=null&&temp.size()>0){
                first=temp;
            }
        }
        String returnStr="";
        List<String> returnList=new ArrayList<>();
        if (first!=null){
            for (String str:first.keySet()){
                returnStr+=(str+" ");
                returnList.add(str);
            }
        }
        if (returnStr.length()>0){
            returnStr=returnStr.substring(0,returnStr.length()-1);
        }
        return returnList.get(0);
    }

    private static List<String> parseTheChineseByObjectToList(List<Map<String,Integer>> list){
        Map<String,Integer> first=null;
        for (int i=0;i<list.size();i++){
            Map<String,Integer> temp=new Hashtable<String, Integer>();
            if (first!=null){
                for (String s:first.keySet()){
                    for (String s1:list.get(i).keySet()){
                        String str=s+s1;
                        temp.put(str,1);
                    }
                }
                if (temp!=null&&temp.size()>0){
                    first.clear();
                }
            }else {
                for (String s:list.get(i).keySet()){
                    String str=s;
                    temp.put(str,1);
                }
            }
            if (temp!=null&&temp.size()>0){
                first=temp;
            }
        }
        List<String> returnList=new ArrayList<>();
        if (first!=null){
            for (String str:first.keySet()){
                returnList.add(str);
            }
        }
        return returnList;
    }

    public static boolean isPinYin(String string){
        char[] chars=string.toCharArray();
        for (char c:chars){
            if ((c>=65&&c<=90)||(c>=97&&c<=122)){

            }else {
                return false;
            }
        }
        return true;
    }
}
