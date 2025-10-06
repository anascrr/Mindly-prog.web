    package br.edu.iff.ccc.mindly.entities;

    import java.util.ArrayList;
    import java.util.List;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "pacientes")
    public class Paciente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable=false)
        private String nome;
        
        @Column(nullable=false, unique=true) 
        private String email;

        @Column(nullable=false)
        private String telefone;

        @Column(nullable=false)
        private String endereco;

        @Column(nullable=false)
        private String dataNascimento;

        @Column(nullable=false, unique=true)
        private String cpf;

        @Column(nullable=false)
        private String planoSaude;

        @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Consulta> consultas = new ArrayList<>();

        public Paciente() {
        }

        public Paciente(Long id, String nome, String email, String telefone, String endereco, String dataNascimento,
                String cpf, String planoSaude) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
            this.endereco = endereco;
            this.dataNascimento = dataNascimento;
            this.cpf = cpf;
            this.planoSaude = planoSaude;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getPlanoSaude() {
            return planoSaude;
        }

        public void setPlanoSaude(String planoSaude) {
            this.planoSaude = planoSaude;
        }
    }
