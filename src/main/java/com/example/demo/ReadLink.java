/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Student
 */

public class ReadLink {
    
//              1) nextRecord -> добавитль в список -> преобразовать список в матрицу строк
//              2) nextRecord -> добавитль в список -> после цикла возвращаем список
//                                                      ? list.toString()
    public static String[][] readLine(String file)
    {
        try {
            ArrayList <String[]> list = new ArrayList <> ();
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
//                for (String cell : nextRecord) {
                list.add(nextRecord);
//                    System.out.print(cell + "\t");
//                }
            }
            return list.toArray(new String [list.size()][]);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
