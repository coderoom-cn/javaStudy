package cn.coderoom.datastructure.tree.binarysorttree;

public class BinarySortTree {

    Node rootNode;

    public  void preIterator(){
        rootNode.preIterator();
    }

    /**
     * 节点的插入
     * @param node
     */
    public void insertNode(Node node){
        if (rootNode == null) {
            rootNode = node;
        }else{
            rootNode.insertNode(node);
        }
    }

    /**
     * 节点查询
     * @param value
     * @return
     */
    public Node searchNode(int value){
        return rootNode.searchNode(value);
    }

    public Node searchParent(int value){
        return rootNode.searchParent(value);
    }

    /**
     * 节点的删除
     * @param vaule
     */
    public void delNode(int vaule){
        if (rootNode == null) {
            return;
        }else {
            Node targetNode = searchNode(vaule);
            if (targetNode == null) {
                return;
            }
            Node parentNode = searchParent(vaule);
            // 判断要删除的节点是什么节点
            // 要删除节点是叶子节点
            if (targetNode.leftNode==null&&targetNode.rightNode==null){
                // 判断要删除的节点是父节点的左子节点还是父节点的右子节点
                if (parentNode.leftNode.value == vaule){
                    parentNode.leftNode = null;
                }else {
                    parentNode.rightNode = null;
                }
                // 要删除节点存在两个子节点
            }else if (targetNode.leftNode!=null&&targetNode.rightNode!=null){
                // 删除并获取右侧最小节点
                int min = delMin(targetNode.rightNode);
                // 替换
                targetNode.value = min;

                // 要删除的节点存在一个叶子节点
            }else {
                // 判断当前节点含有左子节点还是右子节点
                if (targetNode.leftNode!=null){
                    if (parentNode.leftNode.value == vaule){
                        parentNode.leftNode = targetNode.leftNode;
                    }else {
                        parentNode.rightNode = targetNode.leftNode;
                    }
                }else{
                    if (parentNode.rightNode.value == vaule){
                        parentNode.leftNode = targetNode.rightNode;
                    }else {
                        parentNode.rightNode = targetNode.rightNode;
                    }
                }
            }
        }
    }


    public int delMin(Node node){
        Node targetNode = node;
        while(targetNode.leftNode!=null){
            targetNode = targetNode.leftNode;
        }
        delNode(targetNode.value);
        return targetNode.value;
    }
}

