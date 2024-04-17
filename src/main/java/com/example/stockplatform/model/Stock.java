package com.example.stockplatform.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  // Marks this class as a JPA entity
@Table(name = "stocks")  // Specifies the table in the database with which this entity is mapped
public class Stock {
    @Id  // Marks the field below as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Configures the way the id is generated
    private Long id;

    private String symbol;  // Stock ticker symbol, e.g., "AAPL"
    private String name;    // Stock name, e.g., "Apple Inc."
    private Double price;   // Current stock price

    // Constructors
    public Stock() {
    }

    public Stock(String symbol, String name, Double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
