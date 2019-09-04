package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionid from role_permission where roleid = #{userId})")
    public List<Permission> findPermissionByUserId(String userId);

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Insert("insert into permission values (#{id},#{permissionName},#{url})")
    public void save(Permission permission);

}
