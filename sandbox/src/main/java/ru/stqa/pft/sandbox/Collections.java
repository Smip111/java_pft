package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 10.12.2016.
 */
public class Collections {
    public static void main(String[] args){

        String[] langs={"Java","C#","Python","PHP"};

        List<String> languages=new ArrayList<String>();
        languages.add("Java");
        languages.add("C#");
        languages.add("Python");
        languages.add("PHP");



        for(String l : languages){
            System.out.println("I want to learn "+l);
        }
    }


}
