# Lista 1 -- Teoria da Computação / Linguagens Formais e Autômatos -- 2024/2

**Introdução à Teoria da Computação & Fundamentos Matemáticos**

**Autor:** Henrique de Souza Lima

## Problema 3

Este problema pede a construção de uma função que verifica se um número é perfeito. 
Um número perfeito é aquele que é igual à soma de seus divisores próprios, i.e., os
divisores excluindo o próprio número. O programa receberá como entrada um número 
inteiro positivo, e como saída uma mensagem que indica se o número é perfeito ou não.

**Solução**

A solução adotada foi implementar uma recursão. O caso base é quando o divisor atual, 
inicialmente igual a 1, é igual ou maior que o número passado inicialmente para a função. 
Caso seja, verifica se a soma dos divisores, positivos, do número desejado, exceto ele mesmo, é
igual ao próprio número, e indica, em uma mensagem, se é ou não perfeito em conformidade
com esta condição. O conjunto dos divisores, implementado como set, inicia-se 
vazio. Nos demais casos, verifica-se se o resto da razão inteira entre do número e o
divisor atual é igual ou não a zero, e ascrescentará este divisor ao set em caso afirmativo;
em seguida, soma o valor do divisor atual por 1. O resultado é um set contendo todos
os divisores desse número.

**Execução**

Para executar a função no prompt do Scala execute, por exemplo:

```scala 
is_perf_number(28)
```

Para executar usando o SBT use:

```bash
sbt run
```

Ou, para testes automatizados:

```bash
sbt test
```
