<h1 align="center">Açaí Management System</h1>

<p align="center">Açaí Management System is a comprehensive business management application developed during the Object-Oriented Programming course at <a href="https://ufersa.edu.br/">UFERSA</a>. It provides a complete digital solution for managing açaí store operations.
</p>

![image](https://github.com/user-attachments/assets/73c4a0b8-1e38-4f8a-b137-27bef46bda59)

<br/>
  
Contents
=================
<!--ts-->
  * [🛠️ Technologies](#technologies)
  * [🚀 Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation & Setup](#setup)
  * [⚙️ Features](#features)
  * [📷 Application Screenshots](#screenshots)
    * [Authentication](#authentication)
    * [Dashboard](#dashboard)
      * [Sales Overview](#sales-overview)
      * [Order Management](#order-management) 
    * [Point of Sale](#point-of-sale)
    * [Product Management](#products)
      * [Product Catalog](#products-catalog)
      * [Add Product](#products-creation)
      * [Edit Product](#products-update)
    * [Customer Management](#customers)
      * [Customer Directory](#customers-directory)
      * [Add Customer](#customers-creation)
      * [Edit Customer](#customers-update)
      * [Order History](#customers-history)
    * [Employee Management](#employees)
      * [Employee Directory](#employees-directory)
      * [Add Employee](#employees-creation)
      * [Edit Employee](#employees-update)
    * [Reporting](#report)
      * [Report Builder](#report-builder)
      * [PDF Reports](#report-generated) 
  * [👨‍💻 Authors](#authors)
  * [🌐 Socials](#socials)
<!--te-->
<br/>

<h2 id="technologies">🛠️ Technologies</h2>
Built with:

* [Java JDK 21](https://www.java.com/)
  * [JavaFX SDK (17.0.0.1)](https://openjfx.io/)
  * [PostgreSQL JDBC (42.2.23)](https://jdbc.postgresql.org/)
  * [Itextpdf (5.4.0)](https://github.com/itext/itext-java)
* [Postgresql](https://www.postgresql.org/) <br/><br/>

<h2 id="getting-started">🚀 Getting Started</h2>

<h3 id="prerequisites">Prerequisites</h3>

Before running the application, ensure the following tools are installed on your system:
* [Git](https://git-scm.com)
* [Java JDK 21](https://www.java.com/)
* [PostgreSQL](https://www.postgresql.org/)

**Note**: Project dependencies (JavaFX, PostgreSQL driver, and iText PDF) are already included in the project.

<h3 id="setup">Installation & Setup</h3>

```bash
# Clone the repository
$ git clone https://github.com/RiadOliveira/Acai-Manager.git

# Navigate to project directory
$ cd Acai-Manager

# Compile the application and copy FXML views to the bin directory
javac --module-path lib/javafx-sdk-* --add-modules javafx.controls,javafx.fxml -cp "lib/*:lib/javafx-sdk-*/*" -d bin $(find src -name "*.java") && cp -r src/view/screens src/view/modals bin/view/

# Run the application
java --module-path lib/javafx-sdk-* --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*:lib/javafx-sdk-*/*" view.ScreenLoader
```

<br/>

<h2 id="features">⚙️ Features</h2>

- **Complete CRUD Operations** - Full management capabilities for customers, orders, products, and employees with comprehensive data tracking.
- **Search System** - Multi-criteria search functionality for customers by name and orders by date, product, or customer.
- **Invoice Generation** - Automated invoice creation for individual orders.
- **Comprehensive Reporting** - Detailed reports for orders within flexible time periods (daily, weekly, monthly).
- **Role-Based Access Control** - Administrative privileges required for product and employee management operations. <br/><br/>

<h2 id="screenshots">📷 Application Screenshots</h2>

<h3 id="authentication">Authentication</h3>

![image](https://user-images.githubusercontent.com/69125013/148275658-b0836118-597a-4601-a804-ef1b11f9e28a.png)

<h3 id="dashboard">Dashboard</h3>

- <h4 id="sales-overview">Sales Overview</h4>

![image](https://user-images.githubusercontent.com/69125013/148275766-cbf88ccd-62ef-47ec-87cc-5f162f79af02.png)

- <h4 id="order-management">Order Management</h4>

![image](https://user-images.githubusercontent.com/69125013/148276063-9d85a0d3-658b-4a7b-be79-7eb7643a10e8.png)

<h3 id="point-of-sale">Point of Sale</h3>

![image](https://user-images.githubusercontent.com/69125013/148275884-b4736854-bf38-4b0d-a1be-c3047704e1bb.png)

<h3 id="products">Product Management</h3>

- <h4 id="products-catalog">Product Catalog</h4>

![image](https://user-images.githubusercontent.com/69125013/148276242-126d2433-6f80-46db-9b68-35d7a0323122.png)

- <h4 id="products-creation">Add Product</h4>

![image](https://user-images.githubusercontent.com/69125013/148276287-0597e854-0ed9-404c-a3a5-3be7967bc9e6.png)

- <h4 id="products-update">Edit Product</h4>

![image](https://user-images.githubusercontent.com/69125013/148276334-68375612-8a69-403d-8e38-cc386e98189e.png)

<h3 id="customers">Customer Management</h3>

- <h4 id="customers-directory">Customer Directory</h4>
 
![image](https://user-images.githubusercontent.com/69125013/148276436-451b3e0b-ab31-4a48-ab1c-a89793ee8b48.png)

- <h4 id="customers-creation">Add Customer</h4>

![image](https://user-images.githubusercontent.com/69125013/148276538-2ea433bc-cc47-48fb-bf71-6ff50f77a254.png)

- <h4 id="customers-update">Edit Customer</h4>

![image](https://user-images.githubusercontent.com/69125013/148276584-2c604856-f730-43ec-9891-ce0e4939fe43.png)

- <h4 id="customers-history">Order History</h4>

![image](https://user-images.githubusercontent.com/69125013/148276798-fd78c828-7562-4ade-a9b2-14ab5d363c84.png)

<h3 id="employees">Employee Management</h3>

- <h4 id="employees-directory">Employee Directory</h4>

![image](https://user-images.githubusercontent.com/69125013/148276692-f8ec6ef6-e4d0-4ee3-95e1-3a5c88198fa5.png)

- <h4 id="employees-creation">Add Employee</h4>

![image](https://user-images.githubusercontent.com/69125013/148276720-2e70c6ea-a6a8-4d68-b842-791196d64cfa.png)

- <h4 id="employees-update">Edit Employee</h4>

![image](https://user-images.githubusercontent.com/69125013/148276758-5331de2b-c127-4d43-b893-3c0ad48f01dd.png)

<h3 id="report">Reporting</h3>

- <h4 id="report-builder">Report Builder</h4>

![image](https://user-images.githubusercontent.com/69125013/148276859-d01485ed-8c6a-4437-b2c6-68d1f188042b.png)
![image](https://user-images.githubusercontent.com/69125013/148276963-dc74432d-ec9e-4559-a804-55d0288812c1.png)

- <h4 id="report-generated">PDF Reports</h4>

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
