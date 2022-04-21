/**
 * Representa um mapa com todos os itens que participam da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {
    private Veiculo[][] itens;
    private Cidade[][] cidades;
    private Obstaculo[][] obstaculos;
    private int largura;
    private int altura;
    
    private static final int LARGURA_PADRAO = 35;
    private static final int ALTURA_PADRAO = 35;
    
    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da area de simulação.
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Veiculo[altura][largura];
        cidades = new Cidade[altura][largura];
        obstaculos = new Obstaculo[altura][largura];
    }
    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }

    
    /**
     * Adiciona uma cidade ao mapa
     * @param c Cidade a ser adicionada ao mapa
     */
    public void adicionarCidade(Cidade c){
        cidades[c.getLocalizacaoAtual().getX()][c.getLocalizacaoAtual().getY()] = c;
    }

    
    /**
     * Adiciona um obstaculo ao mapa
     * @param o Obstaculo a ser adicionado ao mapa
     */
    public void adicionarObstaculo(Obstaculo o){
        obstaculos[o.getLocalizacaoAtual().getX()][o.getLocalizacaoAtual().getY()] = o;
    }
    
    
    /**
     * Adiciona um veiculo ao mapa
     * @param v Veiculo a ser adicionado  ao mapa
     */
    public void adicionarItem(Veiculo v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = v;
    }
    
    
    /** 
     * Remove um veiculo do mapa
     * @param v Veiculo a ser removido do mapa
     */
    public void removerItem(Veiculo v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = null;
    }
    
    /**
     * Apaga todos os veiculos que estao inseridos no mapa
     */
    public void resetarItens() {
        itens = new Veiculo[altura][largura];
    }
        
    /** 
     * Obtem um veiculo do mapa
     * @param x Coordenada x a ser buscada
     * @param y Coordenada y a ser buscada
     * @return Veiculo
     */
    public Veiculo getItem(int x, int y){
        return itens[x][y];
    }

    
    /** 
     * Obtem uma cidade do mapa
     * @param x Coordenada x a ser buscada
     * @param y Coordenada y a ser buscada
     * @return Cidade
     */
    public Cidade getCidade(int x, int y){
        return cidades[x][y];
    }

    
    /** 
     * Obtem um obstaculo do mapa
     * @param x Coordenada x a ser buscada
     * @param y Coordenada y a ser buscada
     * @return Obstaculo
     */
    public Obstaculo getObstaculo(int x, int y){
        return obstaculos[x][y];
    }

    
    /** 
     * Retorna a largura do mapa
     * @return int Largura do mapa
     */
    public int getLargura() {
        return largura;
    }

    
    /** 
     * Retorna a altura do mapa
     * @return int Altura do mapa
     */
    public int getAltura() {
        return altura;
    }

}