sealed trait MyLinkedList[A] {
  def length: Int
  def contains(el: A): Boolean
  def apply(n: Int) : Option[A] = this match {
      case End() => None
      case Pair(head, tail) => if (n == 0) Option(head) else tail(n-1)
  }
  def fold(f: (A, A)=>A, end: A): A =
    this match    {
      case End()=> end
      case Pair(head, tail) => f(head, tail.fold(f, end))
    }
  def map[B](f: A=>B): MyLinkedList[B] = {
    this match {
      case End() => End[B]()
      case Pair(head, tail) => Pair(f(head), tail.map(f))
    }
  }
}
final case class Pair[A](head: A, tail: MyLinkedList[A]) extends MyLinkedList[A] {
  lazy val length = 1 + tail.length
  def contains(el: A) = head.equals(el) || tail.contains(el)
}
final case class End[A]() extends MyLinkedList[A] {
  val length = 0
  def contains(el: A) = false
}
sealed trait IntList extends MyLinkedList[Int] {
  def sum = fold((x, y)=> x + y, 0)
  def product = fold((x, y) => x * y, 1)
  def length = fold((_, y) => 1+y, 0)
}


val example: MyLinkedList[Int] = Pair(1, Pair(2, Pair (3, End())))
example.fold((x, y) => x+y, 0)
example.map(_.toString() + "x")
example.map(_*2)
example.map(_+1)
example.map(_/3)
/*
val example2: IntList = Pair(1, Pair[Int](2, Pair[Int] (3, End[Int]())))
example2.sum
example2.product
*/
example.length
example.contains(1)
example.contains(3)
example.contains(4)
example(0)
example(2)
example(3)

