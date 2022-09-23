package com.example.demo.mapper;

import com.example.demo.entity.Test;
import com.example.demo.entity.TestExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultHandler;

public interface TestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    long countByExample(TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    int deleteByExample(TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    int insert(Test record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    int insertSelective(Test record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    List<Test> selectByExampleWithBLOBs(TestExample example);


    void selectByPrimaryKey2(String id, ResultHandler<Test> resultHandler);

//    List<Test> selectByPrimaryKey2(String id);

    Cursor<Test> selectByPrimaryKey2(String id);

    void selectByPrimaryKey3(String id, ResultHandler<Test> resultHandler);

    Test selectByPrimaryKey4(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    List<Test> selectByExample(TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    Test selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Test record, @Param("example") TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") Test record, @Param("example") TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Test record, @Param("example") TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Test record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.test
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(Test record);
}