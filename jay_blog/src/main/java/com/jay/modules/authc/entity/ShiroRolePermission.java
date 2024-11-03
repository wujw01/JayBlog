package com.jay.modules.authc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 角色-权限值
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@TableName("shiro_role_permission")
public class ShiroRolePermission extends Model<ShiroRolePermission> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权限值id
     */
    @TableField("permission_id")
    private Long permissionId;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;


    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShiroRolePermission{" +
        ", permissionId=" + permissionId +
        ", roleId=" + roleId +
        "}";
    }
}
