import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstaculo {
    private Localizacao localizacaoAtual;
    private Image imagem;

    public Obstaculo(Localizacao localizacao, String nomeImagem) {
        this.localizacaoAtual = localizacao;
        imagem = new ImageIcon(getClass().getResource(nomeImagem)).getImage();
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public Image getImagem(){
        return imagem;
    }
}
