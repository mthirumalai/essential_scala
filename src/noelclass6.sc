val seq = Seq(1, 2, 3)
seq(0)
seq(2)
//seq(3)
seq.head
seq.tail
Seq()
Seq().headOption
seq.headOption
seq.length
seq.contains(2)
seq.find(_==3)
seq.find(_>4)
seq.filter(_>1)
seq.sortWith(_<_)
seq.sortWith(_>_)

seq.:+(4)
4 +: seq
seq +: Seq(4, 5, 6)
seq ++ Seq(4, 5, 6)

Nil

val list = 1 :: 2 :: 3 :: Nil

4 :: 5 :: list
4 +: 5 +: list

List(1, 2, 3) :: List(4, 5, 6)
List(1 , 2, 3) ::: List(4, 5, 6)




