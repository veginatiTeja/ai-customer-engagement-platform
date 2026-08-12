package com.aiCustomer.ai_customer_engagement_platofrm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity //spring/JPA treats this java class as a database entity
@Table(name="customers")
public class Customer {
   @Id  //This tells JPA This is the primary key
   @GeneratedValue(strategy=GenerationType.IDENTITY) // DB generates auto increment ids when a new customer inserts into atable
   private Long id;

   private String name;
   private String email;
   public Customer() {

   }

   public Customer(String name, String email) {
       this.name =name;
       this.email = email;
   }

   public Long getId() {
       return id;
   }

   public String getName() {
       return name;
   }

   public String getEmail() {
       return  email;
   }

   public void setName(String name) {
       this.name = name;
   }

   public void setEmail(String email) {
        this.email = email;
    }

}