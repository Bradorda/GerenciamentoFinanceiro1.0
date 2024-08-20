document.getElementById('movimentacaoForm').addEventListener('submit', function(event) {
    event.preventDefault();
    addMovimentacao();
});

function addMovimentacao() {
    const categoria = document.getElementById('categoria').value;
    const descricao = document.getElementById('descricao').value;
    const valor = document.getElementById('valor').value;
    const tipo = document.getElementById('tipo').value;
    const data = document.getElementById('data').value;

    const movimentacao = {
        categoria: {
            pk_categoria: parseInt(categoria)
        },
        descricao: descricao,
        valor: parseFloat(valor),
        tipo: tipo,
        data: data
    };

    fetch('http://localhost:8081/movimentacoes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(movimentacao)
    })
    .then(response => {
        if (response.ok) {
            alert('Movimentação adicionada com sucesso!');
            buscarMovimentacoes(); // Atualizar tabela após adicionar
        } else {
            alert('Erro ao adicionar movimentação');
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao adicionar movimentação');
    });
}

function buscarMovimentacoes() {
    const dataInicial = document.getElementById('dataInicio').value;
    const dataFinal = document.getElementById('dataFim').value;

    fetch(`http://localhost:8081/movimentacoes/data?dataInicial=${dataInicial}&dataFinal=${dataFinal}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        const movimentacoes = data.movimentacoes || [];
        const totalReceitas = data.totalReceitas || 0;
        const totalDespesas = data.totalDespesas || 0;
        const diferenca = data.diferenca || 0;

        // Exibir as movimentações na tabela
        const tabela = document.getElementById('tabelaMovimentacoes').getElementsByTagName('tbody')[0];
        tabela.innerHTML = '';

        movimentacoes.forEach(movimentacao => {
            const row = tabela.insertRow();
            row.insertCell(0).textContent = movimentacao.categoria.pk_categoria; // Assumindo que você deseja mostrar o ID da categoria
            row.insertCell(1).textContent = movimentacao.descricao;
            row.insertCell(2).textContent = movimentacao.valor.toFixed(2);
            row.insertCell(3).textContent = movimentacao.tipo;
            row.insertCell(4).textContent = movimentacao.data;
        });

        // Exibir os totais de receitas, despesas e a diferença
        document.getElementById('totalReceitas').textContent = `Total de Receitas: R$ ${totalReceitas.toFixed(2)}`;
        document.getElementById('totalDespesas').textContent = `Total de Despesas: R$ ${totalDespesas.toFixed(2)}`;
        document.getElementById('diferenca').textContent = `Diferença: R$ ${diferenca.toFixed(2)}`;
    })
    .catch(error => {
        console.error('Erro ao buscar movimentações:', error);
        alert('Erro ao buscar movimentações');
    });
}
