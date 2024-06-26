package com.busanit501.shoesproject.repository;

import com.busanit501.shoesproject.repository.nhjrepository.SizeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class SizeRepositoryTest {

    @Autowired
    SizeRepository sizeRepository;

}

