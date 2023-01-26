/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo;

import java.util.LinkedList;

/**
 *
 * @author Student
 */
public interface SQLint extends AutoCloseable{
    boolean connect (String url, String username, String password);
    boolean createTable(String tableName, String[][] params);
    int insert (String[] [] values, String tableName, String[] columns);
    int updateRows (String tableName, String column, String conditionColumn, String[] conditions, String [] values);
    // UPDATE students SET name = CASE id
    //                            WHEN 23 THEN 'Alexandr'
    //                            WHEN 24 THEN 'Sasha'
    //                            WHEN 25 THEN 'Alex'
    //                            ELSE name END
    // WHERE id INT (23,24,25);
    boolean updateColumns (String tableName, String [] columns, String[] values, String condition);
    // UPDATE students SET name = 'Alexandr', date = 12 WHERE school_id = 23 AND last_name LIKE 'smth';
    LinkedList <LinkedList <String>> select (String values, String tableName, String condition);
    boolean delete (String tableName);
    int deleteRows (String tableName, String condition);
    boolean addColumn (String tableName, String column);// column = "name TEXT NOT NULL"
    // close();
    
}
