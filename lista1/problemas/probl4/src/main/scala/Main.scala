// Crie um programa que calcula o valor de uma expressão da álgebra de conjuntos. O programa 
// deve permitir ao usuário inserir conjuntos (apenas conjuntos definidos por extensão, i.e., listando 
// todos os elementos) e, em seguida, o programa deve receber como entrada uma string que 
// representa a expressão e  o resultado. A expressão em questão poderá usar:
// União (A | B);
// • Interseção (A & B);
// • Diferença (A - B); 
// • Diferença simétrica (A ^ B); 
// • Complemento (~A);2 
// • Produto cartesiano (A * B);
// • Conjunto das partes (P(A)); 

// Exemplos simples: 
// • A | (B & C): União do conjunto A com a interseção dos conjuntos B e C. 
// • (A - B) ^ C: Diferença simétrica entre a diferença de A e B e o conjunto C. 
// • ~(A | B): Complemento da união dos conjuntos A e B.
// • P(A) - P(B): Diferença entre o conjunto das partes de A e o conjunto das partes de B.
// • A * (B | C): Produto cartesiano de A com a união de B e C.
// Exemplos mais complexos:
// • ~((A & B) | (C - D)): Complemento da união entre a interseção de A e B e a diferença de C e D.
// • P(A & B) ^ P(A | B): Diferença simétrica entre o conjunto das partes da interseção de 
//   A e B e o conjunto das partes da união de A e B.
// • (A * B) & (A * C): Interseção do produto cartesiano de A e B com o produto cartesiano de A e C.
// • ~(~A & ~B): Complemento da interseção dos complementos de A e B (equivalente a A | B pela Lei de De Morgan).


import scala.io.StdIn
import scala.util.Try

object SetAlgebra {
  
  def union(a: Set[String], b: Set[String]): Set[String] = a union b
  def intersection(a: Set[String], b: Set[String]): Set[String] = a intersect b
  def difference(a: Set[String], b: Set[String]): Set[String] = a diff b
  def symmetricDifference(a: Set[String], b: Set[String]): Set[String] =
    (a diff b) union (b diff a)
  def complement(a: Set[String], all: Set[String]): Set[String] = all diff a
  def cartesianProduct(a: Set[String], b: Set[String]): Set[String] =
    for {
      x <- a
      y <- b
    } yield s"($x, $y)" // Properly formatted string representation
  def powerSet(a: Set[String]): Set[Set[String]] = {
    // val ps = (0 to a.size).flatMap(a.subsets).toSet
    // println(s"Power set of $a: $ps") // Debug statement
    // ps
    (0 to a.size).flatMap(a.subsets).toSet
  }

  def evaluateExpression(expr: String, sets: Map[String, Set[String]]): Set[String] = {
    val universal_set = sets.values.flatten.toSet
    println(s"Universal set: $universal_set") 
    val tokens = expr.replace("(", " ( ").replace(")", " ) ").split("\\s+").toList
    val output = scala.collection.mutable.Stack[Set[String]]()
    val operatorStack = scala.collection.mutable.Stack[String]()

    def applyOperator(operator: String): Unit = {
      operator match {
        case "|" =>
          val b = output.pop()
          val a = output.pop()
          output.push(union(a, b))
        case "&" =>
          val b = output.pop()
          val a = output.pop()
          output.push(intersection(a, b))
        case "-" =>
          val b = output.pop()
          val a = output.pop()
          output.push(difference(a, b))
        case "^" =>
          val b = output.pop()
          val a = output.pop()
          output.push(symmetricDifference(a, b))
        case "~" =>
          val a = output.pop()
          output.push(complement(a, universal_set))
        case "*" =>
          val b = output.pop()
          val a = output.pop()
          output.push(cartesianProduct(a, b))
        case "P" =>
          val a = output.pop()
          output.push(powerSet(a).flatten.toSet)
        case _ => 
      }
    }

    tokens.foreach {
      case token if sets.contains(token) =>
        output.push(sets(token))
      case token if token.matches("[|&^\\-~*P]") =>
        operatorStack.push(token)
      case "(" =>
        // Just push onto stack
      case ")" =>
        while (operatorStack.nonEmpty) {
          applyOperator(operatorStack.pop())
        }
      case unknownToken if unknownToken.startsWith("~") && sets.contains(unknownToken.tail) =>
        output.push(complement(sets(unknownToken.tail), universal_set))
      case unknownToken if unknownToken.startsWith("P") && unknownToken.length > 2 && unknownToken.endsWith(")") =>
        val setName = unknownToken.substring(2, unknownToken.length - 1)
        if (sets.contains(setName)) {
          output.push(powerSet(sets(setName)).flatten.toSet)
        } else {
          println(s"Unknown set for power set: $setName")
        }
      case unknownToken =>
        println(s"Unknown token: $unknownToken")
    }

    while (operatorStack.nonEmpty) {
      applyOperator(operatorStack.pop())
    }

    output.pop()
  }

  // Main program execution
  def main(args: Array[String]): Unit = {
    println("Digite o número de conjuntos:")
    val num_sets = scala.io.StdIn.readLine().toInt

    // Read sets from the user
    val sets: Map[String, Set[String]] = {
      val setNames = List("A", "B", "C", "D").take(num_sets)
      setNames.map { name =>
        println(s"Digite o conjunto $name com valores separados por espaço:")
        name -> scala.io.StdIn.readLine().split(" ").toSet
      }.toMap
    }

    // Read the expression to evaluate
    println("Digite a expressão de álgebra de conjuntos:")
    val expression = scala.io.StdIn.readLine()

    // Evaluate the expression and print the result
    val result = Try(evaluateExpression(expression, sets)).getOrElse(Set())
    println(s"Resultado: $result")
  }
}
