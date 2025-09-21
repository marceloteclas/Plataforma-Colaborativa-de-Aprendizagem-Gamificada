package historico;

public interface Comando {
    void executar();
    void desfazer();
    String descricao();
}
