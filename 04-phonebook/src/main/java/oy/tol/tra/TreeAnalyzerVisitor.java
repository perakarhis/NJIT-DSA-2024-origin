package oy.tol.tra;

class TreeAnalyzerVisitor<K extends Comparable<K>, V> implements Visitor<K, V> {

	int minHeight = Integer.MAX_VALUE;
	int maxHeight = Integer.MIN_VALUE;
	int currentHeight = 0;

	TreeAnalyzerVisitor() {
		// Nada
	}

	@Override
	public void visit(TreeNode<K, V> node) {

		if (null == node.left && null == node.right) {
			minHeight = Math.min(minHeight, currentHeight);
			maxHeight = Math.max(maxHeight, currentHeight);
		} else {
			if (node.left != null) {
				currentHeight++;
				node.left.accept(this);
			}
			if (node.right != null) {
				currentHeight++;
				node.right.accept(this);
			}	
		}
		currentHeight--;
	}

}
