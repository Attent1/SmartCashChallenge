# SmartCash
descriçao

## Endpoints
- Assinatura 
    - [create](#cadastrar-assinatura)
    - [readAll](#listar-todas-assinatura)
    - [readItem](#listar-assinatura)
    - [update](#editar-assinatura)
    - [delete](#apagar-assinatura)

- Empresa 
    - [create](#cadastrar-empresa)
    - [readAll](#listar-todas-empresa)
    - [readItem](#listar-empresa)
    - [update](#editar-empresa)
    - [delete](#apagar-empresa)

- Fluxo de Caixa 
    - [create](#cadastrar-fluxoCaixa)
    - [readAll](#listar-todos-fluxoCaixa)
    - [readItem](#listar-fluxoCaixa)
    - [update](#editar-fluxoCaixa)
    - [delete](#apagar-fluxoCaixa)    

- Registro de Assinatura 
    - [create](#cadastrar-registroAssinatura)
    - [readAll](#listar-todos-registroAssinatura)
    - [readItem](#listar-registroAssinatura)
    - [update](#editar-registroAssinatura)
    - [delete](#apagar-registroAssinatura)   

- Usuário 
    - [create](#cadastrar-usuario)
    - [readAll](#listar-todos-usuario)
    - [readItem](#listar-usuario)
    - [update](#editar-usuario)
    - [delete](#apagar-usuario)

### Cadastrar assinatura

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
        "id_ASSINATURA": 1,
        "tipo": "Premium",
        "valor": 2999.99
    }

```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 201    | Assinatura criada com sucesso |
| 400    | Erro de validação - verifique o corpo da requisição |

### Listar todas assinatura
`GET` /assinatura

Retorna um array com todas as Assinaturas cadastradas

**Exemplo de resposta**
```js
[   
    {
        "id_ASSINATURA": 1,
        "tipo": "Basic",
        "valor": 999.99
    },        
    {
        "id_ASSINATURA": 2,
        "tipo": "Premium",
        "valor": 2999.99
    }
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

### Listar assinatura
`GET` /assinatura/{id}

Retorna a assinatura do `id` informado.

**Exemplo de resposta**
```js
{
	"id_ASSINATURA": 1,
	"tipo": "Premium",
	"valor": 10000.00
}
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

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
        "id_ASSINATURA": 1,  
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


### Apagar assinatura

`DELETE` /assinatura/{id}

Apaga a assinatura com o `id` informado.

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 204    | Assinatura apagada com sucesso |
| 404    |  ID da assinatura não encontrado |

### Cadastrar empresa

`POST` /empresa

Insere uma nova empresa

|campo|tipo|obrigatório|descrição
|-----|----|-----------:|---------
|nome |string|✅| nome da empresa
|cnpj|string|✅|cnpj da empresa
|ramo|string|✅|ramo da empresa

**Corpo da requisição**

```js

    {   
        "nome": "Kabum",
        "cnpj": "86995983000169",
	    "ramo": "Eletrônico"	
    }

```

**Exemplo de resposta**

```js

    {
        "id_EMPRESA": 3,
	    "nome": "Apple",
	    "cnpj": "86995983000169",
	    "ramo": "Eletrônico"
    }

```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 201    | empresa criada com sucesso |
| 400    | Erro de validação - verifique o corpo da requisição |

### Listar todas empresa
`GET` /empresa

Retorna um array com todas as empresas cadastradas

**Exemplo de resposta**
```js
[
  {
    "id_EMPRESA": 1,
    "nome": "Kabum",
    "cnpj": "81278841000167",
    "ramo": "Eletrônico"
  },
  {
    "id_EMPRESA": 3,
    "nome": "Apple",
    "cnpj": "86995983000169",
    "ramo": "Eletrônico"
  }
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

### Listar empresa
`GET` /empresa/{id}

Retorna a empresa do `id` informado.

**Exemplo de resposta**
```js
{
	"id_EMPRESA": 1,
	"nome": "Apple",
	"cnpj": "86995983000169",
	"ramo": "Eletrônico"
}
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

### Editar empresa

`PUT` /empresa/{id}

atualiza os dados da empresa com o `id` informado.

**Corpo da requisição**

```js

    {
       "nome": "Globo",
       "cnpj": "86995983000169",
       "ramo": "Entretenimento"
    }

```

**Exemplo de resposta**
```js
[       
    {
       "id_EMPRESA": 3,
       "nome": "Globo",
       "cnpj": "86995983000169",
       "ramo": "Entretenimento"
    }
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | empresa atualizada com sucesso |
| 400    |  A validação falhou - verifique o corpo da requisição |
| 404    |  ID da empresa não encontrado |


### Apagar empresa

`DELETE` /empresa/{id}

Apaga a empresa com o `id` informado.

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 204    | empresa apagada com sucesso |
| 404    |  ID da empresa não encontrado |

### Cadastrar fluxoCaixa

`POST` /fluxoCaixa

Insere um novo fluxo de caixa

|campo|tipo|obrigatório|descrição
|-----|----|-----------:|---------
|tipo |string|✅| tipo do fluxo de caixa
|valor|number|✅|valor do fluxo de caixa
|descricao|string|✅|descricao do fluxo de caixa
|id_EMPRESA|number|✅|ID da empresa do fluxo de caixa
|id_USUARIO|number|✅|ID do usuário do fluxo de caixa

**Corpo da requisição**

```js

    {
        "tipo": "RECEITA",
        "valor": 10000,
        "descricao": "Cliente",
        "id_EMPRESA": 1,
        "id_USUARIO": 2                
    }
```

**Exemplo de resposta**

```js

    {
        "id_USUARIO": 2,
        "id_EMPRESA": 1,
        "id_FLUXO": 4,
        "tipo": "RECEITA",
        "valor": 10000,
        "descricao": "Cliente",
        "data_INCLUSAO": "2024-04-08"
    }

```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 201    | fluxo de caixa criado com sucesso |
| 400    | Erro de validação - verifique o corpo da requisição |

### Listar todos fluxoCaixa
`GET` /fluxoCaixa

Retorna um array com todos os fluxos de caixa cadastrados

**Exemplo de resposta**
```js
[
  {
    "id_USUARIO": 2,
    "id_EMPRESA": 1,
    "id_FLUXO": 1,
    "tipo": "RECEITA",
    "valor": 10000.00,
    "descricao": "Cliente",
    "data_INCLUSAO": "2024-04-08"
  },
  {
    "id_USUARIO": 2,
    "id_EMPRESA": 1,
    "id_FLUXO": 3,
    "tipo": "DESPESA",
    "valor": 5000.00,
    "descricao": "Folha de pagamento",
    "data_INCLUSAO": "2024-04-08"
  }
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

### Listar fluxoCaixa
`GET` /fluxoCaixa/{id}

Retorna o fluxo de caixa do `id` informado.

**Exemplo de resposta**
```js
{
	"id_FLUXO": 1,
	"id_EMPRESA": 1,
	"id_USUARIO": 2,
	"tipo": "RECEITA",
	"valor": 10000.00,
	"descricao": "Cliente",
	"data_INCLUSAO": "2024-04-09"
}
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

### Editar fluxoCaixa

`PUT` /fluxoCaixa/{id}

atualiza os dados do fluxo de caixa com o `id` informado.

**Corpo da requisição**

```js

{
	"tipo": "DESPESA",
	"valor": 7000,
	"descricao": "Conta de luz",
	"id_EMPRESA": 1,
	"id_USUARIO": 2
	
}

```

**Exemplo de resposta**
```js
       
{
    "id_USUARIO": 2,
    "id_EMPRESA": 1,
    "id_FLUXO": 3,
    "tipo": "DESPESA",
    "valor": 7000,
    "descricao": "Conta de luz",
    "data_INCLUSAO": "2024-04-08"
}

```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | fluxo de caixa atualizado com sucesso |
| 400    |  A validação falhou - verifique o corpo da requisição |
| 404    |  ID do fluxo de caixa não encontrado |


### Apagar fluxoCaixa

`DELETE` /fluxoCaixa/{id}

Apaga o fluxo de caixa com o `id` informado.

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 204    | fluxo de caixa apagado com sucesso |
| 404    |  ID do fluxo de caixa não encontrado |

### Cadastrar registroAssinatura

`POST` /registroAssinatura

Insere um novo registro da assinatura

|campo|tipo|obrigatório|descrição
|-----|----|-----------:|---------
|id_EMPRESA |number|✅| ID da empresa do registro da assinatura
|id_ASSINATURA|number|✅|ID da assinatura do registro da assinatura

**Corpo da requisição**

```js

{	
	"id_EMPRESA": 1,
	"id_ASSINATURA": 1		
}
```

**Exemplo de resposta**

```js

   {
	"id_ASSINATURA": 1,
	"id_EMPRESA": 1,
	"id_ASSINATURA_EMPRESA": 1,
	"data_VENCIMENTO": "2024-07-08",
	"data_AQUISICAO": "2024-04-08"
   }

```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 201    | Registro de assinatura criado com sucesso |
| 400    | Erro de validação - verifique o corpo da requisição |

### Listar todos registroAssinatura
`GET` /registroAssinatura

Retorna um array com todos os Registros de assinatura cadastrados

**Exemplo de resposta**
```js
[
	{
		"id_ASSINATURA": 1,
		"id_EMPRESA": 1,
		"id_ASSINATURA_EMPRESA": 1,
		"data_VENCIMENTO": "2024-07-08",
		"data_AQUISICAO": "2024-04-08"
	}
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

### Listar registroAssinatura
`GET` /registroAssinatura/{id}

Retorna o registro da Assinatura do `id` informado.

**Exemplo de resposta**
```js
{
	"id_ASSINATURA": 3,
	"id_EMPRESA": 2,
	"data_AQUISICAO": "2024-04-09",
	"data_VENCIMENTO": "2024-07-09",
	"id_ASSINATURA_EMPRESA": 1
}
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

### Editar registroAssinatura

`PUT` /registroAssinatura/{id}

atualiza os dados do Registro de assinatura com o `id` informado.

**Corpo da requisição**

```js

{	
	"id_EMPRESA": 2,
	"id_ASSINATURA": 3	
}

```

**Exemplo de resposta**
```js
       
{
	"id_ASSINATURA": 1,
	"id_EMPRESA": 2,
	"id_ASSINATURA_EMPRESA": 2,
	"data_VENCIMENTO": "2024-07-08",
	"data_AQUISICAO": "2024-04-08"
}

```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | Registro de assinatura atualizado com sucesso |
| 400    |  A validação falhou - verifique o corpo da requisição |
| 404    |  ID do Registro de assinatura não encontrado |


### Apagar registroAssinatura

`DELETE` /registroAssinatura/{id}

Apaga o Registro de assinatura com o `id` informado.

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 204    | Registro de assinatura apagado com sucesso |
| 404    |  ID do Registro de assinatura não encontrado |

### Cadastrar usuario

`POST` /usuario

Insere umo novo usuário

|campo|tipo|obrigatório|descrição
|-----|----|-----------:|---------
|nome |string|✅| nome do usuário
|documento|string|✅|documento do usuário
|senha|string|✅|documento do usuário
|id_EMPRESA|number|✅|ID da empresa do usuário

**Corpo da requisição**

```js

    {
        "nome": "Jorge",
        "documento": "87207195000123",
        "senha": "12345678",
        "id_EMPRESA": 1	
    }

```

**Exemplo de resposta**

```js
    {
        "documento": "87207195000123",
        "id_USUARIO": 2,
        "id_EMPRESA": 1,
        "login_USUARIO": "J0123",
        "nome": "Jorge",
        "senha": "12345678"
    }
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 201    | usuário criado com sucesso |
| 400    | Erro de validação - verifique o corpo da requisição |

### Listar todos usuario
`GET` /usuario

Retorna um array com todos os usuários cadastrados

**Exemplo de resposta**
```js   
[
	{
		"documento": "81278841000167",
		"id_USUARIO": 1,
		"id_EMPRESA": 1,
		"login_USUARIO": "J0167",
		"nome": "Jorge",
		"senha": "12345678"
	},
	{
		"documento": "87207195000123",
		"id_USUARIO": 2,
		"id_EMPRESA": 1,
		"login_USUARIO": "J0123",
		"nome": "Judite",
		"senha": "12345678"
	}
]

```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

### Listar usuario
`GET` /usuario/{id}

Retorna o usuário do `id` informado.

**Exemplo de resposta**
```js
{
	"id_EMPRESA": 1,
	"id_USUARIO": 1,
	"documento": "87207195000123",
	"nome": "Aldair",
	"login_USUARIO": "A0123",
	"senha": "12345678"
}
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |

### Editar usuario

`PUT` /usuario/{id}

atualiza os dados do usuário com o `id` informado.

**Corpo da requisição**

```js

    {
        "nome": "Aldair",
        "documento": "87207195000123",
        "senha": "12345678",
        "id_EMPRESA": 1	
    }

```

**Exemplo de resposta**
```js
[       
   {
        "documento": "87207195000123",
        "id_USUARIO": 1,
        "id_EMPRESA": 1,
        "login_USUARIO": "A0123",
        "nome": "Aldair",
        "senha": "12345678"
    }
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | usuário atualizado com sucesso |
| 400    |  A validação falhou - verifique o corpo da requisição |
| 404    |  ID da usuario não encontrado |


### Apagar usuario

`DELETE` /usuario/{id}

Apaga o usuário com o `id` informado.

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 204    | usuário apagado com sucesso |
| 404    |  ID do usuário não encontrado |
