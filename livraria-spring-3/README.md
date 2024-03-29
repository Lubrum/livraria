Pré-requisitos:

- Java 17;
- Docker e docker compose;
- Tomcat 10.1.x instalado e configurado;
- plugin SmartTomcat instalado no IntelliJ para execução do Tomcat na IDE;

Como executar o projeto:

No IntelliJ:

1- executar via CLI a partir do diretório raíz (livraria-in-tomcat) o comando para subir o banco de dados mariadb via
docker compose:

```sh
docker compose up -d
```

2- ajuste os campos Tomcat Server e Deployment Directory da configuração de execução 'run-tomcat-spring-3' com o
diretório do seu Tomcat e da sua pasta src/main/webapps, respectivamente;

3- ainda no 'run-tomcat-spring-3', em VM Options, adicione as configurações de conexão do seu banco de dados MySQL:

```sh
-Durl=jdbc:mysql://localhost:3309/livrariadb -Duser=root -Dpassword=123
```

4- execute o 'run-tomcat-spring-3';

> Abrir no navegador a URL da página de login: http://localhost:8080/login.xhtml

*Observação: ao executar o projeto, o banco será criado vazio.*

*Adicione manualmente um usuário no banco de dados para testar o login e demais funcionalidades deste projeto de
exemplo.*

*Ou, se preferir, execute no IntelliJ a configuração 'init-database', que inicializa o banco de dados com algumas
informações e com usuário e senha 123/123. Verifique se a configuração está com o url do banco de dados que será
utilizado, conforme descrito no docker-compose.yml.*
