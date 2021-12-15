package yangchao.study.map.tree;

import lombok.Data;

/**
 * @author exccedy
 * @date 2021/12/13
 **/
@Data
public class TreeNode {
    public String value;
    TreeNode leftChild;
    TreeNode rightChile;

    public TreeNode(String value) {
        this.value = value;
    }

}
