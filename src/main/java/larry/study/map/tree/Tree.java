package larry.study.map.tree;


/**
 * @author exccedy
 * @date 2021/12/13
 **/
public class Tree {

    public static void main(String[] args) {
        createTree();
    }

    public static void createTree() {
        TreeNode a = new TreeNode("A"),
                b = new TreeNode("B"),
                c = new TreeNode("C"),
                d = new TreeNode("D"),
                e = new TreeNode("E"),
                f = new TreeNode("F"),
                g = new TreeNode("G"),
                h= new TreeNode("H");
        a.leftChild = b;
        a.rightChile = c;
        b.leftChild = d;
        c.leftChild = e;
        c.rightChile = f;
        d.rightChile = g;
        g.rightChile = h;

        //                    a
        //          b                      c
        // d                           e        f
        //    g
        //       h

        // nlr: preorder traversal 根 --> 左 --> 右 前序查询
        System.out.print("Preorder Traversal: ");
        TreeUtils.preorderTraversal(a);
        System.out.println();
        // lnr: inorder traversal 左 --> 根 --> 右 中序查询
        System.out.print("Inorder Traversal: ");
        TreeUtils.inorderTraversal(a);
        System.out.println();
        // lnr: postorder traversal 左 --> 右 --> 根 后序查询
        System.out.print("Postorder Traversal: ");
        TreeUtils.postorderTraversal(a);
        System.out.println();


        //Preorder Traversal: ABDGHCEF
        //Inorder Traversal: DGHBAECF
        //Postorder Traversal: HGDBEFCA
    }

}
