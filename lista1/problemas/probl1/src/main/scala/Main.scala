import scala.annotation.tailrec


@tailrec
def convert_to_rec_aux(num_int: Int, base_to_conv: Int, final_str: String = "",
  base_chars: String = "0123456789ABCDEFGHIJ"): String = {
  
  if (num_int == 0) {
    if (final_str.isEmpty) "0" else final_str
  } else {
    convert_to_rec_aux(num_int / base_to_conv, base_to_conv,
    base_chars.charAt(num_int % base_to_conv) + final_str)
  }
}

def convert_to_another_base(num_original: String,
  original_base: Int, base_to_conv: Int,
  values: Map[Char, Int] = "0123456789ABCDEFGHIJ".zipWithIndex.toMap) : String = {

  if (!num_original.forall(c => values.contains(c) && values(c) < original_base)) {
    throw new IllegalArgumentException(s"Há caracteres inválidos para base($original_base) em $num_original")
  }

  val num_int = num_original.reverse.zipWithIndex.map { case (char, index) =>
    values(char) * math.pow(original_base, index).toInt
  }.sum
  
  val num_finally_conv = convert_to_rec_aux(num_int, base_to_conv)
  s"base$original_base($num_original) = base$base_to_conv($num_finally_conv)"
}

@main def Main() = {
  println("Digite um número:")
  val num_orig = scala.io.StdIn.readLine()
  println("Digite sua base (2, 8, 10, 12, 16 ou 20)")
  val base_orig = scala.io.StdIn.readInt()
  println("Digite a base para se converter (2, 8, 10, 12, 16 ou 20)")
  val base_to_conv = scala.io.StdIn.readInt()
  val conveted_base = convert_to_another_base(num_orig,base_orig, base_to_conv)
  println(s"$conveted_base")
}
