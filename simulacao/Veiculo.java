//package simulacao;

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

    public Veiculo(Localizacao localizacao, Mapa umMapa) {
        mapa = umMapa;
        distanciaPercorrida = 0;
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
        imagem = new ImageIcon(getClass().getResource("Imagens/veiculo.png")).getImage();
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    public Image getImagem(){
        return imagem;
    }

    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public void incrementaDistancia(){
        distanciaPercorrida++;
    }
    
    /**
     * Gera a localizacao para se mover visando alcançar o destino
     * @param localizacaoDestino: localizacao que se deseja alcancar.
     * @return Localizacao para onde se deve ir
     */
    public Localizacao proximaLocalizacao(Localizacao localizacaoDestino){
        if(localizacaoDestino.equals(this)){//Verifica se já alcancou o destino
            // tempoParado = tempoParado + 1;
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
                if(rand.nextInt(2) == 0){//Atualizar x
                    novaLocalizacao = new Localizacao(x + deslocX, y);
                }else{//Atualizar y
                    novaLocalizacao = new Localizacao(x, y + deslocY);
                }
            }else{
                if(deslocX != 0) novaLocalizacao = new Localizacao(x + deslocX, y);
                else novaLocalizacao = new Localizacao(x, y + deslocY);
            }
            return novaLocalizacao;
        }
    }

    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        // mapa.removerItem(this);
        if(destino != null  &&  !( destino.equals(getLocalizacaoAtual()) )  ){
            Localizacao proximaLocalizacao = proximaLocalizacao(localizacaoDestino);
            if (mapa.getItem(proximaLocalizacao.getX(), proximaLocalizacao.getY()) == null) {   
                if (mapa.getObstaculo(proximaLocalizacao.getX(), proximaLocalizacao.getY()) == null && 
                    mapa.getCidade(proximaLocalizacao.getX(), proximaLocalizacao.getY()) == null){

                    setLocalizacaoAtual(proximaLocalizacao);
                    //Incrementa uma unidade na distância total toda vez que o navio se move
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
    private Localizacao localizacaoDestinoQuandoConflita(Veiculo v) {
        Localizacao novaLocalizacao;
        if (v.getLocalizacaoAtual().getX() == v.getLocalizacaoDestino().getX()) {
            if (v.getLocalizacaoAtual().getY() > v.getLocalizacaoDestino().getY()) {
                novaLocalizacao = new Localizacao(v.getLocalizacaoAtual().getX() + 1, v.getLocalizacaoAtual().getY() - 1);
            } else {
                novaLocalizacao = new Localizacao(v.getLocalizacaoAtual().getX() + 1, v.getLocalizacaoAtual().getY() + 1);
            }
        } else {
            if (v.getLocalizacaoAtual().getX() > v.getLocalizacaoDestino().getX()) {
                novaLocalizacao = new Localizacao(v.getLocalizacaoAtual().getX() - 1, v.getLocalizacaoAtual().getY() + 1);
            } else {
                novaLocalizacao = new Localizacao(v.getLocalizacaoAtual().getX() + 1, v.getLocalizacaoAtual().getY() + 1);
            }
        }
        if (mapa.getItem(novaLocalizacao.getX(), novaLocalizacao.getY()) == null) {
            return novaLocalizacao;
        } else {
            return null;
        }
    }

}
