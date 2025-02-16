# Munchbox

Bem-vindo ao **Munchbox**, uma aplicação de restaurante desenvolvida como parte de um projeto de pós-graduação em Arquitetura e Desenvolvimento Java. Este projeto visa fornecer uma solução prática e eficiente para gerenciar o funcionamento de um restaurante, oferecendo funcionalidades de gestão de pedidos, cardápios, clientes e mais.

A seguir, você encontrará tudo o que precisa para rodar a aplicação localmente, testar as APIs e configurar o ambiente de desenvolvimento com Docker.

---

## 🚀 Como Rodar o Projeto

### 🔧 Pré-requisitos

Antes de começar, certifique-se de que você tem os seguintes requisitos instalados:

- [Docker](https://www.docker.com/get-started)
- [IDE Java](https://www.jetbrains.com/idea/) (como IntelliJ IDEA ou Eclipse)
- [Postman](https://www.postman.com/downloads/) para testar as APIs

---

### 🐳 Docker

Para facilitar a configuração e a execução do projeto em um ambiente isolado, incluímos arquivos de configuração do Docker. Isso permite que você suba rapidamente os containers necessários e execute a aplicação com a base de dados configurada corretamente.

#### Estrutura de Arquivos

- **docker/docker-compose.yml**: Arquivo de configuração para orquestrar os containers do Docker.
- **docker/init.sql**: Script SQL para inicializar o banco de dados com dados padrão.

#### Como Usar

1. **Baixar os arquivos**:
   - Clone o repositório ou baixe os arquivos `docker-compose.yml` e `init.sql` para sua máquina local.

2. **Gerar o arquivo JAR**:
   - Abra o projeto na sua IDE preferida (como IntelliJ IDEA ou Eclipse).
   - Compile o projeto e gere o arquivo `munchbox-1.0.jar`.

3. **Configurar a Estrutura de Pastas**:
   - Crie a seguinte estrutura de pastas no seu WSL (Windows Subsystem for Linux) ou diretório local:
     ```
     /SEUSERVIDOR/munchbox/target
     ```

4. **Organizar os Arquivos**:
   - Copie os arquivos `docker-compose.yml` e `init.sql` para a pasta `/SEUSERVIDOR/munchbox`.
   - Copie o arquivo `munchbox-1.0.jar` para a pasta `/SEUSERVIDOR/munchbox/target` e renomeie o arquivo para `munchbox.jar`.

5. **Subir os Containers com Docker Compose**:
   - Execute o seguinte comando na raiz do projeto para subir os containers:
     ```bash
     docker-compose up
     ```

6. **Acesso ao Projeto**:
   - Após a execução, a aplicação estará disponível localmente na URL configurada no `docker-compose.yml`. A base de dados também será inicializada com os dados padrão.

---

## 🧪 Testando as APIs com Postman

Para facilitar o teste das APIs e garantir que tudo esteja funcionando corretamente, disponibilizamos uma coleção do Postman, que contém as requisições pré-configuradas para você testar a aplicação.

#### Arquivo de Coleção

- **Nome do Arquivo**: `postman/munchbox-1.0-collection.json`

#### Como Usar

1. **Baixar o Arquivo**:
   - Baixe o arquivo `munchbox-1.0-collection.json` disponível na raiz do repositório.

2. **Importar no Postman**:
   - Abra o Postman e vá em "File > Import".
   - Selecione o arquivo baixado para importar as requisições.

3. **Configurar as Variáveis de Ambiente**:
   - Se necessário, configure as variáveis de ambiente no Postman para refletir a URL e credenciais da sua instância local do Munchbox.
   - Isso pode incluir o endereço de acesso à API e tokens de autenticação.

4. **Comece a Testar**:
   - Agora você pode começar a testar as diferentes APIs que compõem o projeto, validando se tudo está funcionando como esperado.

---

## 📚 Estrutura do Projeto

- **Backend**: Implementado em Java, utilizando práticas modernas de arquitetura de software.
- **Banco de Dados**: Utiliza MySQL, um banco de dados relacional configurado através do Docker.
- **API**: RESTful, com endpoints para gerenciamento de pedidos, cardápios, clientes, entre outros (Em andamento).

---

## 📝 Contribuindo para o Projeto

Se você deseja contribuir com melhorias ou correções, sinta-se à vontade para fazer um **fork** do projeto e enviar um **pull request**. Ficaremos felizes com a sua contribuição!

---

## 📄 Licença

Este projeto está sob a [Licença MIT](LICENSE), o que significa que você pode usá-lo, modificá-lo e distribuí-lo, desde que mantenha os créditos originais e siga os termos da licença.

---

**Obrigado por utilizar o Munchbox!**
