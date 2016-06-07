case class Director(firstName: String, lastName: String,yearOfBirth: Int, films: Seq[Film]) {
  def name: String = s"$firstName $lastName"
}

case class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double)

val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
val unforgiven = new Film("Unforgiven", 1990, 8.0)
val granTorino = new Film("Gran Torino", 1991, 9.0)

val eastwood = new Director("Client", "Eastood", 1930, Seq(highPlainsDrifter, unforgiven, granTorino))

val predator = new Film("Predator", 1992, 5.0)
val dieHard = new Film("Die Hard", 1993, 6.0)

val someGuy = new Director("Some", "Guy", 1900, Seq(predator, dieHard))

val directors = Seq(eastwood, someGuy)

def findDirectors(numberOfFilms: Int): Seq[Director] = directors.filter(_.films.length == numberOfFilms)
findDirectors(2)
findDirectors(3)
findDirectors(1)
def bornBefore(year: Int): Option[Director] = directors.find(_.yearOfBirth < year)
bornBefore(1991)
bornBefore(1989)
bornBefore(1929)
bornBefore(1899)
def sort(ascending: Boolean = true) = directors.sortWith (
                    (a, b) => ascending match {
                      case true => a.yearOfBirth < b.yearOfBirth
                      case false => a.yearOfBirth > b.yearOfBirth
                    } )
sort()
sort(false)

eastwood.films.map(_.name)
directors.flatMap(_.films.map(_.name))
for {
  d <- directors
  f <- d.films
} yield f.name

def earliestFilm(d: Director) = {
  d.films.foldLeft(Int.MaxValue) { (current: Int, film: Film) => current.min(film.yearOfRelease) }
}
earliestFilm(eastwood)
earliestFilm(someGuy)

/* average imdb */
directors.flatMap(_.films).sortBy(_.imdbRating)
val allFilms = directors.flatMap(_.films)
val sumImdb = allFilms.foldLeft (0.0) { (current: Double, film: Film) => current + film.imdbRating }
sumImdb / allFilms.length

/* Tonight's listings */
directors.foreach {
  d => d.films.foreach {
    f => println(s"Tonight only ${f.name} by ${d.name}")
  }
}
directors.map { d =>
  d.films.foldLeft(
    Film("dummy", Int.MaxValue, 0.0))
    { (current: Film, film: Film) => if (current.yearOfRelease < film.yearOfRelease) current else film }
}

for {
  d <- directors
} yield (d.name, d.films.sortWith((a, b) => a.yearOfRelease < b.yearOfRelease).head)

directors.head

/*
directors.map(d => d.films.foldLeft(None, (current: Option[Film], film: Film) => current match {
  case None => Some(film)
  case Some(f) => if (f.yearOfRelease < film.yearOfRelease) f else film
}))
*/


