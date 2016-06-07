sealed trait Sum[+A, +B] {
  def map[C] (f: B=>C): Sum[A, C] = {
    this match {
      case Failure(a) => Failure(a)
      case Success(b) => Success(f(b))
    }
  }
  def flatMap[AA >: A, C] (f: B=> Sum[AA, C]) : Sum[AA, C] = {
    this match {
      case Failure(a) => Failure(a)
      case Success(b) => f(b)
    }
  }
}
final case class Failure[A, B](value: A) extends Sum[A, B]
final case class Success[A, B](value: B) extends Sum[A, B]