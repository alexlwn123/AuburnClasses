# Activity 4
## Alex Lewin
### 9/5/19

1. 

![Chart](activity5.png)

2. 

Project(ProjectNum, ProjectName, Cost)  
Employee(EmployeeNum, EmployeeName, JobClass)  
JobClass(JobID, JobName, ChargePerHour)  
Assignment(EmployeeNum, ProjNum, HoursWorked)  
3. 

```sql
CREATE TABLE Project(
ProjectNumber int not null primary key,
ProjectName varchar(100),
Cost float,
ProjectLeaderID int
);

CREATE TABLE Employee(
EmployeeNum int not null primary key,
EmployeeName varchar(100),
JobClass int
);

CREATE TABLE JobClass(
JobID int not null primary key,
JobName varchar(100),
ChargePerHour float
);

CREATE TABLE Assignments(
EmployeeNum int not null primary key,
ProjectNum int,
HoursWorked float 
);
``` 

4. 
```sql
INSERT INTO Project(ProjectNum, ProjectName, Cost, ProjectLeaderID)
VALUES
(15, "Evergreen", 105489.7,105)
(18, "Amber Wave", 7171.47, 104)
(22, "Rolling Tide", 13660.10, 113)
(25, "Starflight", 17559.82, 101)

INSERT INTO JobClass(JobID, JobName, ChargePerHour)
VALUES
(1, "Elec. Engineer", 84.5)
(2, "Database Designer", 105)
(3, "Programmer", 35.75)
(4, "Systems Analys", 96.75)
(5, "Applications Designer", 45.95)
(6, "General Support", 18.36)
(7, "DSS Analyst", 45.95)
(8, "Clerical Support", 26.87)

INSERT INTO Employee(EmployeeNum, EmployeeName, JobClass)
VALUES
(103, "June E. Arbough",1)
(101, "John G. News",2)
(105, "Alice K. Johnson",2)
(106, "William Smithfield",3)
(102, "David H. Senior",4)
(114, "Annelise Jones",5)
(118, "James J. Frommer",6)
(104, "Anne K. Ramoras",4)
(112, "Darlene M. Smithson", 7)
(113, "Delbert K. Joenbrood", 5)
(111, "Geoff B. Wabash", 8)
(107, "Maria D. Alonzo", 3)
(115, "Travis B. Bawangi", 4)
(108, "Ralph B. Washington", 4)

INSERT INTO Assignments(EmployeeNum, ProjectNum, HoursWorked)
VALUES
(103,15,23.8)
(101,15,19.4)
(105,15,35.7)
(106,15,12.6)
(102,15,23.8)
(114,18,24.6)
(118,18,45.3)
(104,18,32.4)
(112,18,44.0)
(105,22,64.7)
(104,22,48.4)
(113,22,23.6)
(111,22,22.0)
(106,22,12.8)
(107,25,24.6)
(115,25,45.8)
(101,25,56.3)
(114,25,33.1)
(108,25,23.6)
(118,25,30.5)
(112,25,41.4)
```
