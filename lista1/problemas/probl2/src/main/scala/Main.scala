import scala.annotation.tailrec

@tailrec
def well_formed_parenthesis(s: String, rest:Int = 0, i: Int = 0): String = {
  if (s.length == i) {
    if (rest == 0) {
      "A expressão de parêntesis está bem formada."
    } else {
        if (rest > 0)
          s"A expressão de parêntesis não está bem formada ($rest parentesis abertos a mais)."
        else
          s"A expressão de parêntesis não está bem formada (${math.abs(rest)} parentesis fechados a mais)."
      }
  }
  else {
    if (s.charAt(i) == '(')
      well_formed_parenthesis(s, rest+1, i+1)
    else 
      if (s.charAt(i) == ')') 
        well_formed_parenthesis(s, rest-1, i+1)
      else 
        well_formed_parenthesis(s, rest, i+1)
  }  
}



@main def Main() = {
  println("Digite uma expressão de parêntesis:")
  val parenthesis_str = scala.io.StdIn.readLine()
  val check_wellFormed = well_formed_parenthesis(parenthesis_str)
  println(s"$check_wellFormed")
}
