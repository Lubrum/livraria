Pré-requisitos:

- Java 8;
- Docker e docker compose;
- WildFly 10.0.0 instalado e configurado;

Configurações manuais necessárias no WildFly 10 antes da execução:

- [download do wildfly 10.0.0.Final](https://www.wildfly.org/downloads/);
- extrair conteúdo do arquivo .zip no diretório desejado para execução do servidor Wildfly (geralmente no diretório /opt no Linux);
- criar o diretório /mysql/main em ../modules/system/layers/base/com;
- criar o arquivo module.xml com o conteúdo abaixo:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.38.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
```

- fazer download do jar mysql-connector-java-5.1.38.jar e adicionar no diretório do module.xml criado acima;
- adicionar o driver do MySQL e o datasource no arquivo de configurações do WildFly em ../standalone/configuration/standalone.xml, conforme o exemplo destacado abaixo:

```xml
<subsystem xmlns="urn:jboss:domain:datasources:4.0">
    <datasources>
        <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
            <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
            <driver>h2</driver>
            <security>
                <user-name>sa</user-name>
                <password>sa</password>
            </security>
        </datasource>
        
        <!-- adicionar o trecho abaixo -->
        <datasource jndi-name="java:/livraria-ds" pool-name="livrariaDS" enabled="true" use-java-context="true">
            <connection-url>jdbc:mysql://localhost:3311/livrariadb?createDatabaseIfNotExist=true</connection-url>
            <driver>com.mysql</driver>
            <pool>
                <min-pool-size>10</min-pool-size>
                <max-pool-size>100</max-pool-size>
                <prefill>true</prefill>
            </pool>
            <security>
                <user-name>root</user-name>
                <password>123</password>
            </security>
        </datasource>
        <!-- ========================= -->
        
        <drivers>
            <driver name="h2" module="com.h2database.h2">
                <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
            </driver>
            
            <!-- adicionar o trecho abaixo -->
            <driver name="com.mysql" module="com.mysql">
                <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
            </driver>
            <!-- ========================= -->
        </drivers>
    </datasources>
</subsystem>
```

Observação: utilize o seu usuário e senha de conexão com o banco em user-name e password e sua URL de conexão com o banco em connection-url.

Como executar o projeto:

*Observação: verifique no arquivo .sh se o caminho do WildFly (WILDFLY_PATH) esta correto.*

1- executar via CLI a partir do diretório raíz (livraria_in-tomcat) o comando para subir o banco de dados mariadb via docker compose:

```sh
docker compose up -d
```

No IntelliJ:

- executar 'run-with-wildfly'

Ou, via CLI no diretório raíz:

```sh
./run-wildfly.sh
```

*Observação: ao executar o projeto, o banco será criado vazio, adicione manualmente um usuário no banco de dados para testar o login e demais funcionalidades deste projeto de exemplo.*