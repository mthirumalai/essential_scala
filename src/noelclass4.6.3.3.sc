

sealed trait Calculation
final case class Success(value: Double) extends Calculation
final case class Failure(message: String) extends Calculation
sealed trait Expression {
  def eval: Calculation
}
final case class Addition(left: Expression, right: Expression) extends Expression {
  val eval: Calculation = left.eval match {
    case Failure(message) => Failure(message)
    case Success(value) => right.eval match {
      case Failure(message2) => Failure(message2)
      case Success(value2) => Success(value + value2)
    }
  }
}
final case class Number(value: Double) extends Expression {
  val eval = Success(value)
}
/*
final case class Subtraction(left: Expression, right: Expression) extends Expression {
  val eval = left.eval - right.eval
}
*/
final case class Division(left: Expression, right: Expression) extends Expression {
  val eval = left.eval match {
    case Failure(message) => Failure(message)
    case Success(value) => right.eval match {
      case Failure(message2) => Failure(message2)
      case Success(value2) => value2 match {
        case 0.0 => Failure(s"dividing $value by 0")
        case _ => Success(value/value2)
      }
    }
  }
}
val message : String = Division(Number(1), Number(0)).eval match {
  case Failure(message) => message
  case Success(value) => value.toString
}
assert(Addition(Number(1), Number(2)).eval == Success(3))
assert(Division(Number(1), Number(0)).eval == Failure("dividing 1.0 by 0"))