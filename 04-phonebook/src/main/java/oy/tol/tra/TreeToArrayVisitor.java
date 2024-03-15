package oy.tol.tra;

class TreeToArrayVisitor<K extends Comparable<K>, V> implements Visitor<K, V> {

   private Pair<K, V> [] elements;
   private int count = 0;

   @java.lang.SuppressWarnings({"unchecked"})
   public TreeToArrayVisitor(int count) {
      elements = (Pair<K,V>[])new Pair[count];
      count = 0;
   }

   @Override
   public void visit(TreeNode<K, V> node) {
      if (node.left != null) {
         node.left.accept(this);
      }
      elements[count++] = new Pair<>(node.keyValue.getKey(), node.keyValue.getValue());
      if (node.list != null) {
         for (int index = 0; index < node.list.size(); index++) {
            Pair<K,V> item = node.list.get(index);
            elements[count++] = new Pair<K, V>(item.getKey(), item.getValue());
         }
      }
      if (node.right != null) {
         node.right.accept(this);
      }
   }

   Pair<K, V> [] getArray() {
      return elements;
   }
   
}
