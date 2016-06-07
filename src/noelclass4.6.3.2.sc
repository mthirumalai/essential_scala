sealed trait Node {
  def sum: Int
}
final case class Leaf(element: Int) extends Node {
  val sum = element
}
final case class Tree(left: Node, right: Node) extends Node {
  lazy val sum = left.sum + right.sum
}

object TreeOps {
  def sum(node: Node) : Int = {
    node match {
      case Leaf(element) => element
      case Tree(left, right) => sum(left) + sum(right)
    }
  }
}