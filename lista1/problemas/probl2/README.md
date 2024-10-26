# Lista 1 -- Teoria da Computação / Linguagens Formais e Autômatos -- 2024/2

**Introdução à Teoria da Computação & Fundamentos Matemáticos**

**Autor:** Henrique de Souza Lima

## Problema 2

Este problema pede a construção de uma função que verifica se uma expressão composta apenas de parênteses (‘(’ e ‘)’) está corretamente balanceada, ou seja, se cada parêntese de abertura tem um correspondente parêntese de fechamento. O programa receberá como entrada uma string contendo apenas parênteses, e como saída uma mensagem que indica se a expressão está bem formada.

**Solução**

A solução adotada foi implementar uma recursão. O caso base é quando a string é completamente percorrida ou quando o número de parêntesis abertos torna-se negativo. Caso tenha sido completamente percorrida, verifica se o contador de parentesis abertos (rest, passado como parametro) é igual a 0, indicando que cada parentesis aberto (+1 ao contador) possui correspondente  fechado (-1 ao contador), e mostra que de fato trata-se de uma expressão bem formada. Caso contrário, envia mensagens adequadas à razão da falta de correspondência. O contador que percorre os caracteres da string inicia-se com 0 e é passado como parametro, sendo incrementado a cada iteração.
 
**Execução**

Para executar a função no prompt do Scala execute, por exemplo:


```scala
well_formed_parenthesis("((()")
```

Para executar usando o SBT use:


```bash
sbt run
```

Ou, para testes automatizados:

```bash
sbt test
```