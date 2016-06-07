
sealed trait DivisionResult
case class Finite(val value: Int) extends DivisionResult
case object Infinite extends DivisionResult

final object divide {
  def apply(numerator: Int, denominator: Int): DivisionResult =
    if (denominator == 0) Infinite else Finite(numerator/denominator)
}

divide(1, 1)
divide(1, 0)

