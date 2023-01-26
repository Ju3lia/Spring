package com.example.demo;

import java.util.LinkedList;

public class Product {
    private String name;
    private String type;
    private int amt;
    private int price;
    private float discount;


    private final String COLUMNS = "prod_name, prod_type, prod_amt, prod_price, prod_discount";

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAmt() {
        return amt;
    }

    public int getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }


    Product (String condition) {
        /*(user defined class)*/
        var instance = Post.getInstance (); //singleton
        instance.connect("jdbc:postgresql://localhost:5432/postgres" ,"postgres", "postgres");
//        System.out.println("List = " + instance.select(COLUMNS,"products", condition));
        LinkedList<String> row = instance.select(COLUMNS,"products", condition).get(0);
        //parsing
        this.name = row.get(0);
        this.type = row.get(1);
        this.amt = Integer.parseInt(row.get(2));
        this.price = Integer.parseInt(row.get(3));
        this.discount = Float.parseFloat(row.get(4));
//        this.finalPrice = Float.parseFloat(row.get(5));
    }
}
