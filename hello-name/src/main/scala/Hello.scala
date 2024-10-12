@main def helloName() =
  println("Qual é o seu nome?")
  val nome = scala.io.StdIn.readLine()
  println(s"Olá, $nome!")
