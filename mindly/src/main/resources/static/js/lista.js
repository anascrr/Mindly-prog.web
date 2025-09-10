function filtrarConsultas() {
    var inputData = document.getElementById("filtroDataConsulta").value;
    var inputPaciente = document.getElementById("filtroPacienteConsulta").value.toLowerCase();
    var linhas = document.getElementsByClassName("consulta-row");

    for (var i = 0; i < linhas.length; i++) {
        var data = linhas[i].getAttribute("data-data");
        var paciente = linhas[i].getAttribute("data-paciente");
        var mostra = true;

        if (inputData && data !== inputData) {
            mostra = false;
        }

        if (inputPaciente && (!paciente || paciente.indexOf(inputPaciente) === -1)) {
            mostra = false;
        }
        
        linhas[i].style.display = mostra ? "" : "none";
    }
}

let ordem = [true, true, true, true]; // true = crescente, false = decrescente

function ordenarTabela(col) {
    let tabela = document.querySelector("table tbody");
    let linhas = Array.from(tabela.querySelectorAll("tr"));
    let getValue = (td) => td.textContent.trim();

    linhas.sort((a, b) => {
        let valA = getValue(a.children[col]);
        let valB = getValue(b.children[col]);
        
        // Para data, converte para formato comparável
        if (col === 0) {
            let [diaA, mesA, anoA] = valA.split("/");
            let [diaB, mesB, anoB] = valB.split("/");
            valA = new Date(`${anoA}-${mesA}-${diaA}`);
            valB = new Date(`${anoB}-${mesB}-${diaB}`);
        }

        // Para hora, remove os dois pontos para comparação numérica
        if (col === 1) {
            valA = valA.replace(":", "");
            valB = valB.replace(":", "");
        }

        if (ordem[col]) {
            return valA > valB ? 1 : valA < valB ? -1 : 0;
        } else {
            return valA < valB ? 1 : valA > valB ? -1 : 0;
        }
    });

    // Atualiza a tabela com as linhas ordenadas
    linhas.forEach(linha => tabela.appendChild(linha));

    // Alterna a ordem para o próximo clique
    ordem[col] = !ordem[col];

    // Atualiza as setas indicadoras de ordenação
    document.getElementById("seta-data").textContent = col === 0 ? (ordem[0] ? "▲" : "▼") : "▲";
    document.getElementById("seta-hora").textContent = col === 1 ? (ordem[1] ? "▲" : "▼") : "▲";
    document.getElementById("seta-paciente").textContent = col === 2 ? (ordem[2] ? "▲" : "▼") : "▲";
    document.getElementById("seta-medico").textContent = col === 3 ? (ordem[3] ? "▲" : "▼") : "▲";
}

function filtrarPacientes() {
    var input = document.getElementById("filtroPaciente");
    var filtro = input.value.toLowerCase();
    var linhas = document.getElementsByClassName("paciente-row");

    for (var i = 0; i < linhas.length; i++) {
        var nome = linhas[i].getElementsByTagName("td")[0].textContent.toLowerCase();
        if (nome.indexOf(filtro) > -1) {
            linhas[i].style.display = "";
        } else {
            linhas[i].style.display = "none";
        }
    }
}