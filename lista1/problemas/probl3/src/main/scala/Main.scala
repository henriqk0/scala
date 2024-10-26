import scala.annotation.tailrec


@tailrec
def is_perf_number(num: Int, divs: Set[Int] = Set(), actual_div: Int=1): String = {
  if (actual_div >= num) {
    if (divs.sum == num && num != 0) {
      s"$num é um número perfeito." 
    }
    else {
      s"$num não é um número perfeito."
    }
  }
  else {
    is_perf_number(num, 
      if (num % actual_div == 0) divs+actual_div else divs,
      actual_div+1)
  }
}
  

@main def Main() = {
  println("Digite um inteiro positivo:")
  val intNumber = scala.io.StdIn.readInt()
  val check_perf_num = is_perf_number(intNumber)
  println(s"$check_perf_num")
}
