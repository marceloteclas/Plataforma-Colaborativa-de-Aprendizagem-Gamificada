package conquistas;

public class MainTest {
    public static void main(String[] args) {
        Medalha medalha = new Medalha("Primeiro Desafio", "Completou o primeiro desafio!");
        Nivel nivel = new Nivel(1, "Iniciante");

        // Composite
        ConquistaGrupo grupo = new ConquistaGrupo("Conquistas Iniciais", "Conquistas básicas");
        grupo.adicionar(medalha);
        grupo.adicionar(nivel);

        // Decorator
        Conquista medalhaEspecial = new ConquistaBrilhante(medalha);

        System.out.println(medalha);
        System.out.println(nivel);
        System.out.println(grupo);
        System.out.println(medalhaEspecial);
    }
}
