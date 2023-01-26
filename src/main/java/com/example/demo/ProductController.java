package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping ("/select")
    public Product select (@RequestParam (value="prod_full_name",required=false,defaultValue="potato~import")String condition){
        String[] cond = condition.split("~");
        String conditions = "WHERE prod_name = '" + cond[0] + "' AND prod_type Like '" + cond[1]+ "'";
        return new Product(conditions);
    }
    @GetMapping ("/select")
    public List selectAll (){
        var instance = Post.getInstance (); //singleton
        instance.connect("jdbc:postgresql://localhost:5432/postgres" ,"postgres", "postgres");
        String COLUMNS = "prod_name, prod_type, prod_amt, prod_price, prod_discount";
        var list = instance.select(COLUMNS, "products", "");
        return list.stream().map(row -> new Product (row)).toList();
    }
}
