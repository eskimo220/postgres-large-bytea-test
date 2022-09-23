package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;


@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private JDBCService jdbcService;

    @GetMapping("/")
    public ResponseEntity get() throws SQLException {

//        TestExample testExample = new TestExample();
//        testExample.createCriteria().andIdIsNotNull();
//
//        mapper.selectByExample(testExample);

        testService.test2();
//        mapper.countByExample();

        return ResponseEntity.ok("12345");
    }


    @GetMapping("/test")
    public ResponseEntity get2() throws SQLException {

//        TestExample testExample = new TestExample();
//        testExample.createCriteria().andIdIsNotNull();
//
//        mapper.selectByExample(testExample);

        testService.test();
//        mapper.countByExample();

        return ResponseEntity.ok("12345");
    }

    @GetMapping("/test2")
    public ResponseEntity get3() throws SQLException {

//        TestExample testExample = new TestExample();
//        testExample.createCriteria().andIdIsNotNull();
//
//        mapper.selectByExample(testExample);
        long startTime,endTime;

//        startTime = System.currentTimeMillis();
//        testService.test5();
//
//        endTime = System.currentTimeMillis();
//        System.out.println("処理時間 ： " + (endTime - startTime) + "ミリ秒");


        startTime = System.currentTimeMillis();
        testService.test4();

        endTime = System.currentTimeMillis();
        System.out.println("処理時間 ： " + (endTime - startTime) + "ミリ秒");


        startTime = System.currentTimeMillis();
        testService.test3();
        endTime = System.currentTimeMillis();
        System.out.println("処理時間 ： " + (endTime - startTime) + "ミリ秒");

        startTime = System.currentTimeMillis();
        jdbcService.testCursor();
        endTime = System.currentTimeMillis();
        System.out.println("処理時間 ： " + (endTime - startTime) + "ミリ秒");

//        mapper.countByExample();

        return ResponseEntity.ok("12345");
    }
}
