//package simulacao;

import java.awt.Image;
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

    public Veiculo(Localizacao localizacao) {
        distanciaPercorrida = 0;
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
        imagem = new ImageIcon(getClass().getResource("Imagens/veiculo.jpg")).getImage();
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
    
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
            //Incrementa uma unidade na distância total toda vez que o navio se move
            incrementaDistancia();
        }
    } 

}
