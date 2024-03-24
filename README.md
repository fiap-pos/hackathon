# Sistema de Controle de Pedidos para Lanchonete

Este projeto é um sistema de gerenciamento de controle de pontos. Ele possui as seguintes funcionalidades:

- Registro de ponto
- Criação de relatório dos registro no mês
- Busca os registros de ponto do dia
- Busca os registros de ponto do dia a partir de uma matrícula

## Rotas disponíveis na API
Todas as rotas estão listadas através do Swagger no endereço `http://localhost:8080/swagger-ui/index.html`

Obs.: Sugerimos o Insomnia e já disponibilizamos uma collection em `collections/insomnia` 

## Pré-requisitos

Antes de executar o projeto, verifique se você possui os seguintes requisitos:

- Docker e docker-compose instalados
- Java 17 (caso queira buildar o projeto fora do container)

## Imagem Docker do projeto

Podemos encontrar a imagem do projeto no seguinte repositório do Docker hub [vwnunes/hackathon-61-ponto](https://hub.docker.com/repository/docker/vwnunes/hackathon-61-ponto/general)

## Executando o Projeto

Siga as instruções abaixo para executar o projeto via docker/docker-compose:

1. Faça o clone deste repositório: `https://github.com/fiap-pos/hackathon.git`
2. Acesse o diretório do projeto: `cd hackathon`
3. Execute o comando para iniciar o ambiente Docker: `docker-compose up -d`
4. Aguarde até que os containers estejam prontos e em execução.
5. Acesse a API pelo seu client de escolha pelo seguinte endereço base: `http://localhost:8080`

### Caso queira buildar o projeto fora do container, siga os passos abaixo:

1. Certifique-se de ter o Java 17 instalado em sua máquina.
2. Acesse o diretório do projeto: 
    ```bash 
        cd hackathon
    ```
3. Execute o comando para buildar o projeto: 
    ```bash
        ./mvnw clean package
    ```
4. Execute o comando para iniciar o ambiente Docker: 
    ```bash
        docker-compose -f docker-compose-local.yml up -d
    ```
5. Execute o comando para executar a aplicação: 
    ```bash
        ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
    ```
6. Acesse a API pelo seu client de escolha pelo seguinte endereço base: `http://localhost:8080`

### Caso queira rodar o projeto dentro de um cluster kubernetes local

1. Certifique-se de ter o kubectl instalado e devidamente configurado para cluster kubernetes
2. Execute o comando: 
    ```bash
        kubectl apply -f deployment
    ```
3. Aguarde até que os pods estejam com status de RUNNING. 
4. Acessando o serviço: 
    - Acesse a API pelo seu client de escolha pelo seguinte endereço base: `http://{IP_DO_SEU_CLUSTER}:30000`
    - Se você estiver utilizando o minikube obetenha a url da aplição através do comando:
    ```bash 
        minikube service nlb-hackathon-ponto-service --url
    ```

## Contribuidores
- [engmarcosalves](https://github.com/engmarcosalves) - Marcos de Oliveira Alves - eng.marcos.alves@gmail.com - RM 349707
- [Vandrs](https://github.com/Vandrs) - Vanderson Wilson da Rosa Nunes - vann.nunes@gmail.com - RM 349849
- [coelhos-gabi](https://github.com/coelhos-gabi) - Gabriela Siqueira do Vale Coelho - coelhos.gabi@gmail.com - RM 349957
