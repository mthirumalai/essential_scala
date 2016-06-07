sealed trait Calculation

final case class Success(result: Int) extends Calculation
final case class Failure(message: String) extends Calculation

object Calculation {
  def +(c: Calculation, op2: Int) = {
    c match {
      case Success(op1) => Success(op1 + op2)
      case Failure(msg) => Failure(msg)
    }
  }
  def -(c: Calculation, op2: Int) = {
    c match {
      case Success(op1) => Success(op1 - op2)
      case Failure(msg) => Failure(msg)
    }
  }
}

assert(Calculation.+(Success(1), 1) == Success(2))
assert(Calculation.-(Failure("badness"), 1) == Failure("badness"))
assert(Calculation.-(Success(1), 1) == Success(1))