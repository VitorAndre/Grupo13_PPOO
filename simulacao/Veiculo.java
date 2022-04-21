import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Veiculo {
    private int distanciaPercorrida;
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    private Image imagem;
    private Mapa mapa;
    private static Random rand = new Random();

    /**
     * Cria um veiculo.
     * @param mapa: Mapa.
     * @param distanciaRealPercorrida: Distancia percorrido pelo veiculo.
     * @param localizacaoAtual: Localizacao atual do veiculo.
     * @param localizacaoDestino: Cidade de destino do veiculo.
     * @param imagem: Imagem que representa o veiculo.
     */
    public Veiculo(Localizacao localizacao, Mapa umMapa) {
        mapa = umMapa;
        distanciaPercorrida = 0;
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
        imagem = new ImageIcon(getClass().getResource("Imagens/veiculo.png")).getImage();
    }
    
    /** 
     * Retorna a localizacao atual do veiculo.
     * @return Localizacao
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }
    
    /** 
     * Retorna a localizacao de destino do veiculo.
     * @return Localizacao
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }  
    
    /** 
     * Retorna a imagem do veiculo.
     * @return Image
     */
    public Image getImagem(){
        return imagem;
    }
    
    /** 
     * Retorna a Distancia percorrida.
     * @return int
     */
    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }
    
    /** 
     * Metodo para alterar a localizacao atual do veiculo.
     * @param localizacaoAtual: Localizacao atual do veiculo.
     */
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }
    
    /**
     * Metodo para alterar a localizacao de destino do veiculo. 
     * @param localizacaoDestino: Localizacao destino do veiculo.
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    /**
     * Metodo para incrementar em uma unidade a distancia percorrida pelo veiculo no mapa.
     */
    public void incrementaDistancia(){
        distanciaPercorrida++;
    }
    
    /**
     * Metodo para mostrar a proxima localizacao do veiculo no mapa.
     * Ele recebe como parametro a localizacao de destino e a partir da sua localizacao atual,
     * decide para qual posicao deve ir no proximo passo.
     * @param localizacaoDestino: localizacao que se deseja alcancar.
     * @return Proxima localizacao do veiculo
     */
    public Localizacao proximaLocalizacao(Localizacao localizacaoDestino){
        if(localizacaoDestino.equals(getLocalizacaoAtual())){//Verifica se já alcancou o destino
            return localizacaoDestino;
        }else{
            int x = getLocalizacaoAtual().getX();
            int y = getLocalizacaoAtual().getY();
            int destX = localizacaoDestino.getX();
            int destY = localizacaoDestino.getY();
            int deslocX = x < destX ? 1 : x > destX ? -1 : 0;//Deslocamento de 1 ou 0 ou -1 posição em x
            int deslocY = y < destY ? 1 : y > destY ? -1 : 0;//Deslocamento de 1 ou 0 ou -1 posição em y
            Localizacao novaLocalizacao;
            if(deslocX != 0 && deslocY != 0){//Se nenhuma coordenada coincide com a localizacao destino
                if(rand.nextInt(2) == 0) {//Atualizar x
                    novaLocalizacao = new Localizacao(x + deslocX, y);
                } else {//Atualizar y
                    novaLocalizacao = new Localizacao(x, y + deslocY);
                }
            } else {
                if(deslocX != 0) novaLocalizacao = new Localizacao(x + deslocX, y);//Se y coincide com a localizacao destino
                else novaLocalizacao = new Localizacao(x, y + deslocY);//Se x coincide com a localizacao destino
            }
            return novaLocalizacao;
        }
    }

    /**
     * Metodo para alterar a localizacao do veiculo no mapa.
     * Ele e responsavel por realizar o tratamento de colisao, caso o veiculo possa
     * se mover sem risco de colidir a localizacao atual eh alterada para a proxima localizacao.
     * Se existir risco de colisao entre veiculos, ele simplesmente espera o outro se mover.
     * Se existir risco de colisao entre cidades e bancos de areia, ele altera a localizacao de modo
     * a evitar a colisao. 
     */
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null  &&  !( destino.equals(getLocalizacaoAtual()) )  ){//Se eu nao estiver na posicao de destino e ela for valida.
            Localizacao proximaLocalizacao = proximaLocalizacao(localizacaoDestino);
            if (mapa.getItem(proximaLocalizacao.getX(), proximaLocalizacao.getY()) == null) {//Se na proxima posicao nao existir nenhum outro veiculo 
                if (mapa.getObstaculo(proximaLocalizacao.getX(), proximaLocalizacao.getY()) == null && 
                    mapa.getCidade(proximaLocalizacao.getX(), proximaLocalizacao.getY()) == null
                ){// Se na proxima posicao nao existir nenhum banco de areia ou ciade
                    setLocalizacaoAtual(proximaLocalizacao);
                    incrementaDistancia();
                } else {
                    //Altera a localização do veiculo no mapa para evitar o conflito
                    proximaLocalizacao = localizacaoDestinoQuandoConflita(this);
                    if (proximaLocalizacao != null) {   
                        setLocalizacaoAtual(proximaLocalizacao);
                        incrementaDistancia();
                    }
                }
            }
        }
    } 
    
    /** 
     * Metodo para retornar a localizacao que o veiculo deve ir quando existir risco de colisao.
     * @param v: Veiculo
     * @return Nova Localizacao
     */
    private Localizacao localizacaoDestinoQuandoConflita(Veiculo v) {
        Localizacao novaLocalizacao;
        if (v.getLocalizacaoAtual().getX() == v.getLocalizacaoDestino().getX()) {//Se o eixo X já estiver na posicao de destino
            if (v.getLocalizacaoAtual().getY() > v.getLocalizacaoDestino().getY()) {//Se a localizacao atual em Y for maior que a de destino
                novaLocalizacao = new Localizacao(v.getLocalizacaoAtual().getX() + 1, v.getLocalizacaoAtual().getY() - 1);
            } else {//Se a localizacao atual em Y for menor que a de destino
                novaLocalizacao = new Localizacao(v.getLocalizacaoAtual().getX() + 1, v.getLocalizacaoAtual().getY() + 1);
            }
        } else {//Se o eixo X nao estiver na posicao de destino
            if (v.getLocalizacaoAtual().getX() > v.getLocalizacaoDestino().getX()) {//Se a localizacao atual em X for maior que a de destino
                novaLocalizacao = new Localizacao(v.getLocalizacaoAtual().getX() - 1, v.getLocalizacaoAtual().getY() + 1);
            } else {//Se a localizacao atual em X for menor que a de destino
                novaLocalizacao = new Localizacao(v.getLocalizacaoAtual().getX() + 1, v.getLocalizacaoAtual().getY() + 1);
            }
        }
        if (mapa.getItem(novaLocalizacao.getX(), novaLocalizacao.getY()) == null) {//Se nao tiver nenhum veiculo na nova localizacao ela e retornada 
            return novaLocalizacao;
        } else {
            return null;
        }
    }

}
