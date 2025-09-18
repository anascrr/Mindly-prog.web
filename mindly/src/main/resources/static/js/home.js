function inicializarCalendario(eventos, dataInicial) {
    const calendarEl = document.getElementById('calendario');

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'pt-br',
        height: 500,
        selectable: true,

        // --- MUDANÇA PRINCIPAL AQUI ---
        // 1. Removemos o botão padrão 'today'.
        // 2. Adicionamos o nosso botão customizado 'hojeCustom'.
        headerToolbar: {
            left: 'prev,next hojeCustom', // Substituímos 'today' por 'hojeCustom'
            center: 'title',
            right: ''
        },

        // --- DEFINIÇÃO DO NOSSO BOTÃO ---
        // Esta é a forma oficial da biblioteca para adicionar botões com ações próprias.
        customButtons: {
            hojeCustom: {
                text: 'Hoje', // O texto que aparecerá no botão
                click: function() {
                    // A única ação deste botão: redirecionar para /home. Sem conflitos.
                    window.location.href = '/home';
                }
            }
        },

        events: eventos,

        // A lógica de destaque do dia selecionado continua a mesma.
        dayCellDidMount: function(arg) {
            const cellDateString = arg.date.toISOString().split('T')[0];
            if (dataInicial && cellDateString === dataInicial) {
                arg.el.classList.add('selected-day');
            }
        },

        dateClick: function (info) {
            window.location.href = '/home?data=' + info.dateStr;
        }
    });

    // Se uma data específica veio do backend, centraliza o calendário nela.
    if (dataInicial) {
        calendar.gotoDate(dataInicial);
    }

    // Desenha o calendário na tela.
    calendar.render();
}