Este é um projeto de livraria baseado nas aulas da Alura sobre JSF com primefaces.

Cada diretório tem uma versão desse projeto implementado com diferentes tecnologias. O módulo com WildFly na versão 29.0.0 com jakarta foi minha adaptação do módulo com o WildFly 10. Os módulos com tomcat também foram adaptados, um deles para funcionar com tomcat 8.5.x e tomcat 10.1.x.

Para maiores detalhes, basta acessar o README de cada módulo.

Possibilidades de melhorias: 

- WildFly em um container docker e automatizar toda a execução com scripts.

- usar o .gitlab-ci/.github/workflows/*.yml para automatizar o build/deploy na nuvem.

- testes unitários e de integração;

- Flyway ou liquibase para migração de banco;

- usar JPA em vez de Hibernate diretamente;
