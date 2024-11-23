# Job Management API

Esta API permite gerenciar informações sobre vagas de emprego. Oferece operações para criação, leitura, e exclusão de vagas, além de filtros específicos para busca.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para criação da API REST.
- **Jakarta Validation**: Para validação de entradas nos endpoints.
- **Spring Data JPA**: Para integração com o banco de dados.
- **PostgreSQL**: Para persistência dos dados. 
- **H2 (em memória)**: Para testes simples.
- **Mockito + JUnit 5**: Para testes automatizados.

---

## Endpoints

### **1. Listar Todas as Vagas**
**Endpoint**: `/jobs/all`  
**Método**: `GET`  
**Descrição**: Retorna todas as vagas paginadas.  
**Parâmetros de Consulta**:
- `page`: Número da página (exemplo: `0`).
- `size`: Tamanho da página (exemplo: `5`).
- `sort`: Campo de ordenação e direção (`asc` ou `desc`).

**Exemplo de Requisição**:
```
GET /jobs/all?page=0&size=5&sort=name,asc
```

**Exemplo de Resposta** (HTTP 200):
```json
{
  "content": [
    {
      "id": 1,
      "name": "Software Engineer",
      "description": "Develop and maintain software solutions."
    }
  ],
  "pageable": { ... },
  "totalPages": 1,
  "totalElements": 1
}
```

---

### **2. Listar Vagas Remotas**
**Endpoint**: `/jobs/remote`  
**Método**: `GET`  
**Descrição**: Retorna todas as vagas de trabalho remoto.

**Exemplo de Resposta** (HTTP 200):
```json
[
  {
    "id": 1,
    "name": "Remote Developer",
    "isRemoteWork": true
  }
]
```

---

### **3. Criar Vaga**
**Endpoint**: `/jobs/create`  
**Método**: `POST`  
**Descrição**: Cria uma nova vaga.

**Corpo da Requisição**:
```json
{
  "id": 1,
  "companyId": 101,
  "name": "Software Engineer",
  "description": "Develop and maintain software solutions.",
  "careerPageId": 201,
  "careerPageName": "Tech Careers",
  "careerPageLogo": "https://example.com/logo.png",
  "type": "Full-time",
  "publishedDate": "2023-11-01",
  "applicationDeadline": "2023-12-01",
  "isRemoteWork": true,
  "city": "San Francisco",
  "state": "CA",
  "country": "USA",
  "jobUrl": "https://example.com/job/1",
  "disabilities": false,
  "workplaceType": "Hybrid",
  "careerPageUrl": "https://example.com/careers"
}
```

**Exemplo de Resposta** (HTTP 200):
```json
"Job created with identifier: f47ac10b-58cc-4372-a567-0e02b2c3d479"
```

---

### **4. Criar Múltiplas Vagas**
**Endpoint**: `/jobs/create/batch`  
**Método**: `POST`  
**Descrição**: Cria várias vagas em uma única chamada.

**Corpo da Requisição**:
```json
{
  "jobs": [
    {
      "id": 1,
      "companyId": 101,
      "name": "Software Engineer",
      "description": "Develop and maintain software solutions."
    },
    {
      "id": 2,
      "companyId": 102,
      "name": "Data Scientist",
      "description": "Analyze and interpret complex data."
    }
  ]
}
```

**Exemplo de Resposta** (HTTP 200):
```json
[
  "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "f47ac10b-58cc-4372-a567-0e02b2c3d480"
]
```

---

### **5. Buscar Vagas por Descrição**
**Endpoint**: `/jobs/description/{description}`  
**Método**: `GET`  
**Descrição**: Busca vagas por uma descrição específica.

**Exemplo de Requisição**:
```
GET /jobs/description/Software
```

**Exemplo de Resposta** (HTTP 200):
```json
[
  {
    "id": 1,
    "name": "Software Engineer",
    "description": "Develop and maintain software solutions."
  }
]
```

---

### **6. Buscar Vaga por ID**
**Endpoint**: `/jobs/{id}`  
**Método**: `GET`  
**Descrição**: Busca uma vaga específica pelo ID.

**Exemplo de Requisição**:
```
GET /jobs/1
```

**Exemplo de Resposta** (HTTP 200):
```json
{
  "id": 1,
  "name": "Software Engineer",
  "description": "Develop and maintain software solutions."
}
```

---

### **7. Buscar Vaga por Identificador**
**Endpoint**: `/jobs/identifier/{identifier}`  
**Método**: `GET`  
**Descrição**: Busca uma vaga específica pelo identificador (`UUID`).

**Exemplo de Requisição**:
```
GET /jobs/identifier/f47ac10b-58cc-4372-a567-0e02b2c3d479
```

**Exemplo de Resposta** (HTTP 200):
```json
{
  "id": 1,
  "identifier": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "name": "Software Engineer"
}
```

---

### **8. Deletar Vaga por ID**
**Endpoint**: `/jobs/{id}`  
**Método**: `DELETE`  
**Descrição**: Remove uma vaga pelo ID.

**Exemplo de Requisição**:
```
DELETE /jobs/1
```

**Exemplo de Resposta** (HTTP 204):
Sem conteúdo.

---

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/job-management-api.git
   cd job-management-api
   ```

2. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Acesse a aplicação em `http://localhost:8080`.

---

## Testes

- Os testes foram escritos usando **JUnit 5** e **Mockito**.
- Para executar os testes:
  ```bash
  ./mvnw test
  ```

**Testes Cobertos**:
1. Criação de vagas (`createJob`, `createBatchJob`).
2. Consulta de vagas paginadas e com filtros.
3. Exceções em casos inválidos, como criar lista de vagas vazia.

---

## Autor
- **Nome:** [Maxson Almeida]
- **Contato:** [maxsonferovante@gmail.com]