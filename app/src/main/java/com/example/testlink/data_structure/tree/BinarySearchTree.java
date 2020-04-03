package com.example.testlink.data_structure.tree;

import android.util.Log;

/**
 * Created by lxx on 2019/5/21.
 * Use by
 */

public class BinarySearchTree {

    public Node rootNode;

    /**
     * 查询
     *
     * @param value
     */
    public boolean find(int value) {
        Node current = rootNode;
        while (current.data != value) {
            if (value < current.data) {
                current = current.left;
            } else if (value > current.data) {
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 增加
     *
     * @param value
     */
    public boolean insert(int value) {
        Node newNode = new Node(value);
        if (rootNode == null) {
            rootNode = newNode;
            return true;
        } else {
            Node current = rootNode;
            Node father = null;
            while (current != null) {
                father = current;
                if (value < current.data) {
                    current = current.left;
                    if (current == null) {
                        father.left = newNode;
                        return true;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        father.right = newNode;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void disPlayCenter(Node node) {
        if (node != null) {
            disPlayCenter(node.left);//访问左节点
            Log.d("tree", "disPlayCenter: " + node.data);//使用log表示为访问了这个节点
            disPlayCenter(node.right); //访问右节点
        }
    }

    /**
     * 前序遍历
     *
     * @param node
     */
    public void disPlayFront(Node node) {
        if (node != null) {
            Log.d("tree", "disPlayCenter: " + node.data);
            disPlayFront(node.left);
            disPlayFront(node.right);
        }
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public void disPlayBehind(Node node) {
        if (node != null) {
            disPlayBehind(node.left);
            disPlayBehind(node.right);
            Log.d("tree", "disPlayCenter: " + node.data);
        }
    }

    /**
     * 删除
     *
     * @param value
     */
    public boolean delete(int value) {
        Node current = rootNode;
        Node parentNode = rootNode;
        boolean isLeftNode = false;
        while (current != null && current.data != value) {
            parentNode = current;
            if (value < current.data) {
                current = current.left;
                isLeftNode = true;
            } else if (value > current.data) {
                current = current.right;
                isLeftNode = false;
            }
            if (current == null) {
                return false;
            }
        }
        //需要判断三种情况:1.没有子节点;2.只有一个子节点;3.有两个子节点;
        if (current != null && current.left == null && current.right == null) {
            if (current == rootNode) {
                rootNode = null;
            } else if (isLeftNode) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        } else if (current != null && current.left == null) {
            if (current == rootNode) {
                rootNode = current.right;
            } else if (isLeftNode) {
                parentNode.left = current.right;
            } else {
                parentNode.right = current.right;
            }
        } else if (current != null && current.right == null) {
            if (current == rootNode) {
                rootNode = current.left;
            } else if (isLeftNode) {
                parentNode.left = current.left;
            } else {
                parentNode.right = current.left;
            }
        } else if (current != null) {
            Node nextRootNode = getNextRootNode(current);//即将取代被删除节点的节点.
            if (current == rootNode) {
                rootNode = nextRootNode;
            } else if (isLeftNode) {
                parentNode.left = nextRootNode;
            } else {
                parentNode.right = nextRootNode;
            }

        } else {
            return false;
        }
        return true;
    }

    /**
     * 获取被删除子节点后下一个替代的子节点。
     *
     * @param delNode
     * @return
     */
    private Node getNextRootNode(Node delNode) {
        Node targetParent = delNode;
        Node targetNode = delNode.right;
        Node current = delNode.right;

        if (targetNode.left == null) {//没有左子节点,删除目标节点后整个树结构不需要改变
            targetNode.left = delNode.left; //目标节点的左节点指向被删除目标节点的左节点(右节点不变)
            return targetNode;
        }

        while (current != null) {
            targetParent = targetNode;
            targetNode = current;
            current = current.left;
        }
        targetNode.left = delNode.left; //目标节点的左节点指向被删除目标节点的左节点
        targetNode.right = delNode.right; //目标节点的右节点指向被删除目标节点的右节点
        targetParent.left = targetNode.right; //原本指向目标节点(一定是作为左节点)的指向目标节点的右节点
        return targetNode;
    }


    public static class Node {
        public int data;  //关键字数据域，以int为例子。
        public Node left;
        public Node right;

        public float topx;
        public float leftx;
        public float rightx;
        public float bootomx;
        public int interval;

        public Node(int data) {
            this.data = data;
        }
    }
}
