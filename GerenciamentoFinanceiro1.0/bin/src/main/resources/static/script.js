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
    .then(response => response.json())
    .then(data => {
        alert('Movimentação adicionada com sucesso: ' + JSON.stringify(data));
    })
    .catch(error => {
        console.error('Erro ao adicionar movimentação:', error);
    });
}

/*function getMovimentacao() {
    const id = document.getElementById('movimentacaoId').value;

    fetch(`http://localhost:8081/movimentacoes/${id}`)
    .then(response => response.json())
    .then(data => {
        const resultadoDiv = document.getElementById('resultado');
        resultadoDiv.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
    })
    .catch(error => {
        console.error('Erro ao buscar movimentação:', error);
    });
}*/

function getMovimentacaoPorData() {
    const data = document.getElementById('dataMovimentacao').value;

    fetch(`http://localhost:8081/movimentacoes/data?data=${data}`)
    .then(response => response.json())
    .then(data => {
        const resultadoDiv = document.getElementById('resultado');
        resultadoDiv.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
    })
    .catch(error => {
        console.error('Erro ao buscar movimentação por data:', error);
    });
}

