package com.sample.microservices.multipledb.service;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles({"unit", "api-security"})
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@ComponentScan({"com.sample.microservices.multipledb","com.sample.microservices.common.model"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public abstract class BaseTest {

}
