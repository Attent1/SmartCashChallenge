# SmartCash
descriçao

## Endpoints
- Assinatura 
    - [readAll](#listar-todos)
    - [create](#cadastrar)
    - [delete](#apagar-assinatura)
    - [update](#editar-assinatura)

### Listar todos
`GET` /assinatura

Retorna um array com todos as Assinaturas cadastradas

**Exemplo de resposta**
```js
[   
    {
        "id": 1,
        "tipo": "Basic",
        "valor": 999.99
    },        
    {
        "id": 2,
        "tipo": "Premium",
        "valor": 2999.99
    }
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |


### Cadastrar

`POST` /assinatura

Insere uma nova assinatura

|campo|tipo|obrigatório|descrição
|-----|----|-----------:|---------
|tipo |string|✅| tipo da assinatura
|valor|number|✅|valor da assinatura

**Corpo da requisição**

```js

    {
        "tipo": "Premium",
        "valor": 2999.99
    }

```

**Exemplo de resposta**

```js

    {
        "id": 1,
        "tipo": "Premium",
        "valor": 2999.99
    }

```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 201    | Assinatura criada com sucesso |
| 400    | Erro de validação - verifique o corpo da requisição |


### Apagar assinatura

`DELETE` /assinatura/{id}

Apaga a assinatura com o `id` informado.

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 204    | Assinatura apagada com sucesso |
| 404    |  ID da assinatura não encontrado |


### Editar Assinatura

`PUT` /assinatura/{id}

atualiza os dados da assinatura com o `id` informado.

**Corpo da requisição**

```js

    {
        "tipo": "Medium",
        "valor": 1999.99
    }

```

**Exemplo de resposta**
```js
[       
      {
        "id": 1,  
        "tipo": "Medium",
        "valor": 1999.99
    }
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | Assinatura atualizada com sucesso |
| 400    |  A validação falhou - verifique o corpo da requisição |
| 404    |  ID da assinatura não encontrado |
