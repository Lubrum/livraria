Pré-requisitos:

- Java 8;
- MySQL 5.x instalado (com ou sem Docker);
- Tomcat 8.5.x instalado e configurado;
- plugin SmartTomcat instalado no IntelliJ para execução do Tomcat na IDE;

Como executar o projeto:

No IntelliJ:

- ajuste os campos Tomcat Server e Deployment Directory da configuração de execução 'run-tomcat-spring' com o diretório do seu Tomcat e da sua pasta src/main/webapps, respectivamente;

- ainda no 'run-tomcat-spring', em VM Options, adicione as configurações de conexão do seu banco de dados MySQL: 

```sh
-Durl=jdbc:mysql://localhost:3306/livrariadb -Duser=MEU_USUARIO -Dpassword=MINHA_SENHA
```

- execute o 'run-tomcat-spring';

*Observação: ao executar o projeto, o banco será criado vazio, adicione manualmente um usuário no banco de dados para testar o login e demais funcionalidades deste projeto de exemplo.*