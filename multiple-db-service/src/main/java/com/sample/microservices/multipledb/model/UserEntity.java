package com.sample.microservices.multipledb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users", schema = "c##wanzun")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;
}
