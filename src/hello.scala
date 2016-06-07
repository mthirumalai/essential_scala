/**
  * Created by mthirumalai on 11/16/15.
  */
class Cat(val name: String, val color: String, val food: String)
object chipShop {
  def willServe(cat: Cat) = cat.food.equalsIgnoreCase("chips")
}
object hello {
  def main(args: Array[String]) {
    println("Hello, world!")
  }
  val oswald = new Cat("Oswald", "Black", "Milk")
  val henderson = new Cat("Henderson", "Ginger", "Chips")
  println(chipShop.willServe(oswald))
  println(chipShop.willServe(henderson))
}




