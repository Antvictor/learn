package antvictor.study.map.tree;

/**
 * @author exccedy
 * @date 2021/12/13
 **/
public class TreeUtils {
    public static void preorderTraversal(TreeNode node) {
        if (null == node) {
           return;
        }
        System.out.print(node.value);
        preorderTraversal(node.leftChild);
        preorderTraversal(node.rightChile);
    }

    public static void inorderTraversal(TreeNode node) {
        if (null == node) {
            return;
        }
        inorderTraversal(node.leftChild);
        System.out.print(node.value);
        inorderTraversal(node.rightChile);
    }

    public static void postorderTraversal(TreeNode node) {
        if (null == node) {
            return;
        }
        postorderTraversal(node.leftChild);
        postorderTraversal(node.rightChile);
        System.out.print(node.value);
    }
}
