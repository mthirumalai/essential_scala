sealed trait Sum[+A, +B] {
  def fold[C](failure: A=>C, success: B=> C): C =
    this match {
      case Failure(a) => failure(a)
      case Success(b) => success(b)
    }
  def map[C] (f: B=>C): Sum[A, C] = {
    this match {
      case Failure(a) => Failure(a)
      case Success(b) => Success(f(b))
    }
  }
  def flatMap[AA>: A, C] (f: B=> Sum[AA, C]) : Sum[AA, C] = {
    this match {
      case Failure(a) => Failure(a)
      case Success(b) => f(b)
    }
  }
}
final case class Failure[A, B](value: A) extends Sum[A, B]
final case class Success[A, B](value: B) extends Sum[A, B]




sealed trait Expression {
  def eval: Sum[String, Double]
}

final case class Addition(left: Expression, right: Expression) extends Expression {
  val eval = left.eval.flatMap (
    x => right.eval.flatMap()
  )

  /*
  val eval = left.eval match {
    case Failure(message) => Failure(message)
    case Success(value) => right.eval match {
      case Failure(message2) => Failure(message2)
      case Success(value2) => Success(value + value2)
    }
  }
  */
}
final case class Number(value: Double) extends Expression {
  val eval = Success(value)
}
/*
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

*/