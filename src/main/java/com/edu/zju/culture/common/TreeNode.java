package com.edu.zju.culture.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author y4oung
 * @date 2020/3/15 2:06 PM
 * @description 左侧导航栏数据结构
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    private Integer id;
    @JsonProperty("parentId")
    private Integer pid;
    private String title;
    private Integer type;
    private String icon;
    private Boolean spread;
    private String href;
    private List<TreeNode> children = new ArrayList<TreeNode>();

    /**
     * 首页左边导航树的构造器
     */
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread, Integer type) {
        super();
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
        this.type = type;
    }

    /**
     * dtree的数据格式
     *
     * @param id
     * @param pid
     * @param title
     * @param spread
     */
    public TreeNode(Integer id, Integer pid, String title, Boolean spread) {
        super();
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
    }
}

