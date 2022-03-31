package simulacao;

import java.util.Random;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Veiculo veiculo1;
    private Veiculo veiculo2;
    private Veiculo veiculo3;
    private Veiculo veiculo4;
    private Cidade cancun;
    private Cidade madagascar;
    private Cidade novaYork;
    private Cidade paris;
    private Cidade portugal;
    private Cidade rioDeJaneiro;
    private Cidade buenosAires;
    private Cidade africa;
    private Obstaculo bancoDeareia;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        veiculo1 = new Veiculo(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Cria um veiculo em uma posicao aleatoria
        veiculo1.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(veiculo1);
        veiculo2 = new Veiculo(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        veiculo2.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        mapa.adicionarItem(veiculo2);
        veiculo3 = new Veiculo(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        veiculo3.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        mapa.adicionarItem(veiculo3);
        veiculo4 = new Veiculo(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        veiculo4.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        mapa.adicionarItem(veiculo4);
        //Adiciona as cidades ao mapa
        cancun = new Cidade(new Localizacao(7,10), "Imagens/cancun.jpg", "Cancun");
        mapa.adicionarCidade(cancun);
        madagascar = new Cidade(new Localizacao(33,24), "Imagens/madagascar.jpg", "Madagascar");
        mapa.adicionarCidade(madagascar);
        novaYork = new Cidade(new Localizacao(4,4), "Imagens/NY.png", "Nova York");
        mapa.adicionarCidade(novaYork);
        paris = new Cidade(new Localizacao(28,2), "Imagens/paris.png", "Paris");
        mapa.adicionarCidade(paris);
        portugal = new Cidade(new Localizacao(25,4), "Imagens/portugal.png", "Lisboa");
        mapa.adicionarCidade(portugal);
        rioDeJaneiro = new Cidade(new Localizacao(12,22), "Imagens/RJ.png", "Rio de Janeiro");
        mapa.adicionarCidade(rioDeJaneiro);
        buenosAires = new Cidade(new Localizacao(8,30), "Imagens/Buenos.png", "Buenos Aires");
        mapa.adicionarCidade(buenosAires);
        africa = new Cidade(new Localizacao(20,15), "Imagens/africa.png", "Africa");
        mapa.adicionarCidade(africa);
        //Adiciona os bancos de areia
        bancoDeareia = new Obstaculo(new Localizacao(20,20), "Imagens/areia.png");
        mapa.adicionarObstaculo(bancoDeareia);
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(100);
        }        
    }

    private void executarUmPasso() {
        mapa.removerItem(veiculo1);
        mapa.removerItem(veiculo2);
        mapa.removerItem(veiculo3);
        mapa.removerItem(veiculo4);
        veiculo1.executarAcao();
        veiculo2.executarAcao();
        veiculo3.executarAcao();
        veiculo4.executarAcao();
        mapa.adicionarItem(veiculo1);
        mapa.adicionarItem(veiculo2);
        mapa.adicionarItem(veiculo3);
        mapa.adicionarItem(veiculo4);
        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
