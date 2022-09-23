package com.example.demo;

import com.example.demo.entity.Test;
import com.example.demo.entity.TestExample;
import com.example.demo.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.util.buf.HexUtils;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
@Slf4j
public class TestService {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private TestMapper mapper;

    public void test() {

//        TestExample testExample = new TestExample();
//        testExample.createCriteria().andIdEqualTo("1");
//
//        mapper.selectByExampleWithBLOBs(testExample);
//        mapper.selectByExampleWithBLOBs(testExample);
//        mapper.selectByExampleWithBLOBs(testExample);

        IntStream.range(100, 200).forEach(o -> {
            TestExample testExample2 = new TestExample();
            testExample2.createCriteria().andIdEqualTo("1").andIdNotEqualTo(String.valueOf(o));
            mapper.selectByExampleWithBLOBs(testExample2);
        });

    }

    public void test2() throws SQLException {

        CopyManager copyManager = new CopyManager(sqlSession.getConnection().unwrap(BaseConnection.class));

        try (FileOutputStream fos = new FileOutputStream("./aaa.pdf")) {
            copyManager.copyOut("COPY (SELECT file FROM test WHERE id = '10') TO STDOUT WITH BINARY;", fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test3() {

//        try (FileOutputStream fos = new FileOutputStream("./bbb.pdf")) {
//
//            byte[] holeFile = mapper.selectByPrimaryKey("1").getFile();
//            log.info(HexUtils.toHexString(Arrays.copyOf(holeFile, 8)));
//            fos.write(holeFile);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        long startTime,endTime;

        startTime = System.currentTimeMillis();
        mapper.selectByPrimaryKey2("10", resultContext -> {
            resultContext.getResultObject().getFile();
        });
        endTime = System.currentTimeMillis();
        System.out.println("resultContext処理時間 ： " + (endTime - startTime) + "ミリ秒");

        startTime = System.currentTimeMillis();
        mapper.selectByPrimaryKey2("10").forEach(Test::getFile);
        endTime = System.currentTimeMillis();
        System.out.println("cursor処理時間 ： " + (endTime - startTime) + "ミリ秒");

//        String a = mapper.selectByPrimaryKey2("10").stream().limit(10).map(o->HexUtils.toHexString(Arrays.copyOf(o.getFile(), 10))).collect(Collectors.joining(" "));

//        log.info(a);
    }

    public void test4() {

        System.out.println("!!!!!!");

        System.out.println(this.sqlSession);

        List<String> head = new ArrayList<>();
        IntStream.range(0, 400).forEach(o -> {
            Test a = mapper.selectByPrimaryKey4(o);

            if (o < 10) {
                head.add(HexUtils.toHexString(Arrays.copyOf(a.getFile(), 10)));
            }
        });

        log.info(String.join(" ", head));
//        try (FileOutputStream fos = new FileOutputStream("./xxx.pdf")) {
//
//            IntStream.range(0, 400).forEach(o -> {
//                try {
//                    fos.write(mapper.selectByPrimaryKey4(o).getFile());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void test5() {

        mapper.selectByPrimaryKey("10");
//        try (FileOutputStream fos = new FileOutputStream("./xxx.pdf")) {
//
//            try (Cursor<Test> persons = mapper.selectByPrimaryKey2("10")) {
//                persons.forEach(o -> {
//                    try {
//                        fos.write(o.getFile());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
