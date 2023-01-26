/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Student
 */
public class Post implements SQLint {

    private static Post instance;
    private Connection con;
    private Statement stm;
    
    private Post(){}
    
    public static Post getInstance(){
        if (instance == null) instance = new Post();
        return instance;
    }

    @Override
    public boolean connect(String url, String username, String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
            stm = con.createStatement();
            return true;
        } catch (Exception ex) {
            con = null;
            stm = null;
            return false;
        }
    }

    @Override
    public boolean createTable(String tableName, String[][] columns) {
        try {
            if (columns == null) return false;
            var sql = new StringBuilder("CREATE TABLE " + tableName + "( ");
            System.out.println(sql);
            var pk = new StringBuilder ("PRIMARY KEY (");
            for (String[] column : columns) {
                System.out.println(Arrays.toString(column));
                for (var col : column)
                    if (col.equalsIgnoreCase("pk")) {
                        pk.append(column[0]).append(',');
//                        sql.append(col, 0, col.length() - 3).append(',');
                    } else sql.append(col).append(' ');
                sql.append(',');
            }
            pk = new StringBuilder (pk.substring(0, pk.length()-1));        
            pk.append(')');
            sql.append(pk).append(");");
            System.out.println(sql);
            stm.execute(String.valueOf(sql));
            return true;
        } catch (SQLException e) {
            System.out.println("!!!!");
            return false;
        }
    }

    @Override
    public int insert(String[][] values, String tableName, String [] columns) {
        try {
            String sql = "INSERT INTO " + tableName + "(";
            for (String column: columns) {
                sql += column + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")" + "VALUES"; 
            for (String[] row : values) {
                sql += "(";
                for (String str : row) {
                    sql += str + ',';
                }
                sql = sql.substring(0, sql.length() - 1);
                sql += "),";
            }
            sql = sql.substring(0, sql.length() - 1);
                sql += ";";
            System.out.println(sql);
            return stm.executeUpdate(sql);
        } catch (Exception e) {
            return (-1);
        }
    }

    //condition = "WHERE prod_name=potato+prod_type=import"
    @Override
    public LinkedList<LinkedList<String>> select(String values, String tableName, String condition) {
        LinkedList<LinkedList<String>> list = new LinkedList< LinkedList<String>>();

        try {
            String sql = "SELECT " + values + " FROM " + tableName;
            if (!condition.equals(""))
                sql += " " + condition + ";";
            else {
                sql += ";";
            }
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                LinkedList<String> link = new LinkedList<>();
                var length = values.split(",").length;
                for (int i = 1; i <= length; i++) {
                    link.add(rs.getString(i));
                    
                }
                list.add(link);
            }
            return list;
        } catch (SQLException e) {
            return list;
        }
//        return list;
    }

    @Override
    public int updateRows(String tableName, String column, String conditionColumn, String[] conditions, String [] values) {
        try{
            String sql = "UPDATE " + tableName +  " SET " + column + " = CASE " + conditionColumn;
            int min = Math.min( conditions.length, values.length);
            for (int i=0; i < min; ++i){
                sql += " WHEN " + conditions[i] + " THEN '" + values[i] + "'";
            }
            sql += "ELSE " + column + " END WHERE " + conditionColumn + " IN(";
            for (int i = 0; i < min; ++i) sql += conditions[i] + ",";
            sql = sql.substring(0, sql.length()-1);
            sql += ");";
            System.out.println(sql);
            return stm.executeUpdate(sql);
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public boolean updateColumns(String tableName, String[] columns, String[] values, String condition) {
        try{
            String sql = "UPDATE " + tableName + " SET ";
            int min = Math.min( columns.length, values.length);
            for (int i=0; i < min; ++i){
               sql += columns[i] + " = " + values [i] + ", ";  
            }
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE " + condition + ";" ; 
            System.out.println(sql);
            stm.execute(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(String tableName) {
        try{
            String sql = "DELETE " + tableName + ";";
            boolean ResultSet = stm.execute(sql);
            return ResultSet;
        }catch (Exception e) {
            return false;
        }
                
    }

    @Override
    public int deleteRows(String tableName, String condition) {
       try{
           String sql = "DELETE " + tableName + " WHERE " + condition + ";"; 
           int ResultSet = stm.executeUpdate(sql);
           return ResultSet;
       }catch (Exception e) {
           return -1;
       }
    }

    @Override
    public boolean addColumn(String tableName, String column) {
       try{
           String sql = "ALTER TABLE " + tableName + " ADD COLUMN " + column + ";";
           boolean ResultSet = stm.execute(sql);
           return ResultSet;
       }catch (Exception e) {
           return false;
       }
    }

    @Override
    public void close() throws Exception {
        if (con!=null) {
            con.close();
        }
    }

}
