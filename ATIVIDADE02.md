## ✅ Checklist – Entrega Final do Projeto (API REST) P2  29/09/2025

### Parte I – Design e Implementação da API REST
- [ ] Usar **verbos HTTP corretos** (GET, POST, PUT, PATCH, DELETE).  
- [ ] Estruturar URLs baseadas em **recursos (substantivos no plural)** e com versionamento (ex: `/api/v1/produtos`).  
- [ ] Criar endpoints CRUD (**Criar, Ler, Atualizar, Deletar**) para **todas as entidades principais**.  
- [ ] Encapsular a lógica de negócio na camada de **@Service**.  
- [ ] Retornar **status HTTP corretos** com `ResponseEntity`:  
  - [ ] `201 Created` (com Location no cabeçalho) para criação.  
  - [ ] `204 No Content` para exclusão.  
  - [ ] `200 OK` para sucesso nas demais operações.  

---

### Parte II – Persistência e Lógica de Negócio
- [ ] Usar **Spring Data JPA** para a camada de persistência.  
- [ ] Implementar **consultas customizadas** nos Repositories (`Query Methods` ou `@Query`) para buscas específicas.  

---

### Parte III – Documentação e Tratamento de Exceções
- [ ] Implementar **tratamento de exceções global** com `@ControllerAdvice`.  
- [ ] Retornar erros no formato **ProblemDetail (RFC 7807)**.  
- [ ] Criar **exceções customizadas** e lançá-las na camada de Serviço:  
  - [ ] `RecursoNaoEncontradoException`.  
  - [ ] `RegraDeNegocioException`.  
- [ ] Configurar **Swagger (springdoc-openapi)**:  
  - [ ] Documentar endpoints com `@Tag`, `@Operation`, `@ApiResponse`.  
  - [ ] Documentar DTOs e parâmetros.  
  - [ ] Garantir que a **UI do Swagger** esteja acessível e funcional.  

---

### Parte IV – Entrega no GitHub
- [ ] Criar **Pull Request**: da branch `desenvolvimento` para `main`, com título **Entrega Final P2**.  
- [ ] Marcar o commit final com a Tag **v2.0-FINAL**.  
- [ ] Atualizar o **README.md** com:  
  - [ ] Instruções para configurar e executar a API.  
  - [ ] Link para a documentação do Swagger.  
