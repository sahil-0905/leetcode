/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    static class Info {
        int dia;
        int h;

        public Info(int dia, int h){
            this.dia = dia;
            this.h= h;
        }
    }
    public static Info diameter(TreeNode root){
        if(root == null){
            return new Info(0, 0);
        }
        Info leftInfo = diameter(root.left);
        Info rightInfo = diameter(root.right);
        int diam = Math.max(Math.max(leftInfo.dia, rightInfo.dia), leftInfo.h + rightInfo.h + 1);
        int ht = Math.max(leftInfo.h, rightInfo.h) + 1;

        return new Info(diam, ht);
    }
    public int diameterOfBinaryTree(TreeNode root) {
        return diameter(root).dia - 1 ;
    }
}