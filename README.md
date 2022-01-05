<h1 align="center">Sistema de gestão de Açaí</h1>

<p align="center">Aplicativo para gestão de uma loja fictícia de açaí. Projeto desenvolvido durante a disciplina de orientação a objeto na 
  <a href="https://ufersa.edu.br/">UFERSA</a>.</p>
  
</br>
  
Tópicos
=================
<!--ts-->
   * [🛠 Tecnologias](#technologies)
   * [:computer: Instalação e Execução](#install&run)
      * [Pré-requisitos](#prerequisites)
      * [Executando o App](#running)
   * [:gear: Exigências/Features](#requirements)
   * [:camera: Screen Shots](#screenshots)
      * [Login](#login)
      * [Início](#landing)
      * [Venda (Efetuar venda)](#sale)
      * [Produtos](#products)
      * [Clientes](#customers)
      * [Funcionários](#employees)
      * [Relatório](#report) 
<!--te-->

</br>

<h2 id="technologies">🛠 Tecnologias</h2>

- [Java](https://www.java.com/pt-BR/)
  - Javafx-sdk-17.0.0.1
  - Postgresql-42.2.23
  - Itextpdf-5.4.0  
- [Postgresql](https://www.postgresql.org/)

</br>

<h2 id="install&run">:computer: Instalação e Execução</h2>

  <ul>
    <li id="prerequisites"><h3>Pré-requisitos</h3></li>
      Antes de tudo, para a execução, será necessário que você possua o <a href="https://git-scm.com/">Git</a> instalado em sua máquina,
      para poder clonar esse repositório. Além disso, será necessário a instalação das tecnologias acima, exceto as libs (Javafx, postgres e itext),
      pois essas já estão inclusas no projeto.
    <li id="running"><h3>Executando o App</h3></li>
  
    # Clone esse repositório
    $ git clone https://github.com/RiadOliveira/POO-Acai.git

    # Acesse a pasta do projeto
    $ cd POO-Acai
  
    # Executar o projeto
      # Execute o arquivo principal do projeto (ScreenLoader.java), o qual está dentro da pasta src/view.
  </ul>

</br>

<h2 id="requirements">Exigências/Features</h2>

- a) Cadastrar, editar, atualizar, buscar, deletar e visualizar: Cliente (nome, endereço,
telefone, data do pedido), Pedido (data do pedido, cliente, quantidade, nome do
produto, forma de pagamento e status), Produto (nome do produto, preço do produto,
categoria) e Funcionário (nome e telefone);
- b) Buscar: Cliente (por nome) e pedido (por data, por produto ou por cliente);
- c) Gerar a nota de cada pedido;
- d) Gerar relatório com todos os pedidos feitos em um período (dia, semana ou mês);
- e) Apenas o administrador pode cadastrar novos produtos e funcionários;

</br>

<h2 id="screenshots">Screen Shots</h2>

- <h3 id="login">Login</h3>

![image](https://user-images.githubusercontent.com/69125013/148275658-b0836118-597a-4601-a804-ef1b11f9e28a.png)

- <h3 id="landing">Início</h3>

  - #### Status de vendas
  ![image](https://user-images.githubusercontent.com/69125013/148275766-cbf88ccd-62ef-47ec-87cc-5f162f79af02.png)
  
  - #### Detalhes/Seleção do Pedido
  ![image](https://user-images.githubusercontent.com/69125013/148276063-9d85a0d3-658b-4a7b-be79-7eb7643a10e8.png)

- <h3 id="sale">Venda (Efetuar venda)</h3>

![image](https://user-images.githubusercontent.com/69125013/148275884-b4736854-bf38-4b0d-a1be-c3047704e1bb.png)

- <h3 id="products">Produtos</h3>

  - #### Listagem
  ![image](https://user-images.githubusercontent.com/69125013/148276242-126d2433-6f80-46db-9b68-35d7a0323122.png)

  - #### Criação
  ![image](https://user-images.githubusercontent.com/69125013/148276287-0597e854-0ed9-404c-a3a5-3be7967bc9e6.png)

  - #### Atualização
  ![image](https://user-images.githubusercontent.com/69125013/148276334-68375612-8a69-403d-8e38-cc386e98189e.png)

- <h3 id="customers">Clientes</h3>

  - #### Listagem
  ![image](https://user-images.githubusercontent.com/69125013/148276436-451b3e0b-ab31-4a48-ab1c-a89793ee8b48.png)

  - #### Criação
  ![image](https://user-images.githubusercontent.com/69125013/148276538-2ea433bc-cc47-48fb-bf71-6ff50f77a254.png)

  - #### Atualização
  ![image](https://user-images.githubusercontent.com/69125013/148276584-2c604856-f730-43ec-9891-ce0e4939fe43.png)
  
  - #### Histórico
  ![image](https://user-images.githubusercontent.com/69125013/148276798-fd78c828-7562-4ade-a9b2-14ab5d363c84.png)

- <h3 id="employees">Funcionários</h3>

  - #### Listagem
  ![image](https://user-images.githubusercontent.com/69125013/148276692-f8ec6ef6-e4d0-4ee3-95e1-3a5c88198fa5.png)

  - #### Criação
  ![image](https://user-images.githubusercontent.com/69125013/148276720-2e70c6ea-a6a8-4d68-b842-791196d64cfa.png)
  
  - #### Atualização
  ![image](https://user-images.githubusercontent.com/69125013/148276758-5331de2b-c127-4d43-b893-3c0ad48f01dd.png)

- <h3 id="report">Relatório</h3>

  - #### Geração
  ![image](https://user-images.githubusercontent.com/69125013/148276859-d01485ed-8c6a-4437-b2c6-68d1f188042b.png)
  ![image](https://user-images.githubusercontent.com/69125013/148276963-dc74432d-ec9e-4559-a804-55d0288812c1.png)

  - #### Relatório Gerado (PDF)
  <img src="https://user-images.githubusercontent.com/69125013/148277015-f91190ab-e851-47d6-ab20-653395838954.png" width="500"/>
