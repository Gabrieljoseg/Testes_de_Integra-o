package controleestoque;

import java.util.HashMap;
import java.util.Map;

public class ControleEstoque {
    private Map<String, Integer> estoque = new HashMap<>();

    public void adicionarProduto(String nome, int quantidade) {
        estoque.put(nome, quantidade);
    }

    public void removerProduto(String nome) {
        estoque.remove(nome);
    }

    public void atualizarQuantidade(String nome, int novaQuantidade) {
        estoque.put(nome, novaQuantidade);
    }

    public int verificarSaldo(String nome) {
        return estoque.getOrDefault(nome, 0);
    }
}
