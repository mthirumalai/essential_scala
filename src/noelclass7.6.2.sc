object IntImplicit {
  implicit class IntOps(data: Int) {
    def yeah() = {
      times(_ => println("yeah"))
    }
    def times(func: Int=> Unit) = {
      for (i <- 0 until data)func(i)
    }
  }
}

import IntImplicit._
2.yeah
3.yeah
5.times(i=>println(s"look it is number $i"))

