# Lista 1 -- Teoria da Computação / Linguagens Formais e Autômatos -- 2024/2

**Introdução à Teoria da Computação & Fundamentos Matemáticos**

**Autor:** Henrique de Souza Lima

## Problema 4

Este problema pede a construção de um programa que calcula o valor de uma expressão da álgebra de conjuntos. O programa
deve permitir ao usuário inserir conjuntos (apenas conjuntos definidos por extensão, i.e., listando
todos os elementos) e, em seguida, o programa deve receber como entrada uma string que
representa a expressão e retornar o resultado.

**Solução**

A solução adotada foi adotar uma classe SetAlgebra que contém um traço (sealed trait) chamado SetResult, que é
estendido por StringSet e PowerSet, para permitir resultados em forma de Sets de strings ou Sets de Sets de Strings, como P(A). 
O programa possui funções para cada operação de conjuntos, sendo chamadas de acordo com a ocorrência de caracteres definidos, 
(Diferença, Conjunto das Partes, etc.), cuja assinatura, em inglês, corresponde ao seu produto. A função 
evaluate_expression analisa uma expressão em notação de infixos a partir recebida como uma String, que é convertida em uma
lista de  tokens e avaliada segundo a precedência devida. Para cada token, se for um conjunto definido pelo usuário, 
ele é empilhado; se for um operador, as operações correspondentes são aplicadas aos conjuntos no topo da pilha. 
Para expressões mais complexas, fez-se utilização de uma pilha de operadores de modo que as operações ocorram na ordem devida
para fazer a aplicação das operações das expressões entre parêntesis. 
Os parâmetros são pedidos no o programa principal. Primeiro, a quantidade de conjuntos (até 4, nomeados de A a D); em seguida, 
lerá uma linha dos elementos do conjunto respectivo -- obtidos através da separação por espaço em branco entre 
caracteres (um split), seguido de toSet. 

**Execução**

Para executar a função no prompt do Scala execute, por exemplo:


```scala 
evaluate_expression("A - B", Map("A" -> Set("1", "2", "3"), "B" -> ("3", "4")))
```

Para executar usando o SBT use:


```bash
sbt run
```

Ou,

```bash
sbt test
```
