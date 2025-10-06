function inicializarCalendario(eventos, dataInicial) {
    const calendarEl = document.getElementById('calendario');

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'pt-br',
        height: 500,
        selectable: true,

        headerToolbar: {
            left: 'prev,next hojeCustom',
            center: 'title',
            right: ''
        },

        customButtons: {
            hojeCustom: {
                text: 'Hoje',
                click: function() {
                    window.location.href = '/home'; // Redireciona para a home, que irá carregar a data atual por padrão
                }
            }
        },

        events: eventos,
        // Não precisamos de initialDate aqui, pois o gotoDate abaixo já fará o trabalho
        // initialDate: dataInicial, 

        dayCellDidMount: function(arg) {
            const cellDateString = arg.date.toISOString().split('T')[0];
            // Aplica o destaque ao dia vindo do backend (dataInicial)
            if (dataInicial && cellDateString === dataInicial) {
                arg.el.classList.add('selected-day');
            } else if (!dataInicial && arg.isToday) { // Se não há data inicial e é o dia de hoje, destaca
                arg.el.classList.add('selected-day');
            }
        },

        dateClick: function (info) {
            // A lógica de redirecionamento já cuida da atualização do destaque através do recarregamento da página
            window.location.href = '/home?data=' + info.dateStr;
        }
    });

    // Se uma data específica veio do backend, centraliza o calendário nela.
    // E o dayCellDidMount cuidará de destacar esse dia.
    if (dataInicial) {
        calendar.gotoDate(dataInicial);
    } else {
        // Se não há dataInicial, o calendário deve estar no mês atual.
        // O dayCellDidMount já cuida do destaque do dia atual via `arg.isToday`.
    }
    
    calendar.render();
}