# Lista 1 -- Teoria da Computação / Linguagens Formais e Autômatos -- 2024/2

**Introdução à Teoria da Computação & Fundamentos Matemáticos**

**Autor:** Henrique de Souza Lima

## Problema 1

Este problema pede a construção de um programa que converte um número inteiro de 
uma base numérica para outra. O programa deve receber como entrada o número a ser 
convertido, a base original e a base para a qual ele deve ser convertido. As 
bases suportadas devem ser 2 (binária), 8 (octal), 10 (decimal), 12 (duodecimal),
16 (hexadecimal), e 20 (vigesimal), usando as letras maiúsculas no alfabeto latino
como os dígitos adicionais. Por exemplo, os dígitos da da base 16 
serão {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F}.

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
