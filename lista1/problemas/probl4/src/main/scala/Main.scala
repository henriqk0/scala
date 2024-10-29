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
  sealed trait SetResult
  case class StringSet(result: Set[String]) extends SetResult
  case class PowerSet(result: Set[Set[String]]) extends SetResult

  def evaluate_expression(expr: String, sets: Map[String, Set[String]]): SetResult = {

    def union(a: Set[String], b: Set[String]): Set[String] = a union b
    def intersection(a: Set[String], b: Set[String]): Set[String] = a intersect b
    def difference(a: Set[String], b: Set[String]): Set[String] = a diff b
    def symmetric_difference(a: Set[String], b: Set[String]): Set[String] =
      (a diff b) union (b diff a)
    def complement(a: Set[String], all: Set[String]): Set[String] = all diff a
    def cartesian_product(a: Set[String], b: Set[String]): Set[String] = {
      for {
        x <- a
        y <- b
      } yield s"($x, $y)" 
    }
    def to_power_set(a: Set[String]): Set[Set[String]] = {
      (0 to a.size).flatMap(a.subsets).toSet
    }
    val universal_set = sets.values.flatten.toSet
    val tokens = expr.replace("(", " ( ").replace(")", " ) ").split("\\s+").toList
    val output = scala.collection.mutable.Stack[SetResult]()
    val operatorStack = scala.collection.mutable.Stack[String]()

    def apply_operator(operator: String): Unit = {
      operator match {
        case "|" =>
          val b = output.pop()
          val a = output.pop()
          (a, b) match {
            case (StringSet(aSet), StringSet(bSet)) =>
              output.push(StringSet(union(aSet, bSet)))
            case (PowerSet(aPowerSet), PowerSet(bPowerSet)) =>
              output.push(PowerSet(aPowerSet ++ bPowerSet)) // Union of power sets
            case _ =>
              println(s"Unsupported operation for $operator between $a and $b")
          }
        case "&" =>
          val b = output.pop()
          val a = output.pop()
          (a, b) match {
            case (StringSet(aSet), StringSet(bSet)) =>
              output.push(StringSet(intersection(aSet, bSet)))
            case _ =>
              println(s"Unsupported operation for $operator between $a and $b")
          }
        case "-" =>
          val b = output.pop()
          val a = output.pop()
          (a, b) match {
            case (StringSet(aSet), StringSet(bSet)) =>
              output.push(StringSet(difference(aSet, bSet)))
            case (PowerSet(aPowerSet), PowerSet(bPowerSet)) =>
              output.push(PowerSet(aPowerSet -- bPowerSet)) // Difference of power sets
            case (PowerSet(aPowerSet), StringSet(bSet)) =>
              output.push(PowerSet(aPowerSet.filterNot(subset => subset.intersect(bSet).nonEmpty))) // Remove subsets containing elements in bSet
            case _ =>
              println(s"Unsupported operation for $operator between $a and $b")
          }
        case "^" =>
          val b = output.pop()
          val a = output.pop()
          (a, b) match {
            case (StringSet(aSet), StringSet(bSet)) =>
              output.push(StringSet(symmetric_difference(aSet, bSet)))
            case _ =>
              println(s"Unsupported operation for $operator between $a and $b")
          }
        case "~" =>
          val a = output.pop()
          a match {
            case StringSet(aSet) =>
              output.push(StringSet(complement(aSet, universal_set)))
            case _ =>
              println(s"Unsupported operation for ~ on $a")
          }
        case "*" =>
          val b = output.pop()
          val a = output.pop()
          (a, b) match {
            case (StringSet(aSet), StringSet(bSet)) =>
              output.push(StringSet(cartesian_product(aSet, bSet)))
            case _ =>
              println(s"Unsupported operation for $operator between $a and $b")
          }
        case "P" =>
          val a = output.pop()
          a match {
            case StringSet(aSet) =>
              output.push(PowerSet(to_power_set(aSet))) // Push the power set
            case _ =>
              println(s"Unsupported operation for $operator on $a")
          }
        case _ => 
      }
    }


    tokens.foreach {
      case token if sets.contains(token) =>
        output.push(StringSet(sets(token)))
      case token if token.matches("[|&^\\-~*P]") =>
        operatorStack.push(token)
      case "(" =>
        // push in stack
      case ")" =>
        while (operatorStack.nonEmpty) {
          apply_operator(operatorStack.pop())
        }
      case unknownToken if unknownToken.startsWith("~") && sets.contains(unknownToken.tail) =>
        output.push(StringSet(complement(sets(unknownToken.tail), universal_set)))
      case unknownToken if unknownToken.startsWith("P") && unknownToken.length > 2 && unknownToken.endsWith(")") =>
        val setName = unknownToken.substring(2, unknownToken.length - 1)
        if (sets.contains(setName)) {
          output.push(PowerSet(to_power_set(sets(setName))))
        } else {
          println(s"Unknown set for power set: $setName")
        }
      case unknownToken =>
        println(s"Unknown token: $unknownToken")
    }

    while (operatorStack.nonEmpty) {
      apply_operator(operatorStack.pop())
    }

    output.pop()
  }
  
  def main(args: Array[String]): Unit = {
    println("Digite o número de conjuntos:")
    val num_sets = scala.io.StdIn.readLine().toInt

    val sets: Map[String, Set[String]] = {
      val setNames = List("A", "B", "C", "D").take(num_sets)
      setNames.map { name =>
        println(s"Digite o conjunto $name com valores separados por espaço:")
        name -> scala.io.StdIn.readLine().split(" ").toSet
      }.toMap
    }

    println("Digite a expressão de álgebra de conjuntos:")
    val expression = scala.io.StdIn.readLine()

    val result = Try(evaluate_expression(expression, sets)).getOrElse(Set())
    println(s"Resultado: $result")
  }
}
