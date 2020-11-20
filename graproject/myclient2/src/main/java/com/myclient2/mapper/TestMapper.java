package com.myclient2.mapper;

import com.myclient2.entity.TestEntity;
import com.test.myjdbcutils.myannotations.MyJDBCAnnotation;

import java.util.List;

/**
 * @author pb
 */
public class TestMapper {
    @MyJDBCAnnotation(sql = "select * from test where id = $pb$ ")
    public TestEntity selectAll(Integer s){
        return null;
    }
    public void test(){
        System.out.println("未被代理的方法");
    }
}
