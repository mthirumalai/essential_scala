trait Equal[A] {
  def equal(v1: A, v2: A) : Boolean
}

/*
implicit val caseInsensitiveEquals = new Equal[String] {
  def equal(v1: String, v2: String) : Boolean =
    v1.toLowerCase() == v2.toLowerCase
}*/
  implicit object StringComparator extends Equal[String] {
    def equal(v1: String, v2: String) : Boolean = {
      v1.equalsIgnoreCase(v2)
    }
  }


/*
implicit class ToEqual[A] (in: A) {
  def === (other: A) (implicit equal: Equal[A]): Boolean =
    equal.equal(in, other)
}
*/
implicit class StringOps(s: String) {
    def ===(t: String)(implicit comparator: Equal[String]) = {
      comparator.equal(s, t)
    }
  }

val x = "abcd" === "ABCD"
val y = "abcd" === "ABCDE"
