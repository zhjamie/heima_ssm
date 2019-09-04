package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户ID查询所有角色
    @Select("select * from role where id in (select roleid from users_role where userid = #{id})")
    @Results({
            @Result(property = "permissions",column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByUserId")
            )
    })
    public List<Role> findRolesByUserId(String id) throws Exception;

    //查询所有角色
    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    //添加角色信息
    @Insert("insert into role values (#{id},#{roleName},#{roleDesc})")
    public void save(Role role);

    //根据role ID查询出角色信息
    @Select("select * from role where id = #{roleId}")
    public Role findRoleById(String roleId) throws Exception;

    //根据角色ID查出该角色不具有的所有权限
    @Select("select * from permission where id not in (select permissionid from role_permission where roleid = #{id})")
    public List<Permission> findOthers(String id) throws Exception;

    //向中间表中添加角色权限对应关系
    @Insert("insert into role_permission values (#{roleId},#{permissionId})")
    public void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);



}
