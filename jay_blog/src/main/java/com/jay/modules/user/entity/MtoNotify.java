package com.jay.modules.user.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 通知表
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@TableName("mto_notify")
public class MtoNotify extends Model<MtoNotify> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 事件
     */
    private Integer event;
    /**
     * 从
     */
    @TableField("from_id")
    private Long fromId;
    /**
     * 所属用户id
     */
    @TableField("own_id")
    private Long ownId;
    /**
     * 阅读状态
     */
    private Integer status;
    /**
     * 关联文章id
     */
    @TableField("post_id")
    private Long postId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getEvent() {
        return event;
    }

    public void setEvent(Integer event) {
        this.event = event;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getOwnId() {
        return ownId;
    }

    public void setOwnId(Long ownId) {
        this.ownId = ownId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MtoNotify{" +
        ", id=" + id +
        ", created=" + created +
        ", event=" + event +
        ", fromId=" + fromId +
        ", ownId=" + ownId +
        ", status=" + status +
        ", postId=" + postId +
        "}";
    }
}
