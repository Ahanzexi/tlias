package com.niepan.mapper;

import com.niepan.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
    * 所有部门
    * */
    @Select("select * from dept")
    List<Dept> list();

    /*
    * 删除部门
    * */
    @Delete("delete from dept where id = #{id}")
    void delete(int id);

    /*
    * 新建部门
    * */
    @Insert("insert into dept(name,createtime,updatetime) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /*
    * 通过ID查找部门
    * */
    @Select("select * from dept  where id = #{id}")
    Dept getDeptById(int id);

    /*
    * 修改部门
    * */
    @Update("update dept set name = #{name},updatetime = #{updateTime} where id = #{id}")
    void edit(Dept dept);
}
