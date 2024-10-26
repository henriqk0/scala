# Lista 1 -- Teoria da Computação / Linguagens Formais e Autômatos -- 2024/2

**Introdução à Teoria da Computação & Fundamentos Matemáticos**

**Autor:** Henrique de Souza Lima

## Problema 3

Este problema pede a construção de uma função que receba como entrada um número
$n$ representando o tamanho de uma cadeia de bits e retorne uma sequência com
todas as cadeias em binário com o tanho indicado.

**Solução**

A solução adotada foi implementar uma recursão. O caso base é quando o
comprimento é zero. A única cadeia de tamanho zero é a cadeia vazia. As cadeias
estão sendo representada como listas em Scala. Nos demais casos, gera-se todas
as cadeias de tamanho $n-1$ e em seguida gera-se duas semi-duplicadas, uma com 0
acrescentando à frente de cada cadeia, e outra com 1 acrescentado à frente de
cada cadeia. O resultado é uma lista contendo as duas listas com as
semi-duplicatas.

**Execução**

Para executar a função no prompt do Scala execute, por exemplo:


```scala
allBinStrings(5)
```

Para executar usando o SBT use:


```bash
sbt run
```
