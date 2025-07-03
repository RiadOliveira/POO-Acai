<h1 align="center">Açaí Management System</h1>

<p align="center">Application for managing a fictional açaí store. Project developed during the OOP course at 
  <a href="https://ufersa.edu.br/">UFERSA</a>.
</p>

![image](https://github.com/user-attachments/assets/00fb89e5-84cd-400c-a759-9842c838669b)

<br/>
  
Contents
=================
<!--ts-->
   * [🛠️ Technologies](#technologies)
   * [💻 Install & Run](#install&run)
      * [Prerequisites](#prerequisites)
      * [Running the app](#running)
   * [⚙️ Features](#features)
   * [📷 Screenshots](#screenshots)
      * [Login](#login)
      * [Home](#landing)
        * [Sales Status](#sales-status)
        * [Order Details](#order-details) 
      * [Sales (Make a Sale)](#sale)
      * [Products](#products)
        * [Listing](#products-listing)
        * [Creation](#products-creation)
        * [Update](#products-update)
      * [Customers](#customers)
        * [Listing](#customers-listing)
        * [Creation](#customers-creation)
        * [Update](#customers-update)
        * [History](#customers-history)
      * [Employees](#employees)
        * [Listing](#employees-listing)
        * [Creation](#employees-creation)
        * [Update](#employees-update)
      * [Report](#report)
        * [Generation](#report-generation)
        * [Generated Report (PDF)](#report-generated) 
   * [👨‍💻 Authors](#authors)
   * [🌐 Socials](#socials)
<!--te-->
<br/>

<h2 id="technologies">🛠️ Technologies</h2>
Tools used on this project:

- [Java](https://www.java.com/pt-BR/)
  - Javafx-sdk-17.0.0.1
  - Postgresql-42.2.23
  - Itextpdf-5.4.0  
- [Postgresql](https://www.postgresql.org/) <br/><br/>

<h2 id="install&run">💻 Install & Run</h2>

<h3 id="prerequisites">Prerequisites</h3>

Before proceeding, you will need to have [Git](https://git-scm.com) installed on your machine to clone this repository. Additionally, you will need to install the technologies listed above, except for the libraries (Javafx, Postgres, and Itext), as these are already included in the project.

<h3 id="running">Running the app</h3>

  ```bash
    # Clone this repository
    $ git clone https://github.com/RiadOliveira/POO-Acai.git

    # Navigate to the project folder
    $ cd POO-Acai
  
    # Run the project
      # Execute the main project file (ScreenLoader.java), located in the src/view folder.
  ```

<br/>

<h2 id="features">⚙️ Features</h2>

- a) Register, edit, update, search, delete, and view: Customer (name, address, phone, order date), Order (order date, customer, quantity, product name, payment method, and status), Product (product name, product price, category), and Employee (name and phone);
- b) Search: Customer (by name) and order (by date, product, or customer);
- c) Generate an invoice for each order;
- d) Generate a report with all orders placed during a period (day, week, or month);
- e) Only the administrator can register new products and employees; <br/><br/>

<h2 id="screenshots">📷 Screenshots</h2>

<h3 id="login">Login</h3>

![image](https://user-images.githubusercontent.com/69125013/148275658-b0836118-597a-4601-a804-ef1b11f9e28a.png)

<h3 id="home">Home</h3>

- <h4 id="sales-status">Sales Status</h4>

![image](https://user-images.githubusercontent.com/69125013/148275766-cbf88ccd-62ef-47ec-87cc-5f162f79af02.png)

- <h4 id="order-details">Order Details</h4>

![image](https://user-images.githubusercontent.com/69125013/148276063-9d85a0d3-658b-4a7b-be79-7eb7643a10e8.png)

<h3 id="sale">Sales (Make a Sale)</h3>

![image](https://user-images.githubusercontent.com/69125013/148275884-b4736854-bf38-4b0d-a1be-c3047704e1bb.png)

<h3 id="products">Products</h3>

- <h4 id="products-listing">Listing</h4>

![image](https://user-images.githubusercontent.com/69125013/148276242-126d2433-6f80-46db-9b68-35d7a0323122.png)

- <h4 id="products-creation">Creation</h4>

![image](https://user-images.githubusercontent.com/69125013/148276287-0597e854-0ed9-404c-a3a5-3be7967bc9e6.png)

- <h4 id="products-update">Update</h4>

![image](https://user-images.githubusercontent.com/69125013/148276334-68375612-8a69-403d-8e38-cc386e98189e.png)

<h3 id="customers">Customers</h3>

- <h4 id="customers-listing">Listing</h4>
 
![image](https://user-images.githubusercontent.com/69125013/148276436-451b3e0b-ab31-4a48-ab1c-a89793ee8b48.png)

- <h4 id="customers-creation">Creation</h4>

![image](https://user-images.githubusercontent.com/69125013/148276538-2ea433bc-cc47-48fb-bf71-6ff50f77a254.png)

- <h4 id="customers-update">Update</h4>

![image](https://user-images.githubusercontent.com/69125013/148276584-2c604856-f730-43ec-9891-ce0e4939fe43.png)

- <h4 id="customers-history">History</h4>

![image](https://user-images.githubusercontent.com/69125013/148276798-fd78c828-7562-4ade-a9b2-14ab5d363c84.png)

<h3 id="employees">Employees</h3>

- <h4 id="employees-listing">Listing</h4>

![image](https://user-images.githubusercontent.com/69125013/148276692-f8ec6ef6-e4d0-4ee3-95e1-3a5c88198fa5.png)

- <h4 id="employees-creation">Creation</h4>

![image](https://user-images.githubusercontent.com/69125013/148276720-2e70c6ea-a6a8-4d68-b842-791196d64cfa.png)

- <h4 id="employees-update">Update</h4>

![image](https://user-images.githubusercontent.com/69125013/148276758-5331de2b-c127-4d43-b893-3c0ad48f01dd.png)

<h3 id="report">Report</h3>

- <h4 id="report-generation">Generation</h4>

![image](https://user-images.githubusercontent.com/69125013/148276859-d01485ed-8c6a-4437-b2c6-68d1f188042b.png)
![image](https://user-images.githubusercontent.com/69125013/148276963-dc74432d-ec9e-4559-a804-55d0288812c1.png)

- <h4 id="report-generated">Generated Report (PDF)</h4>

<img src="https://user-images.githubusercontent.com/69125013/148277015-f91190ab-e851-47d6-ab20-653395838954.png" width="500"/>

<h2 id="authors">👨‍💻 Authors</h2>

<kbd>
  <a href="https://github.com/RiadOliveira">
    <img src="https://avatars.githubusercontent.com/u/69125013?v=4" width="100" alt="Ríad Oliveira"/>
    <br/><br/>
    <p align="center"><b>Ríad Oliveira</b></p>
  </a>
</kbd>
<kbd>
  <a href="https://github.com/ValdirAPN">
    <img src="https://avatars.githubusercontent.com/u/44126505?v=4" width="100" alt="Valdir Pinheiro"/>
    <br/><br/>
    <p align="center"><b>Valdir Pinheiro</b></p>
  </a>
</kbd>

## 🌐 Socials

<div id="socials">
  <a href = "mailto:riad.oliveira@hotmail.com"><img class="badge" src="https://img.shields.io/badge/Microsoft_Outlook-0078D4?style=for-the-badge&logo=microsoft-outlook&logoColor=white" target="_blank"/></a>
  <a href = "mailto:riad.oliveira@gmail.com"><img class="badge" src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"/></a>
  <a href="https://www.linkedin.com/in/ríad-oliveira" target="_blank"><img class="badge" src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"/></a>
</div>
