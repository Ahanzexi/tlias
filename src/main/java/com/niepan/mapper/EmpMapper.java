package com.niepan.mapper;

import com.niepan.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /*
    * 员工信息查询
    * */
    List<Emp> list(@Param("name") String name,@Param("gender") Short gender,@Param("begin") LocalDate begin,@Param("end") LocalDate end);

    /*
    * 批量删除员工
    * */
    void delete(@Param("ids") List<Integer> ids);

    /*
    * 新建员工
    * */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, createtime, updatetime) " +
            "values(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /*
    * 根据ID查询员工
    * */
    @Select("select id, username, name, gender, image, job, entrydate, dept_id as deptId, createtime, updatetime from emp where id = #{id}")
    Emp getEmpById(String id);
    void edit(Emp emp);

    /*
    * 根据用户名和密码查询员工
    * */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getEmpByUsernameAndPassword(Emp emp);

    /*
    * 根据部门Id删除该部门的员工
    * */
    @Delete("delete from emp where dept_id = #{deptID}")
    void deleteByDeptId(Integer deptID);
}
