package com.jay.modules.authc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@TableName("shiro_role")
public class ShiroRole extends Model<ShiroRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色状态
     */
    private Integer status;

    @Getter
    @Setter
    private List<ShiroPermission> permissions;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "ShiroRole{" +
        ", description=" + description +
        ", name=" + name +
        ", status=" + status +
        "}";
    }
}
