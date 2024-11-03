package com.jay.modules.authc.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@TableName("mto_users")
public class MtoUsers extends Model<MtoUsers> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 注册时间
     */
    private String created;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 最近登录
     */
    @TableField("last_login")
    private Date lastLogin;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 更新时间
     */
    private Date updated;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 注册来源，主要用于区分第三方登录
     */
    private Integer source;
    /**
     * 邮箱激活状态
     */
    @TableField("active_email")
    private Integer activeEmail;
    /**
     * 发布评论数
     */
    private Integer comments;
    /**
     * 粉丝数
     */
    private Integer fans;
    /**
     * 收到的喜欢数
     */
    private Integer favors;
    /**
     * 关注人数
     */
    private Integer follows;
    /**
     * 文章数
     */
    private Integer posts;
    /**
     * 个性签名
     */
    private String signature;


    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getActiveEmail() {
        return activeEmail;
    }

    public void setActiveEmail(Integer activeEmail) {
        this.activeEmail = activeEmail;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getFavors() {
        return favors;
    }

    public void setFavors(Integer favors) {
        this.favors = favors;
    }

    public Integer getFollows() {
        return follows;
    }

    public void setFollows(Integer follows) {
        this.follows = follows;
    }

    public Integer getPosts() {
        return posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
        return "MtoUsers{" +
        ", created=" + created +
        ", email=" + email +
        ", lastLogin=" + lastLogin +
        ", mobile=" + mobile +
        ", password=" + password +
        ", status=" + status +
        ", username=" + username +
        ", name=" + name +
        ", avatar=" + avatar +
        ", updated=" + updated +
        ", gender=" + gender +
        ", roleId=" + roleId +
        ", source=" + source +
        ", activeEmail=" + activeEmail +
        ", comments=" + comments +
        ", fans=" + fans +
        ", favors=" + favors +
        ", follows=" + follows +
        ", posts=" + posts +
        ", signature=" + signature +
        "}";
    }
}
