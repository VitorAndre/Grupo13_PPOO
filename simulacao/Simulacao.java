import java.util.ArrayList;
import java.util.Random;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private ArrayList<Veiculo> veiculos;
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
    private int alturas[];
    private int larguras[];

    public Simulacao() {
        mapa = new Mapa();
        // int largura = mapa.getLargura();
        // int altura = mapa.getAltura();
        alturas = new int[8];
        larguras = new int[8];
        veiculos  = new ArrayList<>();
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public void executarSimulacao(int numPassos, int numVeiculos, int numIteracoes){
        adicionarCidades();
        adicionarObstaculos();
        for (int i = 0; i < numIteracoes; i++) {
            mapa.resetarItens();
            adicionarVeiculos(numVeiculos);
            janelaSimulacao.executarAcao();
            for (int j = 0; j < numPassos; j++) {
                executarUmPasso();
                esperar(100); //todo: ver esse tempo
                if (todosChegaramAoDestino()) { //Caso todos os veiculos acabem seus caminhos, uma nova iteração pode se iniciar
                    esperar(100);
                    j = numPassos;
                }
            }
        }
        //Aqui abriria uma janela com os resultados //todo: ver de fazer isso qnd fechar     
        System.out.println("cabo");       
    }

    private boolean todosChegaramAoDestino() {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getLocalizacaoDestino() != veiculo.getLocalizacaoAtual()) {
                return false;
            }
        }
        return true;
    }

    private void adicionarVeiculos(int qtdVeiculos) {
        Veiculo veiculo;
        veiculos.clear();
        for (int i = 0; i < qtdVeiculos; i++) {
            Random rand = new Random();
            int inicio = rand.nextInt(8);
            //O larguras[r] -1 representa que os navios vão ficar acima da cidade
            veiculo = new Veiculo(new Localizacao(alturas[inicio], larguras[inicio] - 1), "Imagens/veiculo.jpg");//Cria um veiculo em uma cidade aleatoria
            int destino = rand.nextInt(8);
            while (destino == inicio) { //garante que inicio e destino não sejam iguais
                destino = rand.nextInt(8);
            }
            veiculo.setLocalizacaoDestino(new Localizacao(alturas[destino], larguras[destino] - 1));//Define a cidade destino aleatoriamente
            veiculos.add(veiculo);
            mapa.adicionarItem(veiculo);
        }
    }

    private void adicionarCidades() {
        //Adiciona as cidades ao mapa
        cancun = new Cidade(new Localizacao(7,10), "Imagens/cancun.jpg", "Cancun");
        alturas[0] = 7;
        larguras[0] = 10;
        mapa.adicionarCidade(cancun);
        
        madagascar = new Cidade(new Localizacao(33,24), "Imagens/madagascar.jpg", "Madagascar");
        alturas[1] = 33;
        larguras[1] = 24;
        mapa.adicionarCidade(madagascar);
        
        africa = new Cidade(new Localizacao(20,15), "Imagens/africa.png", "Africa");
        alturas[2] = 20;
        larguras[2] = 15;
        mapa.adicionarCidade(africa);

        novaYork = new Cidade(new Localizacao(4,4), "Imagens/NY.png", "Nova York");
        alturas[3] = 4;
        larguras[3] = 4;
        mapa.adicionarCidade(novaYork);
        
        paris = new Cidade(new Localizacao(28,2), "Imagens/Paris.png", "Paris");
        alturas[4] = 28;
        larguras[4] = 2;
        mapa.adicionarCidade(paris);
        
        portugal = new Cidade(new Localizacao(25,4), "Imagens/portugal.png", "Lisboa");
        alturas[5] = 25;
        larguras[5] = 4;
        mapa.adicionarCidade(portugal);
        
        rioDeJaneiro = new Cidade(new Localizacao(12,22), "Imagens/RJ.png", "Rio de Janeiro");
        alturas[6] = 12;
        larguras[6] = 22;
        mapa.adicionarCidade(rioDeJaneiro);
        
        buenosAires = new Cidade(new Localizacao(8,30), "Imagens/Buenos.png", "Buenos Aires");
        alturas[7] = 8;
        larguras[7] = 30;
        mapa.adicionarCidade(buenosAires);
    }

    private void adicionarObstaculos() {
        bancoDeareia = new Obstaculo(new Localizacao(8, 25), "Imagens/areia.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(14, 5), "Imagens/areia.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(10, 20), "Imagens/areia.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(18, 29), "Imagens/areia.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(30, 07), "Imagens/areia.png");
        mapa.adicionarObstaculo(bancoDeareia);

        bancoDeareia = new Obstaculo(new Localizacao(18, 18), "Imagens/areia.png");
        mapa.adicionarObstaculo(bancoDeareia);
    }


    private void executarUmPasso() {
        Localizacao proxLocalizacao;
        ArrayList<Localizacao> destinos = new ArrayList<>();
        for (int i = 0; i < veiculos.size(); i++) {
            proxLocalizacao = veiculos.get(i).getLocalizacaoAtual().proximaLocalizacao(veiculos.get(i).getLocalizacaoDestino());        
            
            //verifica se não há um navio indo para o mesmo local
            for (Localizacao l : destinos) {
                if (proxLocalizacao.equals(l) && !veiculos.get(i).getLocalizacaoDestino().equals(l)) {
                    proxLocalizacao = localizacaoDestinoQuandoConflita(veiculos.get(i));
                    mapa.removerItem(veiculos.get(i));
                    veiculos.get(i).setLocalizacaoAtual(proxLocalizacao);
                    mapa.adicionarItem(veiculos.get(i));
                }
            }
            destinos.add(veiculos.get(i).getLocalizacaoAtual());
            destinos.add(proxLocalizacao);
            if (mapa.getObstaculo(proxLocalizacao.getX(), proxLocalizacao.getY()) == null &&
                mapa.getCidade(proxLocalizacao.getX(), proxLocalizacao.getY()) == null) {
                mapa.removerItem(veiculos.get(i));
                mapa.adicionarItem(veiculos.get(i));
                veiculos.get(i).executarAcao();
            } else {
                proxLocalizacao = localizacaoDestinoQuandoConflita(veiculos.get(i));
                mapa.removerItem(veiculos.get(i));
                mapa.adicionarItem(veiculos.get(i));
                veiculos.get(i).setLocalizacaoAtual(proxLocalizacao);
            }
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
    
    private Localizacao localizacaoDestinoQuandoConflita(Veiculo v) {
        if (v.getLocalizacaoAtual().getX() == v.getLocalizacaoDestino().getX()) {
            if (v.getLocalizacaoAtual().getY() > v.getLocalizacaoDestino().getY()) {
                return new Localizacao(v.getLocalizacaoAtual().getX() + 1, v.getLocalizacaoAtual().getY() - 1);
            } else {
                return new Localizacao(v.getLocalizacaoAtual().getX() + 1, v.getLocalizacaoAtual().getY() + 1);
            }
        } else {
            if (v.getLocalizacaoAtual().getX() > v.getLocalizacaoDestino().getX()) {
                return new Localizacao(v.getLocalizacaoAtual().getX() - 1, v.getLocalizacaoAtual().getY() + 1);
            } else {
                return new Localizacao(v.getLocalizacaoAtual().getX() + 1, v.getLocalizacaoAtual().getY() + 1);
            }
        }
    }
}
