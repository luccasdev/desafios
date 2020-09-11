# Entrega - Desafio Strings

<p align="center">
  <a href="#entendimento">Entendimento</a> •
  <a href="#fluxo">Fluxo</a> •
  <a href="#como-executar">Como Executar</a> •
  <a href="#contato">Contato</a>
</p>

---

## Entendimento

1 - Formatação

A princípio utilizei lógica de quebrar o texto em parágrafos, realizando primeiro a formatação do primeiro parágrafo e depois do segundo, e ao final unindo os dois parágrafos formatados e limitados com base no parâmetro.

Para realizar a limitação precisei percorrer a quantidade de palavras já existentes em uma linha, e verificar se essa quantidade somado com o tamanho da palavra atual da repetição era maior que o limite estabelecido, o método que aplica essa validação 
é o ``` notFitInLine(String word, Integer lineLength) ``` que retorna se a palavra cabe ou não na linha.

2 - Justificar


Foi preciso entender, pesquisar e buscar informações a respeito do funcionamento e regras de um texto justificado.

Dado que separei o texto em parágrafos, criei o método de justificação por parágrafo chamado ```justifyParagraph(String paragraph)```, ele é responsável por receber o parágrafo e realizar os cálculos de espaços que faltam para dar o aspecto de texto justificado. Esse espaço faltante é baseado no limite estabelecido pelo usuário, e o espaço é distribuído entre proporcionalmente da esquerda para direita entre as palavras da linha.

---

## Fluxo

![StringFormatter](https://i.imgur.com/jIAtXZz.png)

---

## Como Executar

1. Efetue o git clone do projeto
2. Abra a pasta princípal do projeto das Strings
3. Execute o comando: ```docker build -t string-formatter . ``` para que seja criado a imagem do docker
4. Execute o comando: ```docker run -d --name string-formatter string-formatter ```

---

## Contato
- Email: lucasrti@hotmail.com
- Website: [luccasdev.github.io](https://luccasdev.github.io/)

---

# Desafio 1: Strings

Após ler o coding style do kernel Linux, você descobre a mágica que é
ter linhas de código com no máximo 80 caracteres cada uma.

Assim, você decide que de hoje em diante seus e-mails enviados também
seguirão um padrão parecido e resolve desenvolver um plugin para te ajudar
com isso. Contudo, seu plugin aceitará no máximo 40 caracteres por linha.

Implemente uma função que receba:
1. um texto qualquer
2. um limite de comprimento

e seja capaz de gerar os outputs dos desafios abaixo.

## Exemplo input

`In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.`

`And God said, "Let there be light," and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light "day," and the darkness he called "night." And there was evening, and there was morning - the first day.`

O texto deve ser parametrizável e se quiser, pode utilizar um texto de input de sua preferência.

### Parte 1 (Básico) - limite 40 caracteres
Você deve seguir o exemplo de output [deste arquivo](https://github.com/idwall/desafios/blob/master/strings/output_parte1.txt), onde basta o texto possuir, no máximo, 40 caracteres por linha. As palavras não podem ser quebradas no meio.

### Parte 2 (Intermediário) - limite 40 caracteres
O exemplo de output está [neste arquivo](https://github.com/idwall/desafios/blob/master/strings/output-parte2.txt), onde além de o arquivo possuir, no máximo, 40 caracteres por linha, o texto deve estar **justificado**.

### Dicas
- Existe um template para projetos em Java ;)

### Extras

- Parametrização da quantidade de caracteres por linha.
