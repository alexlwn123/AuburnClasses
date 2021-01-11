Project 1
=========
Alexander Lewin
---------------

## User Stories: 
  - As a user, I want to change my password and/or full name.  
  - As an admin, I want to add a new user into the system.  
  - As an admin, I want to update the type/class of a user into the system (e.g., from cashier to manager).  
  - As a manager, I want to add a new product or update a current product in the system.  
  - As a manager, I want to view a summary report of sales from the system.   
  - As a cashier, I want to add a new customer or update a current customer in the system.  
  - As a cashier, I want to add a new purchase or update a current purchase (only price and quantity) in the system.  
  - As a customer, I want to add a new purchase into the system.  
  - As a customer, I want to view my purchase history into the system.  
  - As a customer, I want to search for product information (based on name, price) in the system.  
 
## Write Use Cases:   

## User: 

|**Actor**|**System Response**|
|----------|-------------------|
|1. Change Credentials ![main](mainscreen.png)  |2. System redirects to 'Change Credentials Screen' ![AddProduct](creds.png)|
|3. Fills in data and clicks add ![FilledIn](creds.png)|4. Display 'Success' screen ![AddedProduct](AddedProduct.png)|
|5. Click 'OK' Button ![AddedProduct](AddedProduct.png)|6. Display 'Main' ![main](mainscreen.png) |

## Manager: 

|**Actor**|**System Response**|
|----------|-------------------|
|1. Choose 'Add Product' ![main](mainscreen.png)  |2. System redirects to 'Add Product Screen' ![AddProduct](AddProductScreen.png)|
|3. Fills in data and clicks add ![FilledIn](FilledInProduct.png)|4. Display 'Added Product' screen ![AddedProduct](AddedProduct.png)|
|5. Click 'OK' Button ![AddedProduct](AddedProduct.png)|6. Display 'Main' ![main](mainscreen.png) |

|**Actor**|**System Response**|
|----------|-------------------|
|1. Choose 'View Summary' ![main](mainscreen.png)  |2. System redirects to 'Summary' ![AddProduct](AddCustomerScreen.png)|
|3. Click 'OK' Button ![AddedProduct](AddedCustomer.png)|4. Display 'Main' ![main](mainscreen.png) |

## Cashier: 

|**Actor**|**System Response**|
|----------|-------------------|
|1. Choose 'Add Customer' ![main](mainscreen.png)  |2. System redirects to 'Add Customer Screen' ![AddProduct](AddCustomerScreen.png)|
|3. Fills in data with errors and clicks add ![FilledIn](FilledInCustomer.png)|4. Display 'error' screen ![AddedProduct](error.png)|
|5. Click 'OK' Button ![AddedProduct](error.png)|6. Display 'Add Customer Screen' ![main](AddCustomerScreen.png) |

|**Actor**|**System Response**|
|----------|-------------------|
|1. Choose 'Add Purchase' ![main](mainscreen.png)  |2. System redirects to 'Add Purchase Screen' ![AddProduct](AddPurchaseScreen.png)|
|3. Fills in data and clicks add ![FilledIn](FilledInPurchase.png)|4. Display 'Added Purchase' screen ![AddedProduct](AddedPurchase.png)|
|5. Click 'OK' Button ![AddedProduct](AddedPurchase.png)|6. Display 'Main' ![main](mainscreen.png) |


## Customer: 

|**Actor**|**System Response**|
|----------|-------------------|
|1. Choose 'Add Purchase' ![main](mainscreen.png)  |2. System redirects to 'Add Purchase Screen' ![AddProduct](AddPurchaseScreen.png)|
|3. Fills in data and clicks add ![FilledIn](FilledInPurchase.png)|4. Display 'Added Purchase' screen ![AddedProduct](AddedPurchase.png)|
|5. Click 'OK' Button ![AddedProduct](AddedPurchase.png)|6. Display 'Main' ![main](mainscreen.png) |

|**Actor**|**System Response**|
|----------|-------------------|
|1. Choose 'Add Customer' ![main](mainscreen.png)  |2. System redirects to 'Add Customer Screen' ![AddProduct](AddCustomerScreen.png)|
|3. Fills in data with errors and clicks add ![FilledIn](FilledInCustomer.png)|4. Display 'error' screen ![AddedProduct](error.png)|
|5. Click 'OK' Button ![AddedProduct](error.png)|6. Display 'Add Customer Screen' ![main](AddCustomerScreen.png) |

|**Actor**|**System Response**|
|----------|-------------------|
|1. Choose 'Search Product' ![main](mainscreen.png)  |2. System redirects to 'Search Product Screen' ![AddProduct](AddPurchaseScreen.png)|
|5. Click 'OK' Button ![AddedProduct](AddedPurchase.png)|6. Display 'Main' ![main](mainscreen.png) |
