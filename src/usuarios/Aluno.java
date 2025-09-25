package usuarios;

import conquistas_reestruturadas.ConquistaComposite;
import conquistas_reestruturadas.MedalhaComposite;

public class Aluno extends Usuarios {
    private ConquistaComposite minhasConquistas = new ConquistaComposite("Minhas Conquistas", "Conquistas desbloqueadas pelo aluno");
    private MedalhaComposite minhasMedalhas = new MedalhaComposite("Minhas Medalhas", "Medalhas recebidas pelo aluno");
    
    public Aluno(String nome, String senha) {
        super(nome, senha);
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }

    public ConquistaComposite getMinhasConquistas() {
        return minhasConquistas;
    }

    public MedalhaComposite getMinhasMedalhas() {
        return minhasMedalhas;
    }

    public void adicionarConquista(ConquistaComposite conquista) {
        this.minhasConquistas.adicionar(conquista);
    }

    public void adicionarMedalha(MedalhaComposite medalha) {
        this.minhasMedalhas.adicionar(medalha);
    }
}
