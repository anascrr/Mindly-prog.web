## ✅ Checklist – Entrega Final do Projeto (API REST) P2  29/09/2025

### Parte I – Design e Implementação da API REST
- [x] Usar **verbos HTTP corretos** (GET, POST, PUT, PATCH, DELETE).  
- [x] Estruturar URLs baseadas em **recursos (substantivos no plural)** e com versionamento (ex: `/api/v1/produtos`).  
- [x] Criar endpoints CRUD (**Criar, Ler, Atualizar, Deletar**) para **todas as entidades principais**.  
- [x] Encapsular a lógica de negócio na camada de **@Service**.  
- [x] Retornar **status HTTP corretos** com `ResponseEntity`:  
  - [x] `201 Created` (com Location no cabeçalho) para criação.  
  - [x] `204 No Content` para exclusão.  
  - [x] `200 OK` para sucesso nas demais operações.  

---

### Parte II – Persistência e Lógica de Negócio
- [x] Usar **Spring Data JPA** para a camada de persistência.  
- [x] Implementar **consultas customizadas** nos Repositories (`Query Methods` ou `@Query`) para buscas específicas.  

---

### Parte III – Documentação e Tratamento de Exceções
- [x] Implementar **tratamento de exceções global** com `@ControllerAdvice`.  
- [x] Retornar erros no formato **ProblemDetail (RFC 7807)**.  
- [x] Criar **exceções customizadas** e lançá-las na camada de Serviço:  
  - [x] `RecursoNaoEncontradoException`.  
  - [x] `RegraDeNegocioException`.  
- [x] Configurar **Swagger (springdoc-openapi)**:  
  - [x] Documentar endpoints com `@Tag`, `@Operation`, `@ApiResponse`.  
  - [x] Documentar DTOs e parâmetros.  
  - [x] Garantir que a **UI do Swagger** esteja acessível e funcional.  

---

### Parte IV – Entrega no GitHub
- [x] Criar **Pull Request**: da branch `desenvolvimento` para `main`, com título **Entrega Final P2**.  
- [x] Marcar o commit final com a Tag **v2.0-FINAL**.  
- [x] Atualizar o **README.md** com:  
  - [x] Instruções para configurar e executar a API.  
  - [x] Link para a documentação do Swagger.  
