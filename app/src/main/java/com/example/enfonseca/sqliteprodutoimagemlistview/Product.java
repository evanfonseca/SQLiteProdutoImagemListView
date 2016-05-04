package com.example.enfonseca.sqliteprodutoimagemlistview;

/**
 * Created by enfonseca on 26/04/16.
 */
public class Product {

    int id;
    String name;
    double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    public double getPreco() {
        return price;
    }

    public void setPreco(double price) {
        this.price = price;
    }

    public Product(int id, String nome, double price) {
        this.id = id;
        this.name = nome;
        this.price = price;
    }

    public Product(String nome, double price) {
        this.name = nome;
        this.price = price;
    }

    public Product() {
    }
}
