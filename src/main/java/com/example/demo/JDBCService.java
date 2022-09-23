package com.example.demo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
@Transactional
public class JDBCService {

    @Autowired
    private SqlSession sqlSession;

    public void testCursor() throws SQLException {
        Connection conn = sqlSession.getConnection();

        conn.setAutoCommit(false);

        Statement st = conn.createStatement();
        st.setFetchSize(10);
        ResultSet rs = st.executeQuery("WITH f AS (SELECT file f\n" +
                "            FROM public.test\n" +
                "            WHERE \"id\" = '10')\n" +
                "            SELECT\n" +
                "            substring((SELECT f\n" +
                "            FROM f) FROM n FOR 1024*1024) AS \"file\"\n" +
                "            FROM generate_series(1, length((SELECT f\n" +
                "                                            FROM f)), 1024*1024) n\n");
        while (rs.next()) {
            rs.getBytes(1);
        }
        rs.close();
        st.close();
    }
}
