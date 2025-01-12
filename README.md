# Munchbox
Aplicação de restaurante criada para a pós graduação de arquitetura e desenvolvimento Java.



## Docker

Para facilitar a subida do projeto no Docker foram disponibilizados os arquivos:

- Nomes:
  `docker/docker-compose.yml`
  `docker/munchbox-1.0.jar`
  `docker/init.sql`

- Como usar:
  1. Baixe os arquivos
  2. Crie a seguinte estrutura de pastas em seu WSL: `/SEUSERVIDOR/munchbox/target`
  3. Copie os arquivos `docker-compose.yml` e `init.sql` para a pasta `SEUSERVIDOR/munchbox`
  4. Copie o arquivo `munchbox-1.0.jar` para a pasta `SEUSERVIDOR/munchbox/target` renomeando o arquivo para `munchbox.jar`
  5. Execute o comando `docker-compose up`



## Postman Collection

Para facilitar os testes das APIs, uma coleção do Postman foi disponibilizada na raiz do projeto.

- Nome: `postman/munchbox-1.0-collection.json`
- Como usar: 
  1. Baixe o arquivo.
  2. Importe no Postman através de "File > Import".
  3. Configure as variáveis de ambiente conforme necessário.
