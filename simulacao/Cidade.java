//Representa as cidades da simulacao.

public class Cidade extends Obstaculo {
    private String nome;

    public Cidade(Localizacao localizacao, String nomeImagem, String nome) {
        super(localizacao, nomeImagem);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
