case class Person(name: String, email: String)

trait Equal[A] {
  def equal(v1: A, v2: A) : Boolean
}

object NE {
  implicit object NameEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person) =
      v1.name == v2.name
  }
}

object EE {
  implicit object EmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person) =
      v1.email == v2.email
  }

}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Approach 1  implicit parameter lists

object Eq {
  def apply[A](v1: A, v2: A) (implicit equals: Equal[A]): Boolean = {
    equals.equal(v1, v2)
  }
}


object Examples {
  def E1 = {

    import EE._

    //Eq[Person](Person("Madhavan", "mthirumalai@gmail.com"), Person("Madhavan", "mthirumalai@gmail.com"))
    Eq(Person("Madhavan", "mthirumalai@gmail.com"), Person("Madhavan2", "mthirumalai@gmail.com"))
    //Eq(Person("Madhavan", "mthirumalai@gmail.com"), Person("Madhavan2", "mthirumalai2@gmail.com"))
  }

  def E2 = {

    import NE._

    Eq(Person("Madhavan", "mthirumalai@gmail.com"), Person("Madhavan", "mthirumalai2@gmail.com"))
  }

}
Examples.E1
Examples.E2

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Approach 2 companion object pattern

object Equal {
  def apply[A](implicit instance: Equal[A]): Equal[A] = {
    instance
  }
}

object Examples3 {
  def E3 = {
    import EE._
    Equal[Person].equal(Person("Madhavan", "mthirumalai@gmail.com"), Person("Madhavan2", "mthirumalai@gmail.com"))
  }
}
Examples3.E3