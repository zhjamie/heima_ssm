package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll() throws Exception;

    public void save(Role role);

    public Role findRoleById(String id) throws Exception;

    public List<Permission> findOthers(String id) throws Exception;

    public void addPermissionToRole(String roleId,String[] permissionIds);

}
