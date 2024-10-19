import scala.annotation.tailrec

@tailrec
def fact_tailrec(n: Int, acc: Int = 1): Int = {
  if (n == 0) 
    acc
  else 
    fact_tailrec(n - 1, n * acc)
}

@main def Funcs() = {
  val factsTails = for (i <- 0 to 7) yield fact_tailrec(i)
  println(s"Factorials (tailrec) => $factsTails")
}

