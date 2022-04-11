public class Viagem {
    private Veiculo veiculo;
    private int numeroDePassageiros; 
    private double valorPassagem;
    private double lucro;

    private String origem;
    private String destino;

    //Em qual momento vai ser decidido o numero de passageiros? passado por parametro no construtor, ou
    // decidido um numero aleatorio dentro do construtor?

    private double distanciaEntreDoisPontos;
    private int distanciaRealPercorrida;

    public Viagem(Veiculo veiculo, String origem, String destino){
        this.veiculo = veiculo;
        this.numeroDePassageiros = 100;
        this.valorPassagem = 100;

        this.origem = origem;
        this.destino = destino;
    }


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public int getNumeroDePassageiros() {
        return numeroDePassageiros;
    }

    public int getDistanciaRealPercorrida() {
        return distanciaRealPercorrida;
    }

    public double getDistanciaEntreDoisPontos(){
        return distanciaEntreDoisPontos;
    }

    public double getLucro(){
        return lucro;
    }

    public String getOrigem(){
        return origem;
    }
    public String getDestino(){
        return destino;
    }

    public void calcularDistanciaEntreDoisPontos(int x1,int y1){
        int x2 = veiculo.getLocalizacaoDestino().getX();
        int y2= veiculo.getLocalizacaoDestino().getY();

        distanciaEntreDoisPontos = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // Diminuir 2 para não contar os blocos de origem e destino
    public void calcularDistanciaRealPercorrida(){
        int numPassos = 100;      //// toda vez que mudar aqui tenho que mudar lá
        distanciaRealPercorrida = numPassos - veiculo.getLocalizacaoDestino().tempoParado ;
    }

    /** 
     *  
     * Cada litro de combustível é capaz de navegar por 0,5 milha, que é equivalente a quase 1 km.
     * 
     */

     public void CalcularLucro(){
        double precoCombustivelPorLitro = 100;
        double litrosConsumidosPorKm = 1; 

        double custo = precoCombustivelPorLitro * (litrosConsumidosPorKm * distanciaRealPercorrida) ;
        double receita = numeroDePassageiros * valorPassagem;

        lucro = receita - custo;

     }

   
}
