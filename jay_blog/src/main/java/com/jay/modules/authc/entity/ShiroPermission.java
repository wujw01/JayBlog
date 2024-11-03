package com.jay.modules.authc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 权限值表
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@TableName("shiro_permission")
public class ShiroPermission extends Model<ShiroPermission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String description;
    /**
     * 权限值名称
     */
    private String name;
    /**
     * 父id
     */
    @TableField("parent_id")
    private Long parentId;
    private Integer version;
    private Integer weight;


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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
        return "ShiroPermission{" +
        ", description=" + description +
        ", name=" + name +
        ", parentId=" + parentId +
        ", version=" + version +
        ", weight=" + weight +
        "}";
    }
}
