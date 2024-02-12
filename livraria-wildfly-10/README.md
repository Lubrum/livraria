Pré-requisitos:

- Java 8;
- Docker e docker compose;
- WildFly 10.0.0 instalado e configurado;

Configurações manuais necessárias no WildFly 10 antes da execução:

- [download do wildfly 10.0.0.Final](https://www.wildfly.org/downloads/);
- extrair conteúdo do arquivo .zip no diretório desejado para execução do servidor Wildfly (geralmente no diretório /opt
  no Linux);
- criar o diretório /mariadb/main em ../modules/system/layers/base/org;
- criar o arquivo module.xml com o conteúdo abaixo:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="org.mariadb">
  <resources>
    <resource-root path="mariadb-java-client-3.1.4.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
```

- fazer download do jar mariadb-java-client-3.1.4.jar e adicionar no diretório do module.xml criado acima;
- adicionar o driver do MariaDB e o datasource no arquivo de configurações do WildFly em
  ../standalone/configuration/standalone.xml, conforme o exemplo destacado abaixo:

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
            <connection-url>jdbc:mariadb://localhost:3310/livrariadb</connection-url>
            <driver>org.mariadb</driver>
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
            <driver name="org.mariadb" module="org.mariadb">
                <xa-datasource-class>org.mariadb.jdbc.MariaDbDataSource</xa-datasource-class>
            </driver>
            <!-- ========================= -->
        </drivers>
    </datasources>
</subsystem>
```

Observação: utilize o seu usuário e senha de conexão com o banco em user-name e password e sua URL de conexão com o
banco em connection-url.

Como executar o projeto:

*Observação: verifique no arquivo .sh se o caminho do WildFly (WILDFLY_PATH) esta correto.*

1- executar via CLI a partir do diretório raíz (livraria-in-tomcat) o comando para subir o banco de dados mariadb via
docker compose:

```sh
docker compose up -d
```

No IntelliJ:

- executar 'run-wildfly-10'

Ou, via CLI no diretório raíz:

```sh
./run-wildfly.sh
```

> Abrir no navegador a URL da página de login: http://localhost:8080/login.xhtml

*Observação: ao executar o projeto, o banco será criado vazio, adicione manualmente um usuário no banco de dados para
testar o login e demais funcionalidades deste projeto de exemplo.*