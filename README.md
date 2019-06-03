# bank_operations
This is a project about a RestFull Api without Spring Framework.

## Endpoints

+ Get data of account 

**GET** /banking_business/account/

Body
``` [json]
{
    "client_id": 35099240,
    "pin": 102457,
    "account_number": "347621/1"
}
```

+ Deposit to account

**POST** /banking_business/operation/deposit

Body
``` [json]
{
   "client_id": 35099240,
   "pin": 102457,
   "account_number": "347621/1",
   "amount": 50.0
}
```

+ Take out from account

**POST** /banking_business/operation/deposit

Body
``` [json]
{
   "client_id": 35099240,
   "pin": 102457,
   "account_number": "347621/1",
   "amount": 50.0
}
```

+ List operation of account

**GET** /banking_business/operation/history

Body
``` [json]
{
   "client_id": 35099240,
   "pin": 102457,
   "account_number": "347621/1"
}
```