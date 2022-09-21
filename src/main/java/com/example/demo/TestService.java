package com.example.demo;

import com.example.demo.entity.Test;
import com.example.demo.entity.TestExample;
import com.example.demo.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Arrays;
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
        try (FileOutputStream fos = new FileOutputStream("./xxx.pdf")) {
            mapper.selectByPrimaryKey2("1", resultContext -> {

                byte[] bytes = resultContext.getResultObject().getFile();
//                log.info(HexUtils.toHexString(Arrays.copyOf(bytes, 8)));
                try {
                    fos.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test4() {

        try (FileOutputStream fos = new FileOutputStream("./xxx.pdf")) {

            IntStream.range(0, 40).forEach(o -> {
                try {
                    fos.write(mapper.selectByPrimaryKey4(o).getFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
