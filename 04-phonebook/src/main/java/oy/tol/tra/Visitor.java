package oy.tol.tra;

interface Visitor<K extends Comparable<K>, V> {
     void visit(TreeNode<K,V> node);
 }
