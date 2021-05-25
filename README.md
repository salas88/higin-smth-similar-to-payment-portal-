GET account by an id client

http://localhost:8080/clients/{clientId}

POST (add a new client )

http://localhost:8080/clientAdd

REQUEST

{
"first_name": "Имя",
"last_name": "Фамилия",
"password" : "password',
"accounts" : [
{
    "account_num": "123456789",
    "account_type": "card/simple",
    "balance": 5000.00
    },
    {
    "account_num": "987654321",
    "account_type": "card/simple",
    "balance": 10000.00
}
]
}

POST (created payment)

http://localhost:8080/clients/payment

REQUEST

{
"source_acc_id": 654,
"dest_acc_id": 655,
"amount": 100.00,
"reason": "назначение платежа"
}


POST (created payments)

http://localhost:8080/clients/payment

REQUEST

[
{"source_acc_id": 654,
"dest_acc_id": 655,
"amount": 100.00,
"reason": "назначение платежа"
},
{
"source_acc_id": 655,
"dest_acc_id": 654,
"amount": 1000.00,
"reason": "назначение платежа"
}
]  

GET ( all payments )

http://localhost:8080/clients/payments/history

REQUEST

{
"payer_id": 123,
"recipient_id": 124,
"source_acc_id": 456,
"dest_acc_id": 457
}


