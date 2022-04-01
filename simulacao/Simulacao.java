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
        veiculo1 = new Veiculo(new Localizacao(1,10), "Imagens/veiculo.jpg");//Cria um veiculo em uma posicao aleatoria
        veiculo1.setLocalizacaoDestino(new Localizacao(22,10));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(veiculo1);
        veiculo2 = new Veiculo(new Localizacao(20,10), "Imagens/veiculo.jpg");
        veiculo2.setLocalizacaoDestino(new Localizacao(25,15));
        mapa.adicionarItem(veiculo2);
        veiculo3 = new Veiculo(new Localizacao(10,1), "Imagens/veiculo.jpg");
        veiculo3.setLocalizacaoDestino(new Localizacao(8,27));
        mapa.adicionarItem(veiculo3);
        veiculo4 = new Veiculo(new Localizacao(1,30), "Imagens/veiculo.jpg");
        veiculo4.setLocalizacaoDestino(new Localizacao(18,30));
        mapa.adicionarItem(veiculo4);
        //Adiciona as cidades ao mapa
        cancun = new Cidade(new Localizacao(7,10), "Imagens/cancun.jpg", "Cancun");
        mapa.adicionarCidade(cancun);
        madagascar = new Cidade(new Localizacao(33,24), "Imagens/madagascar.jpg", "Madagascar");
        mapa.adicionarCidade(madagascar);
        novaYork = new Cidade(new Localizacao(4,4), "Imagens/NY.png", "Nova York");
        mapa.adicionarCidade(novaYork);
        paris = new Cidade(new Localizacao(28,2), "Imagens/Paris.png", "Paris");
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
        bancoDeareia = new Obstaculo(new Localizacao(15,30), "Imagens/areia.png");
        mapa.adicionarObstaculo(bancoDeareia);
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(300);
        }        
    }

    private void executarUmPasso() {
        
        Localizacao proxLocalizacao = veiculo1.getLocalizacaoAtual().proximaLocalizacao(veiculo1.getLocalizacaoDestino());
        Localizacao novaLocalizacao = null;
        System.out.println(veiculo1.getLocalizacaoAtual());
        if(mapa.getObstaculo(proxLocalizacao.getX(), proxLocalizacao.getY()) == null){
            mapa.removerItem(veiculo1);
            mapa.adicionarItem(veiculo1);
            veiculo1.executarAcao();
            
        } else {
            novaLocalizacao = new Localizacao(veiculo1.getLocalizacaoAtual().getX()+1, veiculo1.getLocalizacaoAtual().getY()+1);
            veiculo1.setLocalizacaoAtual(novaLocalizacao);
            veiculo1.setLocalizacaoAtual(novaLocalizacao);
        }

        System.out.println(veiculo2.getLocalizacaoAtual());
        proxLocalizacao = veiculo2.getLocalizacaoAtual().proximaLocalizacao(veiculo2.getLocalizacaoDestino());        
        if(mapa.getObstaculo(proxLocalizacao.getX(), proxLocalizacao.getY()) == null){
            mapa.removerItem(veiculo2);
            mapa.adicionarItem(veiculo2);
            veiculo2.executarAcao();
            
        } else {
            novaLocalizacao = new Localizacao(veiculo2.getLocalizacaoAtual().getX()+1, veiculo1.getLocalizacaoAtual().getY()-1);
            veiculo2.setLocalizacaoAtual(novaLocalizacao);
            veiculo2.setLocalizacaoAtual(novaLocalizacao);
        }

        System.out.println(veiculo3.getLocalizacaoAtual());
        proxLocalizacao = veiculo3.getLocalizacaoAtual().proximaLocalizacao(veiculo3.getLocalizacaoDestino());
        if(mapa.getObstaculo(proxLocalizacao.getX(), proxLocalizacao.getY()) == null){
            mapa.removerItem(veiculo3);
            mapa.adicionarItem(veiculo3);
            veiculo3.executarAcao();
            
        } else {
            novaLocalizacao = new Localizacao(veiculo3.getLocalizacaoAtual().getX()-1, veiculo3.getLocalizacaoAtual().getY()+1);
            veiculo3.setLocalizacaoAtual(novaLocalizacao);
            veiculo3.setLocalizacaoAtual(novaLocalizacao);
        }

        System.out.println(veiculo4.getLocalizacaoAtual());
        proxLocalizacao = veiculo4.getLocalizacaoAtual().proximaLocalizacao(veiculo4.getLocalizacaoDestino());
        if(mapa.getObstaculo(proxLocalizacao.getX(), proxLocalizacao.getY()) == null){
            mapa.removerItem(veiculo4);
            mapa.adicionarItem(veiculo4);
            veiculo4.executarAcao();
            
        } else {
            novaLocalizacao = new Localizacao(veiculo4.getLocalizacaoAtual().getX(), veiculo4.getLocalizacaoAtual().getY()+1);
            veiculo4.setLocalizacaoAtual(novaLocalizacao);
        }
             
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
