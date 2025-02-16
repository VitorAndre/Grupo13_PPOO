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
    private GerenciadoraViagem gerenciadora;
    private Random rand;

    /**
     * Instancia o mapa, o rand, as cidades, os veiculos, a gerenciadora de viagens, adiciona as cidades,
     * adiciona os obstaculos e instancia a janela de simulacao
     */
    public Simulacao() {
        rand = new Random();
        mapa = new Mapa();

        cidades = new ArrayList<Cidade>();
        veiculos = new ArrayList<Veiculo>();
        gerenciadora = new GerenciadoraViagem();

        // ADICIONAR CIDADES E OBSTACULOS
        adicionarCidades();
        adicionarObstaculos();

        janelaSimulacao = new JanelaSimulacao(mapa);       

    }
    
    /** 
     * Inicia a simulacao
     * @param numPassos Numero maximo de passos realizados em cada iteracao
     * @param quantidadeVeiculos Quantidade de veiculos que andarao no mapa 
     * @param tempoDeEspera Tempo entre cada iteracao
     */
    public void executarSimulacao(int numPassos, int quantidadeVeiculos, int tempoDeEspera){        
        for (int i = 0; i < 10; i++) {
            mapa.resetarItens();
            adicionarVeiculos(quantidadeVeiculos);
            janelaSimulacao.executarAcao();
            for (int j = 0; j < numPassos; j++) {
                executarUmPasso();
                esperar(tempoDeEspera);
            }
        }
        gerenciadora.imprimirRelatorio();
        gerenciadora.mostrarRelatorioJanela();        
    }

    /**
     * Define, para cada veiculo, a proxima localizacao a ser atingida
     */
    private void executarUmPasso() {
        for (int i = 0; i < veiculos.size(); i++) {
            mapa.removerItem(veiculos.get(i));
            veiculos.get(i).executarAcao();
            mapa.adicionarItem(veiculos.get(i));
        }
        janelaSimulacao.executarAcao();
    }
    
    /** 
     * Define o tempo de espera
     * @param milisegundos Tempo em milissegundos que o sistema vai esperar em cada iteracao
     */
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Adiciona as cidades ao mapa
     */
    private void adicionarCidades() {   
        //0
        Cidade cancun = new Cidade(new Localizacao(7,10), "Imagens/cancun.png", "Cancun(México)");
        mapa.adicionarCidade(cancun);
        cidades.add(cancun);
        //1
        Cidade madagascar = new Cidade(new Localizacao(33,24), "Imagens/madagascar.png", "Madagascar");
        mapa.adicionarCidade(madagascar);
        cidades.add(madagascar);
        //2
        Cidade africa = new Cidade(new Localizacao(20,15), "Imagens/africa.png", "Dacar(Senegal)");
        mapa.adicionarCidade(africa);
        cidades.add(africa);
        //3
        Cidade novaYork = new Cidade(new Localizacao(4,4), "Imagens/NY.png", "Nova York(EUA)");
        mapa.adicionarCidade(novaYork);
        cidades.add(novaYork);
        //4
        Cidade paris = new Cidade(new Localizacao(28,2), "Imagens/Paris.png", "Paris(França)");
        mapa.adicionarCidade(paris);
        cidades.add(paris);
        //5
        Cidade portugal = new Cidade(new Localizacao(25,4), "Imagens/portugal.png", "Lisboa(Portugal)");
        mapa.adicionarCidade(portugal);
        cidades.add(portugal);
        //6
        Cidade rioDeJaneiro = new Cidade(new Localizacao(12,22), "Imagens/RJ.png", "Rio de Janeiro(Brasil)");
        mapa.adicionarCidade(rioDeJaneiro);
        cidades.add(rioDeJaneiro);
        //7
        Cidade buenosAires = new Cidade(new Localizacao(8,30), "Imagens/Buenos.png", "Buenos Aires(Argentina)");
        mapa.adicionarCidade(buenosAires);
        cidades.add(buenosAires); 
    }

    /**
     * Adiciona os bancos de areia ao mapa
    */
    private void adicionarObstaculos() {
        bancoDeareia = new Obstaculo(new Localizacao(8, 25), "Imagens/areia2.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(14, 5), "Imagens/areia2.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(10, 20), "Imagens/areia2.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(18, 29), "Imagens/areia2.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(30, 07), "Imagens/areia2.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(18, 18), "Imagens/areia2.png");
        mapa.adicionarObstaculo(bancoDeareia);
    }

    
    /** 
     * Adiciona um veiculo ao mapa e cria a Viagem que ele realizara
     * @param cidadeOrigem Cidade que o navio vai sair, definida de maneira aleatoria
     * @param cidadeDestino Cidade que o navio vai chegar, definida de maneira aleatoria
     */
    private void adicionarVeiculo(Cidade cidadeOrigem, Cidade cidadeDestino) {
        Veiculo veiculo;        
        
        veiculo = new Veiculo(new Localizacao(cidadeOrigem.getLocalizacaoAtual().getX() -1,cidadeOrigem.getLocalizacaoAtual().getY()), mapa);//Cria um veiculo em uma posicao 
        veiculo.setLocalizacaoDestino(new Localizacao(cidadeDestino.getLocalizacaoAtual().getX() -1,cidadeDestino.getLocalizacaoAtual().getY()));//Define a posicao destino 
        mapa.adicionarItem(veiculo);//Inicializando o mapa com o veículo        
        veiculos.add(veiculo); // Adiciona um veiculo no arraylist de veiculos

        int numeroDePassageiros = rand.nextInt(4501) + 500; // É escolhido um número aleatório entre 500 e 5000 para representar a quantidade de passageiros do navio
        double valorPassagem = 80 + (rand.nextDouble() * (480 - 80)); // É escolhido um número aleatório entre 80 e 480 para representar o preço da passagem
        // Criando uma viagem para cada veiculo
        Viagem viagem = new Viagem( veiculo, cidadeOrigem.getNome(), cidadeDestino.getNome(), numeroDePassageiros, valorPassagem );  
        gerenciadora.adicionarViagem(viagem);
    }

    
    /** 
     * Adiciona vários veiculos ao mapa, utilizando uma iteracao que chama a funcao adicionarVeiculo
     * @param quantidadeVeiculos Quantidade total de veiculos que estarao no mapa
     */
    private void adicionarVeiculos(int quantidadeVeiculos) {
        veiculos.clear();
        // ADICIONAR VEICULO, passa como parametro cidade de origem e destino dese veiculo 
        for(int i = 0; i < quantidadeVeiculos; i++){
            int indiceOrigem = rand.nextInt(8); //nextInt(8) gera numero aleatorio entre 0 e 7
            int indiceDestino = rand.nextInt(8); 
            while (indiceDestino == indiceOrigem) { //garante que inicio e destino nao sejam iguais
                indiceDestino = rand.nextInt(8);
            }
            Cidade cidadeOrigem = cidades.get(indiceOrigem);
            Cidade cidadeDestino = cidades.get(indiceDestino);
            adicionarVeiculo(cidadeOrigem, cidadeDestino);              
        }
    }
    
}
