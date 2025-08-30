# 🧠 Mindly - Sistema de Agendamento para Clínicas de Saúde Mental

**Mindly** é um sistema web para facilitar o gerenciamento de atendimentos em clínicas de saúde mental. Voltado para **psicólogos**, **psiquiatras** e **recepcionistas**, oferece um ambiente seguro, simples e acessível para organização de consultas e histórico de pacientes.

---

## 👥 Perfis de Usuário

* **Recepcionista**: realiza, edita e cancela agendamentos.
* **Profissionais (Psicólogo/Psiquiatra)**: definem horários disponíveis, bloqueiam horários e acessam o histórico clínico de seus pacientes.
* **Administrador**: cadastra novos profissionais e colaboradores.

---

## ✅ Funcionalidades Principais

* 📅 **Agendamento de Consultas** – criar, editar e cancelar com base na agenda dos profissionais.
* 👤 **Cadastro de Pacientes** – dados como nome, CPF, telefone, data de nascimento e e-mail.
* 🗓️ **Agenda Dinâmica** – visualização diária/semanal e bloqueio de horários.
* 🧾 **Histórico Clínico** – acompanhamento dos atendimentos realizados.
* 🔐 **Login com Controle de Acesso** – permissões específicas por perfil.

---

## 💻 Tecnologias Utilizadas

* **Java 17** – linguagem principal
* **Spring Boot 3.x** – framework para aplicações web
* **Spring MVC** – controllers, rotas e views
* **Spring Data JPA (Jakarta Persistence)** – persistência ORM
* **Thymeleaf** – templates HTML dinâmicos
* **Jakarta Validation** – validação com anotações (`@NotNull`, `@NotBlank` etc.)
* **Tailwind CSS** – estilização (utilitários CSS)
* **HTML/CSS** – estrutura e estilo de páginas
* **Git** – versionamento

---

## 🚀 Como Executar (detalhado, sem Maven)

### 0) Pré-requisitos

1. **Java 17 JDK** instalado

   * Verifique: `java -version` deve mostrar **17.x**
   * Windows: baixe o JDK 17 (Temurin, Oracle ou Zulu).&#x20;

2. **Git** instalado

   * Verifique: `git --version`

3. **Visual Studio Code** com extensões:

   * **Extension Pack for Java** (Microsoft)
   * **Spring Boot Extension Pack** (Pivotal/VMware)

---

### 1) Clonar o repositório

```bash
# use HTTPS ou SSH conforme seu GitHub
git clone https://github.com/usuario/mindly.git
cd mindly
```

---

### 2) Abrir o projeto no VS Code

* No VS Code, **File → Open Folder** e selecione a pasta do projeto.
* Aguarde a indexação do Java. O VS Code deve detectar a estrutura do projeto e preparar o classpath.

> Dica: se aparecer um prompt “**Importar projeto Java**”, aceite.

---

### 3) Executar a aplicação no VS Code

Há duas maneiras simples dentro do VS Code:

**A) Pela classe principal**

1. No Explorador, abra a classe com `@SpringBootApplication` (ex.: `MindlyApplication.java`).
2. Clique em **Run** ▶️ acima do método `main` ou use **Run → Run Without Debugging**.

**B) Pelo Spring Boot Dashboard**

1. Abra o painel **Spring Boot Dashboard** (ícone de folha na barra lateral ou `Ctrl+Shift+P` → “Spring Boot Dashboard: Focus on Contributions View”).
2. Localize o projeto **Mindly** e clique em **Run**.

Quando iniciar com sucesso, você verá logs parecidos com: `Tomcat started on port(s): 8080`.

Acesse em: [**http://localhost:8080**](http://localhost:8080)

---

### 4) Problemas comuns e soluções

* \*\*Erro: \*\*\`\` → verifique as credenciais do banco e se o servidor está ativo.
* **Erro de versão do Java** → garanta que o VS Code está usando o **JDK 17** (Settings → `java.configuration.runtimes`).
* **Porta 8080 em uso** → mude `server.port` para outra (ex.: `server.port=8081`).
* **Classe principal não aparece para rodar** → aguarde a conclusão do “Java Projects Import” ou reabra o VS Code.

---

## 🖼️ Wireframe

```markdown
![Wireframe do Projeto](./docs/wireframe.png)
```

---

## 📂 Estrutura de Pastas

```
mindly/
```

---
## 🙌 Agradecimentos
Agradecemos por explorar o projeto **Mindly**!
