// Executa o script apenas quando o conteúdo da página estiver totalmente carregado.
document.addEventListener('DOMContentLoaded', function () {

    // Máscara para o campo CPF
    const cpfInput = document.getElementById('cpf');
    if (cpfInput) {
        cpfInput.addEventListener('input', function (e) {
            let value = e.target.value.replace(/\D/g, '').slice(0, 11);
            value = value.replace(/(\d{3})(\d)/, '$1.$2');
            value = value.replace(/(\d{3})\.(\d{3})(\d)/, '$1.$2.$3');
            value = value.replace(/(\d{3})\.(\d{3})\.(\d{3})(\d{1,2})/, '$1.$2.$3-$4');
            e.target.value = value;
        });
    }

    // Máscara para o campo Telefone
    const telefoneInput = document.getElementById('telefone');
    if (telefoneInput) {
        telefoneInput.addEventListener('input', function (e) {
            let value = e.target.value.replace(/\D/g, '').slice(0, 11);
            value = value.replace(/^(\d{2})(\d)/g, '($1) $2');
            value = value.replace(/(\d{5})(\d)/, '$1-$2');
            e.target.value = value;
        });
    }

    // Máscara para o campo Data de Nascimento
    const dataNascimentoInput = document.getElementById('dataNascimento');
    if (dataNascimentoInput) {
        dataNascimentoInput.addEventListener('input', function (e) {
            let value = e.target.value.replace(/\D/g, '').slice(0, 8);
            value = value.replace(/(\d{2})(\d)/, '$1/$2');
            value = value.replace(/(\d{2})\/(\d{2})(\d)/, '$1/$2/$3');
            e.target.value = value;
        });
    }

});