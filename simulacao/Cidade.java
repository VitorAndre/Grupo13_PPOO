/**
 * Define uma cidade a ser inserida no mapa
 */

public class Cidade extends Obstaculo {
    private String nome;

    public Cidade(Localizacao localizacao, String nomeImagem, String nome) {
        super(localizacao, nomeImagem);
        this.nome = nome;
    }

    
    /**
     * Retorna o nome da cidade
     * @return String
     */
    public String getNome() {
        return nome;
    }
}