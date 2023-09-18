package com.wypaperplane.syscore.entity;

public class WxUserVo extends WxUserBase{

    /**
     * 0教练, 1学员
     * */
    private Byte role;

    private Byte permission;

    /**
     * 0 false 可用, 1 true 禁用
     * */
    private Boolean deleted;

    public WxUserVo() {}

    public WxUserVo(Byte role, Byte permission, Boolean deleted) {
        this.role = role;
        this.permission = permission;
        this.deleted = deleted;
    }

    public WxUserVo(Integer id, String avatar, String nickname, String reallname, Byte gender, String mobile, Byte role, Byte permission, Boolean deleted) {
        super(id, avatar, nickname, reallname, gender, mobile);
        this.role = role;
        this.permission = permission;
        this.deleted = deleted;
    }

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    public Byte getPermission() {
        return permission;
    }

    public void setPermission(Byte permission) {
        this.permission = permission;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
