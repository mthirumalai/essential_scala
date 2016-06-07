class Person(val firstName: String, val lastName: String = "") {
  val name = s"$firstName $lastName"
}
object Person {
  def apply(fullName: String) = {
    val parts = fullName.split(" ");
    parts.length match {
      case 1 => new Person(parts(0))
      case _ => new Person(parts(0), parts(parts.length-1))
    }
  }
}
Person("dunga").name
Person("nila thirunalai").name
Person("nilasha rahini thirumalai").name