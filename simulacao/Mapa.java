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
     * @param altura: altura da área de simulação.
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

    public void adicionarCidade(Cidade c){
        cidades[c.getLocalizacaoAtual().getX()][c.getLocalizacaoAtual().getY()] = c;
    }

    public void adicionarObstaculo(Obstaculo o){
        obstaculos[o.getLocalizacaoAtual().getX()][o.getLocalizacaoAtual().getY()] = o;
    }
    
    public void adicionarItem(Veiculo v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = v;
    }
    
    public void removerItem(Veiculo v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = null;
    }
    
    public void resetarItens() {
        itens = null;
        itens = new Veiculo[altura][largura];
    }

    public void atualizarMapa(Veiculo v, Cidade c, Obstaculo o){
        removerItem(v);
        adicionarItem(v);
        adicionarCidade(c);  
        adicionarObstaculo(o);      
    }
    
    public Veiculo getItem(int x, int y){
        return itens[x][y];
    }

    public Cidade getCidade(int x, int y){
        return cidades[x][y];
    }

    public Obstaculo getObstaculo(int x, int y){
        return obstaculos[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public Cidade[][] getCidades() {
        return cidades;
    }

}
