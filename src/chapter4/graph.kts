import java.util.concurrent.ConcurrentLinkedQueue

data class MyGraph<T> {
    val nodes: List<Node<T>> = listOf()

    data class Node<T>(var data: T, val adjacentNodes: List<Node<T>> = listOf()) {
        var hasVisited = false
    }

    private fun markUnvisited(): Unit = nodes.forEach { it.hasVisited = false }

    fun printDepthFirstSearch() {
        markUnvisited()
        val search = { node: Node<T> ->
            print("${node.data} ")
            node.hasVisited = true
            node.adjacentNodes.forEach { if (!it.hasVisited) search(it) }
        }
        nodes.forEach(::search)
    }

    fun printBreadthFirstSearch() {
        markUnvisited()
        val queue = ConcurrentLinkedQueue<GraphNode<T>>()
        node.hasVisited = true
        queue.add(node)
        while (!queue.isEmpty()) {
            val current = queue.poll()
            for (adjacentNode in current.adjacentNodes)
                if (!adjacentNode.hasVisited) {
                    adjacentNode.hasVisited = true
                    queue.add(adjacentNode)
                }
        }
    }
}
