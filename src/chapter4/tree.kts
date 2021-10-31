data class MyBinarySearchTree<T>(var root: Node<T>? = null) {
    data class Node<T>(var data: T, var left: Node<T>? = null, var right: Node<T>? = null)
}

fun <T> printInOrderTraversal(node: MyBinarySearchTree.Node<T>?) {
    if (node == null) return
    preOrderTraverse(node.left)
    print("${node.data} ")
    preOrderTraverse(node.right)
}

fun <T> printPreOrderTraversal(node: MyBinarySearchTree.Node<T>?) {
    if (node == null) return
    print("${node.data} ")
    preOrderTraverse(node.left)
    preOrderTraverse(node.right)
}

fun <T> printPostOrderTraversal(node: MyBinarySearchTree.Node<T>?) {
    if (node == null) return
    preOrderTraverse(node.left)
    preOrderTraverse(node.right)
    print("${node.data} ")
}
