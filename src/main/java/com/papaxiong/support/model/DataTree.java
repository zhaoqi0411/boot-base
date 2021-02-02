package com.papaxiong.support.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author zhaoqi
 * @since 2020-11-14
 */
@Data
public class DataTree {

    private String treeName;

    private TreeNode rootNode;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeNode extends Object {


        private List<TreeNode> children;

    }
}
