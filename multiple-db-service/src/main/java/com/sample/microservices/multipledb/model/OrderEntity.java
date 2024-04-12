package com.sample.microservices.multipledb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders", schema = "c##wanzun")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Integer id;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "order_amount")
  private Integer orderAmount;

  @Column(name = "user_id")
  private Integer userId;
}
