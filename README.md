# parkassist

ParkAssist é um sistema de parquímetro digital para gerenciamento e cobrança de vagas de estacionamento rotativo. Tem por princípios fundamentais a utilização de tecnologias modernas e escaláveis para prover uma solução otimizada e eficiente, capaz de responder à variações de demanda diárias ou sazonais.

Em desenvolvimento como trabalho de curso da pós-graduação em Arquitetura e Desenvolvimento Java na FIAP.

Este projeto utiliza o *framework* Quarkus, para mais informações consulte: <https://quarkus.io/> .

## Executado a aplicação em modo de desenvolvimento

Para rodar a aplicação em modo de desenvlvimento (`dev`) com "live coding":

```shell script
./mvnw compile quarkus:dev
```

> **_NOTA:_**  Quarkus tem uma interfac, disponível apenas mo modo dev em <http://localhost:8080/q/dev/>.

## Empacotando em JAR e executando a aplicação

O empacotamento da aplicação é feito executando o seguinte comando:

```shell script
./mvnw package
```

O arquivo `quarkus-run.jar` é produzido na pasta `target/quarkus-app/` . As dependências são copiadas para a pasta `target/quarkus-app/lib/`.


Para rodar a aplicação pode se usar o seguinte comando:

```shell script
java -jar target/quarkus-app/quarkus-run.jar
```

Caso queira que seja gerado um _über-jar_, deve-se utilizar o seguinte comando:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

A aplicação empacotada como um _über-jar_ pode ser executada por: `java -jar target/*-runner.jar`.

## Criando executáveis nativos e Contêineres

O Quarkus permite a criação de executáveis nativos que não necessitam da JVM para rodar. Sendo assim, definimos um fluxo em `Dockerfile` para compilar e criar um container com a aplicação. Para isto basta executar o seguinte comando:

```shell script
docker build ./
```

> **_NOTA:_**  Necessário ter o Docker e o Docker Composer instalados.

## Deploy

Para prover escalabilidade horizontal, um container contendo a aplicação pode ser orquestrado com o Kubernetes. Para isto, definimos um fluxo de CI/CD com o Github Actions capaz de realizar o deploy da aplicação na plataforma GCP.

## Guias Relacionados do Quarkus

- MongoDB with Panache ([guide](https://quarkus.io/guides/mongodb-panache)): Simplify your persistence code for MongoDB via the active record or the repository pattern

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

- [Easily start your Reactive RESTful Web Services](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
