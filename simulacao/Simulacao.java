//package simulacao;

import java.util.Random;
import java.util.*;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;    
    private ArrayList<Cidade> cidades;
    private ArrayList<Veiculo> veiculos;
    private Obstaculo bancoDeareia;    
    //private Viagem viagem;
    private GerenciadoraViagem gerenciadora;

    public Simulacao() {
        /*
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        */
        Random rand = new Random();
        mapa = new Mapa();

        cidades = new ArrayList<Cidade>();
        veiculos = new ArrayList<Veiculo>();
        gerenciadora = new GerenciadoraViagem();
        int quantidadeVeiculos = 4;


        // ADICIONAR CIDADES E OBSTACULOS
        adicionarCidades();
        adicionarObstaculos();
        
        // ADICIONAR VEICULOS, passa como parametro cidade de origem e destino dese veiculo 
        for(int i = 0; i < quantidadeVeiculos; i++){
            /* AINDA TEM QUE IMPLEMENTAR PRA CIDADE DE DESTINO SER DIFERENTE DA CIDADE DE ORIGEM */
            int indiceOrigem = rand.nextInt(8); //nextInt(8) gera numero aleatorio entre 0 e 7
            int indiceDestino = rand.nextInt(8); 

            Cidade cidadeOrigem = cidades.get(indiceOrigem);
            Cidade cidadeDestino = cidades.get(indiceDestino);
            adicionarVeiculos(cidadeOrigem, cidadeDestino);              
        }

        janelaSimulacao = new JanelaSimulacao(mapa);       

    }
    
    public void executarSimulacao(int numPassos){        
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(100);   //original 100 milisegundos
        }
        gerenciadora.imprimirRelatorio();       
    }

    private void executarUmPasso() {
        for (int i = 0; i < veiculos.size(); i++) {
            mapa.removerItem(veiculos.get(i));       
            veiculos.get(i).executarAcao();
            mapa.adicionarItem(veiculos.get(i));
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
    
    private void adicionarCidades() {   
        //0
        Cidade cancun = new Cidade(new Localizacao(7,10), "Imagens/cancun.jpg", "Cancun");
        mapa.adicionarCidade(cancun);
        cidades.add(cancun);
        //1
        Cidade madagascar = new Cidade(new Localizacao(33,24), "Imagens/madagascar.jpg", "Madagascar");
        mapa.adicionarCidade(madagascar);
        cidades.add(madagascar);
        //2
        Cidade africa = new Cidade(new Localizacao(20,15), "Imagens/africa.png", "Africa");
        mapa.adicionarCidade(africa);
        cidades.add(africa);
        //3
        Cidade novaYork = new Cidade(new Localizacao(4,4), "Imagens/NY.png", "Nova York");
        mapa.adicionarCidade(novaYork);
        cidades.add(novaYork);
        //4
        Cidade paris = new Cidade(new Localizacao(28,2), "Imagens/Paris.png", "Paris");
        mapa.adicionarCidade(paris);
        cidades.add(paris);
        //5
        Cidade portugal = new Cidade(new Localizacao(25,4), "Imagens/portugal.png", "Lisboa");
        mapa.adicionarCidade(portugal);
        cidades.add(portugal);
        //6
        Cidade rioDeJaneiro = new Cidade(new Localizacao(12,22), "Imagens/RJ.png", "Rio de Janeiro");
        mapa.adicionarCidade(rioDeJaneiro);
        cidades.add(rioDeJaneiro);
        //7
        Cidade buenosAires = new Cidade(new Localizacao(8,30), "Imagens/Buenos.png", "Buenos Aires");
        mapa.adicionarCidade(buenosAires);
        cidades.add(buenosAires); 
    }

    private void adicionarObstaculos() {
        bancoDeareia = new Obstaculo(new Localizacao(8, 25), "Imagens/areia2.jpg");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(14, 5), "Imagens/areia2.jpg");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(10, 20), "Imagens/areia2.jpg");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(18, 29), "Imagens/areia2.jpg");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(30, 07), "Imagens/areia2.jpg");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(18, 18), "Imagens/areia2.jpg");
        mapa.adicionarObstaculo(bancoDeareia);
    }

    private void adicionarVeiculos(Cidade cidadeOrigem, Cidade cidadeDestino) {
        Veiculo veiculo;        
        
        veiculo = new Veiculo(new Localizacao(cidadeOrigem.getLocalizacaoAtual().getX() ,cidadeOrigem.getLocalizacaoAtual().getY()));//Cria um veiculo em uma posicao 
        veiculo.setLocalizacaoDestino(new Localizacao(cidadeDestino.getLocalizacaoAtual().getX() ,cidadeDestino.getLocalizacaoAtual().getY()));//Define a posicao destino 
        mapa.adicionarItem(veiculo);//Inicializando o mapa com o veículo
        
        veiculos.add(veiculo); // -- Adiciona um veiculo no arraylist de veiculos

        //criando uma viagem para cada veiculo
        Viagem viagem = new Viagem( veiculo, cidadeOrigem.getNome(), cidadeDestino.getNome() );  
        gerenciadora.adicionarViagem(viagem);
    }

    
}
