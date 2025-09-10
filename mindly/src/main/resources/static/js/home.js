// Função para inicializar o calendário com os dados recebidos do HTML
function inicializarCalendario(eventos, dataInicial) {
    const calendarEl = document.getElementById('calendario');

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'pt-br',
        height: 500,
        selectable: true,
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: ''
        },
        buttonText: {
            today: 'Hoje'
        },
        events: eventos, // Usa os eventos passados como parâmetro
        dateClick: function (info) {
            // Redireciona para a página principal com a data selecionada como parâmetro
            window.location.href = '/?data=' + info.dateStr;
        },
        eventClick: function (info) {
            // Opcional: ação ao clicar em um evento
            // Ex: alert('Paciente: ' + info.event.title);
        },
        validRange: {
            start: null,
            end: null
        }
    });

    // Se uma data inicial foi fornecida, navega o calendário para essa data
    if (dataInicial) {
        calendar.gotoDate(dataInicial);
    }

    calendar.render();
}