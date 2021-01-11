Activity 7
------------

####Entity Relationships:
* Products(**productid**, barcode, name, expirationdate, price, tax rate, quantity, supplier, manufactured date)  
* Customers(**customerid**, name, phone, paymentinfo)  
* Purchase(**purchaseid**, *customerid*,*productid*, time, quantity, price, tax, cost)  

    [diagram](EntityRelationshipDiagram.png)

```sql
CREATE TABLE Products(
productid int not null primary key,
barcode int,
name varchar(100),
expirationdate varchar(100),
price float,
tax float,
quantity int,
supplier varchar(100),
manufacturedate varchar(100)
);

CREATE TABLE Customer(
customerid int not null primary key,
name varchar(100),
phone int,
paymentinfo varchar(100)
);

CREATE TABLE Purchase(
purchaseid int not null primary key,
customerid int,
productid int, 
time varchar(100),
quantity int,
price float,
tax float,
cost float
);


INSERT INTO Products(productid, barcode, name, expirationdate, price, tax, quantity, supplier, manufacturedate)
VALUES
(1,10101,"Brick","9/9/99",100.00,0.1,1,"brick co","8/9/93")

INSERT INTO Customer(customerid, name, phone, paymentinfo)
VALUES
(1,"Joe",1011011100,"card")

INSERT INTO Purchase(purchaseid, customerid, productid, time, quantity, price, tax, cost)
VALUES
(1,1,1,"9:00",1,100.00,0.1,110.0)
``` 

