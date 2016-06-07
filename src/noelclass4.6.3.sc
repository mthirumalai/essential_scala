import scala.annotation.tailrec

sealed trait IntList
final case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

@tailrec
def double(list: IntList) : IntList = {
  list match {
    case End => End
    case Pair(head, tail) => Pair(2*head, double(tail))
  }
}
val example = Pair(1, Pair(2, Pair(3, End)))
assert(double(example) == Pair(2, Pair(4, Pair(6, End))))
assert(double(example) == Pair(2, Pair(4, Pair(7, End))))