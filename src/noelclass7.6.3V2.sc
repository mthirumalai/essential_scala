trait Equal[A] {
  def equal(v1: A, v2: A) : Boolean
}
object Equal {
  //def apply[A](implicit instance: Equal[A]) : Equal[A] = instance
  implicit class ToEqual[A] (in: A) {
    def === (other: A) (implicit equal: Equal[A]): Boolean =
      equal.equal(in, other)
  }
}


implicit val caseInsensitiveEquals = new Equal[String] {
  def equal(v1: String, v2: String) : Boolean =
    v1.toLowerCase() == v2.toLowerCase
}

import Equal._

"abcd".===("ABCD")
