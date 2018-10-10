package com.shiro;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

public class ShiroZhujie {

    @RequiresRoles("manager")//角色校验
    public String save() {
        return "save";
    }

    @RequiresPermissions("user:manage")//权限校验
    public String delete() {
        return "delete";
    }
}
