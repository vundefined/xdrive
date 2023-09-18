package com.wypaperplane.shiroapi.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysMenuVo implements Serializable {
    private Integer id;
    private Integer pid;
    private Byte sort;
    private String path;
    private String name;
    private String component;
    private Meta meta;
    private List<SysMenuVo> children;

    public static class Meta {
        private String title;
        private String icon;
        private Boolean hidden;
        private Boolean keepAlive;
        private String permission;

        public Meta(String title, String icon, Boolean hidden, Boolean keepAlive, String permission) {
            this.title = title;
            this.icon = icon;
            this.hidden = hidden;
            this.keepAlive = keepAlive;
            this.permission = permission;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }


        public Boolean getHidden() {
            return hidden;
        }

        public void setHidden(Boolean hidden) {
            this.hidden = hidden;
        }

        public Boolean getKeepAlive() {
            return keepAlive;
        }

        public void setKeepAlive(Boolean keepAlive) {
            this.keepAlive = keepAlive;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }
    }

    public SysMenuVo(Integer id, Integer pid, Byte sort, String path, String name, String component, Meta meta, List<SysMenuVo> children) {
        this.id = id;
        this.pid = pid;
        this.sort = sort;
        this.path = path;
        this.name = name;
        this.component = component;
        this.meta = meta;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<SysMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuVo> children) {
        if (children == null) {
            this.children = new ArrayList<SysMenuVo>();
        } else {
            this.children = children;
        }
    }
}
