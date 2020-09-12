# Entrega - Desafio Crawlers

<p align="center">
  <a href="#entendimento">Entendimento</a> •
  <a href="#fluxo">Fluxo</a> •
  <a href="#como-executar">Como Executar</a> •
  <a href="#contato">Contato</a>
</p>

---

## Entendimento

1 - Reddit Crawler

A princípio criei uma aplicação Spring Boot na versão 2.3.3, utilizando Java 11.

A parte da extração de conteúdo do site do [Reddit](https://old.reddit.com) foi feito com a utilização da lib [JSOUP](https://jsoup.org/)

Foi criado pequenos métodos que realizam a busca e extraem as informações baseado nos subrredits.

2 - Integração com o Bot

Utilizei um [starter](https://github.com/xabgesagtx/telegram-spring-boot-starter) para facilitar a integração com a API do Telegram.

Realizei os respectivos tratamentos de mensagens vindas do usuário e direcionamentos para os fluxos baseado no comando da mensagem.

Tipos de comandos e retornos:
1. /Start -> "Olá, sou o MrLuke, o melhor BOT para te ajudar a ficar por dentro das threads que estão bombando!, digite /NadaPraFazer [+ Lista de subrredits], que eu te mostro!"

2. /Help -> "Vi que você precisa de ajuda, para ficar por dentro das threads que estão bombando, basta digitar /NadaPraFazer [+ Lista de subrredits]"

3. /NadaPraFazer [subreddits] -> "Se liga! Nessa Thread que ta bombando no Reddit! {}"

---

## Fluxo

![Crawlers](https://i.imgur.com/v42tQb1.png)

---

## Como Executar

- API REST
Basta realizar um `GET` no endpoint `/thread/hot` , passando as subreddits separados por ponto-e-vírgula (`;`) no parâmetro da requisição.
Exemplo de como deve ficar: http://localhost:8080/thread/hot?subreddits=worldnews;cats

- **Você envia:**  Os Subrredits que você quer realizar a busca.
- **Você recebe:** Uma lista de threads que estão em alta, baseado nos subreddits.


**Request:**
```json
GET /thread/hot HTTP/1.1
Query-Parameters: Lista dos subreddits separada por ponto-e-vírgula
```

**Sucesso na requisição:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  {
    "title": "Tesla Model Y Owners Find Cooling System Cobbled Together With Home Depot-Grade Fake Wood",
    "link": "https://old.reddit.com/r/cars/comments/ipi89a/tesla_model_y_owners_find_cooling_system_cobbled/",
    "commentLink": "https://old.reddit.com/r/cars/comments/ipi89a/tesla_model_y_owners_find_cooling_system_cobbled/",
    "subreddit": "cars",
    "upVotes": 7030
  }
]
```
**Tratamento ao enviar não enviar subreddits:**
```json
HTTP/1.1 400 Bad Request
Content-Type: application/json

{
  "message": "Você deve informar ao menos um subreddit.",
  "status": 400,
  "error": "Bad Request",
  "path": "uri=/thread/hot",
  "timestamp": "2020-09-11T16:16:45.967+00:00"
}
``` 
- Rodando o Container Docker
1. Efetue o git clone do projeto
2. Abra a pasta princípal do projeto do Crawler
3. Execute o comando: ```docker build -t crawler . ``` para que seja criado a imagem do docker
4. Execute o comando: ```docker run -d --name crawler crawler ```
5. E por fim chame o nosso amigo bot procurando no Telegram por: MrLukeRedditBot

---

## Contato
- Email: lucasrti@hotmail.com
- Website: [luccasdev.github.io](https://luccasdev.github.io/)

---

# Desafio 2: Crawlers

Parte do trabalho na IDwall inclui desenvolver *crawlers/scrapers* para coletar dados de websites.
Como nós nos divertimos trabalhando, às vezes trabalhamos para nos divertir!

O Reddit é quase como um fórum com milhares de categorias diferentes. Com a sua conta, você pode navegar por assuntos técnicos, ver fotos de gatinhos, discutir questões de filosofia, aprender alguns life hacks e ficar por dentro das notícias do mundo todo!

Subreddits são como fóruns dentro do Reddit e as postagens são chamadas *threads*.

Para quem gosta de gatos, há o subreddit ["/r/cats"](https://www.reddit.com/r/cats) com threads contendo fotos de gatos fofinhos.
Para *threads* sobre o Brasil, vale a pena visitar ["/r/brazil"](https://www.reddit.com/r/brazil) ou ainda ["/r/worldnews"](https://www.reddit.com/r/worldnews/).
Um dos maiores subreddits é o "/r/AskReddit".

Cada *thread* possui uma pontuação que, simplificando, aumenta com "up votes" (tipo um like) e é reduzida com "down votes".

Sua missão é encontrar e listar as *threads* que estão bombando no Reddit naquele momento!
Consideramos como bombando *threads* com 5000 pontos ou mais.

## Entrada
- Lista com nomes de subreddits separados por ponto-e-vírgula (`;`). Ex: "askreddit;worldnews;cats"

### Parte 1
Gerar e imprimir uma lista contendo a pontuação, subreddit, título da thread, link para os comentários da thread e link da thread.
Essa parte pode ser um CLI simples, desde que a formatação da impressão fique legível.

### Parte 2
Construir um robô que nos envie essa lista via Telegram sempre que receber o comando `/NadaPraFazer [+ Lista de subrredits]` (ex.: `/NadaPraFazer programming;dogs;brazil`)

### Dicas
 - Use https://old.reddit.com/
 - Qualquer método para coletar os dados é válido. Caso não saiba por onde começar, procure por JSoup (Java), SeleniumHQ (Java), PhantomJS (Javascript) e Beautiful Soup (Python).
