sealed trait Tree[A] {
  def fold[B]( leaf: (A)=>B, node: (B, B)=>B): B = {
    this match {
      case Leaf(element) => leaf(element)
      case Node(left, right) =>  node(left.fold(leaf, node), right.fold(leaf, node))
    }
  }
}
sealed case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
sealed case class Leaf[A](element: A) extends Tree[A]

val tree: Tree[String] = Node(Node(Leaf("to"), Leaf("err")),
                              Node(Node(Leaf("is"), Leaf("human")),
                                    Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

tree.fold( (a: String) => a, (a: String, b: String) => a + " " + b)

val tree2: Tree[Int] = Node(Node(Leaf(1), Leaf(2)),
                            Node(Leaf(3), Leaf(4)))
tree2.fold( (a: Int) => a.toString, (a: String, b: String) => a + "x" + b)

final case class Pair[A, B](one: A, two: B)
val pair = Pair("hi", 1)
pair.one
pair.two

trait Sum[A, B] {
  def fold[C](left: A=> C, right: B=>C) = {
    this match {
      case Left(value) => left(value)
      case Right(value) => right(value)
    }
  }
}
final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]

Left(1).value
Right("foo").value

val sum: Sum[Int, String] = Right("foo")
sum match {
  case Left(x) => x.toString
  case Right(y) => y
}


sealed trait Maybe[A] {
  def fold[B](empty:B, full: A=>B): B = {
    this match {
      case Empty() => empty
      case Full(value) => full(value)
    }
  }
  def Map[B](f: A=> B): Maybe[B] = {
    this match {
      case Empty() => Empty[B]()
      case Full(value) => Full(f(value))
    }
  }
  def flatMap[B](f: A=> Maybe[B]): Maybe[B] = {
    this match {
      case Empty()=>Empty[B]()
      case Full(value) => f(value)
    }
  }
  /*
  def Map2[B](f: A=>B): Maybe[B] = flatMap[B] {
      f(_) match {
        case Empty
      }
  }
  */
}
final case class Empty[A]() extends Maybe[A]
final case class Full[A](value: A) extends Maybe[A]
val perhaps: Maybe[Int] = Empty[Int]
val perhaps2: Maybe[Int] = Full(1)
val p1 = perhaps.fold(-1, _+1)
val p2 = perhaps2.fold(-1, _+1)
val foo = Tuple2(1, "one")
val bar = Tuple3("one", "two", 0)

val list = List(Full(3), Full(2), Full(1))
list.map[Maybe[Int]] { full => if(full.value % 2 == 0) Full[Int](full.value) else Empty[Int]()}
list.map(  full => full.flatMap( x => if (x%2 == 0) Full(x) else Empty[Int]()))
//list.flatMap( full=> if (full.value %2 == 0) Full(full.value) else Empty[Int]())
