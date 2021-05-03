package com.groupjn.cartservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue
    private int id;
    private int product_id;
    private int quantity;
    private double price;
    private int user_id;

}
