import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Define um obstaculo que os navios terao que desviar
 */
public class Obstaculo {
    private Localizacao localizacaoAtual;
    private Image imagem;

    public Obstaculo(Localizacao localizacao, String nomeImagem) {
        this.localizacaoAtual = localizacao;
        imagem = new ImageIcon(getClass().getResource(nomeImagem)).getImage();
    }

    
    /**
     * Retorna a localizacao atual do obstaculo
     * @return Localizacao
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    
    /** 
     * Define a localizacao atual do obstaculo
     * @param localizacaoAtual
     */
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    
    /** 
     * Retorna a imagem do obstaculo
     * @return Image
     */
    public Image getImagem(){
        return imagem;
    }
    
}
