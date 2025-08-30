# Entrega do projeto pt.1 01/09/2025

1. Organização Repositório e Gestão de atividades Projeto
- [ ] Adequar o arquivo .gitignore, remover arquivos desnecessários como: pasta target/, arquivos de IDE.

2. Documentação Projeto
- [ ] README.md com: nome e objetivo do projeto, tecnologias utilizadas (Java 17, Spring Boot 3.x, Thymeleaf, etc.) e um guia de "Como Executar" de como clonar, configurar as dependências e rodar a aplicação. Incluir imagem do wireframe.
      - [ ] adicionar wireframe e exemplo de pastas

3. Atendimento Estrutura padrão de pacotes e organização Projeto
- [ ] Adoção da arquitetura em camadas, de pacotes: com.empresa.projeto.controller, com.empresa.projeto.service, com.empresa.projeto.entity, com.empresa.projeto.dto, etc.

4. Implementação View Controller
- [ ] Classes anotadas com @Controller. Métodos que usam @GetMapping para preparar e exibir as views e @PostMapping para processar a submissão de formulários. Uso correto do objeto Model para enviar dados para a view e do @ModelAttribute para receber os dados do formulário, vinculando-os a um DTO.

5. Implementação Entidades
- [ ] Classes de domínio anotadas com @Entity, representando as tabelas do banco de dados. Uso correto de @Id para a chave primária e @GeneratedValue para a estratégia de geração. Os atributos devem estar mapeados com os tipos de dados adequados.

6. Implementação Service com a lógica de negócio
- [ ] Classes anotadas com @Service e injetadas nos controllers. A lógica de negócio (regras de validação complexas, orquestração de operações, conversão entre DTOs [quando necessário] e Entidades), mantendo os controllers "magros" e focados em gerenciar o fluxo web.

7. Implementação View
- [ ] Arquivos HTML (utilizando Thymeleaf) que renderizam os dados recebidos do controller. Uso de expressões Thymeleaf para exibir dados (th:text), criar links (th:href), popular formulários (th:object, th:field) e exibir mensagens de erro (th:errors, th:if).

8. Validação Entidades
- [ ] Aplicação de anotações do Jakarta Bean Validation (@NotBlank, @Size, @Email, @Positive, etc.) para garantir a consistência dos dados. A prática ideal é aplicar validações nos DTOs (para a entrada de dados da web) e também nas Entidades (como uma camada final de proteção da integridade do domínio).

9. Relacionamentos e Restrições Camada de Persistência (Banco)
- [ ] Mapeamento correto dos relacionamentos entre entidades (@ManyToOne, @OneToMany, etc.), com atenção especial ao uso de mappedBy para definir o lado "inverso" em associações bidirecionais. Além disso, uso de restrições (@Column(nullable = false, unique = true)) que se traduzem em constraints no banco de dados, garantindo a integridade referencial e dos dados na camada mais baixa.

10. Implementação e Organização Layouts
- [ ] Criação de um layout base consistente para a aplicação usando fragmentos Thymeleaf (th:fragment, th:replace, th:insert). Componentes comuns como cabeçalho, rodapé e menu de navegação devem ser definidos como fragmentos e reutilizados nas diferentes páginas, seguindo o princípio DRY (Don't Repeat Yourself).
   - Fixo: barra de navegação



- Pagina de Login: logar usuário
- Navbar: Pacientes, Consulta
- Main page: "sessões" marcadas pra X data
- Pacientes: listagem de pacientes cadastrados, botão de novo paciente
- Consulta: consulta vinculado a paciente e médico, botão de nova consulta
