package simulacao;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Cidade {
    private Localizacao localizacaoAtual;
    private Image imagem;

    public Cidade(Localizacao localizacao) {
        this.localizacaoAtual = localizacao;
        imagem = new ImageIcon(getClass().getResource("Imagens/veiculo.jpg")).getImage();
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Image getImagem(){
        return imagem;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }
}
