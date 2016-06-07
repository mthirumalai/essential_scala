class Director(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  def name: String = s"$firstName $lastName"
}
object Director {
  def apply(firstName: String, lastName: String, yearOfBirth: Int) = {
    new Director(firstName, lastName, yearOfBirth)
  }
  def older(x : Director, y: Director) = {
    if (x.yearOfBirth > y.yearOfBirth) x else y
  }
}

class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double, val director: Director) {
  def directorsAge = yearOfRelease - director.yearOfBirth
  def isDirectedBy(candidate: Director) = director == candidate
  def copy(name: String = this.name, yearOfRelease: Int = this.yearOfRelease, imdbRating: Double = this.imdbRating, director: Director = this.director) = new Film(name, yearOfRelease, imdbRating,director)
}
object Film {
  def apply(name: String, yearOfRelease: Int, imdbRating: Double, director: Director) =
    new Film(name, yearOfRelease, imdbRating, director)
  def oldestDirectorAtTheTime(x: Film, y: Film) =
    if (x.directorsAge > y.directorsAge) x else y
}

val eastwood = new Director("Client", "Eastood", 1930)
val eastwood2 = new Director("Client", "Eastood", 1930)
eastwood.name

val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7, eastwood)
highPlainsDrifter.directorsAge
highPlainsDrifter.isDirectedBy(eastwood)
highPlainsDrifter.isDirectedBy(eastwood2)
highPlainsDrifter.copy(yearOfRelease = 1975, name = "l'homme des hautes plaines")

class Adder(amount: Int) {
  def add(in: Int) = in + amount
}
class Counter(val count: Int) {
  def dec : Counter = dec()
  def inc : Counter = inc()

  def dec(by: Int = 1) = new Counter(count-by)
  def inc(by: Int = 1) = new Counter(count+by)
  def adjust(adder: Adder) : Counter = new Counter(adder.add(count))
}

new Counter(10).inc.dec.inc.inc(10).count






