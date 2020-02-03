package com.feng.servcie.impl;

import com.feng.servcie.TableOptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableOptServiceImplTest {
    @Autowired
    private TableOptService tableOptService;

    @Test
    public void cleanTableData() {
        tableOptService.cleanTableData("tb_film");
    }
}