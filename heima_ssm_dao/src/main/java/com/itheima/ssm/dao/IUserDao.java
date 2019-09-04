package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    //用户权限管理spring Security
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRolesByUserId")
            )
    })
    public UserInfo findByUsername(String username) throws Exception;

    //在用户管理中查询用户
    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    //添加用户
    @Insert("insert into users values (#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);

    //根据用户的ID查询用户详情
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRolesByUserId")
            )
    })
    public UserInfo findById(String id) throws Exception;

    //查询用户还可以添加哪些权限
    @Select("select * from role where id not in (select roleid from users_role where userid = #{id})")
    public List<Role> findRolesByUserId(String id) throws Exception;

    //添加用户和角色管理
    @Insert("insert into users_role(userid,roleid) values (#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);


}
