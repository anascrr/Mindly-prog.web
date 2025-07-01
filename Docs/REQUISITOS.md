# Requisitos básicos de negócio

## Requisitos Funcionais — Sistema Mindly

O **Mindly** é um sistema web de agendamento voltado para clínicas de saúde mental. Ele tem como objetivo principal facilitar o gerenciamento de atendimentos psicológicos e psiquiátricos por meio de uma plataforma simples, segura e acessível. O sistema contempla três tipos principais de usuários: **recepcionistas**, **psicólogos** e **psiquiatras**. Cada perfil possui permissões específicas, respeitando o fluxo de trabalho da clínica.

Com o Mindly, é possível:

- Controlar a agenda de profissionais
- Realizar agendamentos
- Cadastrar pacientes
- Acompanhar históricos clínicos
- Enviar lembretes automáticos

Tudo isso em um só lugar.

---

## Requisitos do sistema

- **R01** - O sistema deve permitir o login por email e senha com distinção de papéis (recepcionista, psicólogo, psiquiatra);
- **R02** - O sistema deve permitir o cadastro de novos usuários (colaboradores da clínica), a partir do administrador (psicólogo ou psiquiatra);
- **R03** - O profissional deve poder definir sua disponibilidade semanal para consultas;
- **R04** - O recepcionista deve poder visualizar a agenda completa dos profissionais em formato diário e semanal;
- **R05** - O recepcionista deve agendar consultas com base na disponibilidade cadastrada pelos profissionais;
- **R06** - O sistema deve impedir o agendamento de horários já ocupados ou bloqueados na agenda do profissional;
- **R07** - Os profissionais devem poder bloquear horários específicos da agenda em casos de imprevistos ou folgas;
- **R08** - O sistema deve permitir o cadastro de pacientes, com dados como nome completo, CPF, telefone e e-mail;
- **R09** - Os profissionais devem poder visualizar o histórico de atendimentos de cada paciente, contendo datas e observações;
- **R10** - O recepcionista deve poder editar ou cancelar agendamentos já realizados;
- **R11** - Os usuários devem poder alterar seus dados de perfil, incluindo senha, e-mail e telefone.