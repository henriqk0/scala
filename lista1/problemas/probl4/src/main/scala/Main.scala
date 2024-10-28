// Crie um programa que calcula o valor de uma expressão da álgebra de conjuntos. O programa 
// deve permitir ao usuário inserir conjuntos (apenas conjuntos definidos por extensão, i.e., listando 
// todos os elementos) e, em seguida, o programa deve receber como entrada uma string que 
// representa a expressão e retornar o resultado. A expressão em questão poderá usar:
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


import scala.annotation.tailrec


@main def Main() = {
  println("Digite o primeiro conjunto:")
  val num_orig = scala.io.StdIn.readLine()
  println("Digite uma expressão")
  val base_orig = scala.io.StdIn.readLine()
  val conveted_base = convert_to_another_base(num_orig,base_orig, base_to_conv)
  println(s"$")
}
