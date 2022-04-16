package cn.coderoom.datastructure.tree.binarysorttree;

/**
 *
 * 二叉排序树：将节点依次插入，大于当前节点放在右侧，小于当前节点放在左侧。
 *
 */
public class Node {
    int value;
    Node leftNode;
    Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    public void preIterator(){
        System.out.print(this.value);
        if (this.leftNode !=null) {
            this.leftNode.preIterator();
        }
        if (this.rightNode!=null) {
            this.rightNode.preIterator();
        }
    }


    /**
     * 添加节点
     * 大于当前节点放在右侧，小于当前节点放在左侧。
     * @param rootNode
     */
    public void insertNode(Node rootNode){
        if (rootNode == null) {

            return;
        }

        if (this.value>rootNode.value){
            if (this.leftNode==null){
                this.leftNode = rootNode;
            }else {
                this.leftNode.insertNode(rootNode);
            }
        }else{
            if (this.rightNode == null){
                this.rightNode = rootNode;
            }else {
                this.rightNode.insertNode(rootNode);
            }
        }
    }

    /**
     * 查找节点
     * @param value
     * @return
     */
    public Node searchNode(int value){
        if (this.value == value){
            return this;
        }else if (this.value>value){
            if (this.leftNode ==null) {
                return null;
            }
            return this.leftNode.searchNode(value);
        }else if (this.value<value){
            if (this.rightNode==null) {
                return null;
            }
            return this.rightNode.searchNode(value);
        }else {
            return null;
        }
    }

    /**
     * 查找当前节点的父节点
     * @return
     */
    public Node searchParent(int value){
        if (this.leftNode!=null&&this.leftNode.value==value||this.rightNode!=null&&this.rightNode.value==value){
            return this;
        }else if (this.value>value){
            if (this.leftNode == null) {
                return null;
            }
            return this.leftNode.searchParent(value);
        }else if (this.value<value){
            if (this.rightNode==null) {
                return null;
            }
            return this.rightNode.searchParent(value);
        }else return null;
    }
}

