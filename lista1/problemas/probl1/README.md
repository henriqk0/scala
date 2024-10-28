# Lista 1 -- Teoria da Computação / Linguagens Formais e Autômatos -- 2024/2

**Introdução à Teoria da Computação & Fundamentos Matemáticos**

**Autor:** Henrique de Souza Lima

## Problema 1

Este problema pede a construção de um programa que converte um número inteiro de 
uma base numérica para outra. O programa deve receber como entrada o número a ser 
convertido, a base original e a base para a qual ele deve ser convertido. As 
bases suportadas devem ser 2 (binária), 8 (octal), 10 (decimal), 12 (duodecimal),
16 (hexadecimal), e 20 (vigesimal), usando as letras maiúsculas no alfabeto latino
como os dígitos adicionais. Por exemplo, os dígitos da base 16 serão 
{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F}.

**Solução**

A solução adotada foi implementar 2 funções, sendo uma auxiliar com uso de recursão. 
Primeiramente, converte-se a string recebida por um número inteiro, multiplicando
cada dígito pelo seu valor correspondente, associado através de um Map(Char, Int),
e da posição que ocupa, invertendo a string recebida para que os indices estejam corretamente
relacionados com o expoente daquela base; por fim, soma-se todos os valores obtidos. Com o
resultado, chama-se a função recursiva de cauda com o numero e sua base final, cujo caso base é
quando o número em seu parametro é igual a 0. Nos demais casos, o número é dividido 
sucessivamente pela base desejada e possui o caracter associado ao o resto desta divisão acrescentado 
ao início de uma string, iniciada vazia. Ao fim, obtém-se a convertido para a base desejada.  


**Execução**

Para executar a função no prompt do Scala execute, por exemplo:


```scala 
convert_to_another_base("1111", 2, 16)
```

Para executar usando o SBT use:


```bash
sbt run
```

Ou,

```bash
sbt test
```
