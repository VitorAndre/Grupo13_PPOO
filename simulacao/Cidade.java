/**
 * Define uma cidade a ser inserida no mapa
 */
public class Cidade extends Obstaculo {
    private String nome;

    /**
     * Cria uma cidade para os veiculos sairem/chegarem
     * @param localizacao Localizacao da cidade
     * @param nomeImagem Nome da imagem que representa a cidade
     * @param nome Nome da cidade
     */
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