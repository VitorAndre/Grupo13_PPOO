public class Viagem {
    private Veiculo veiculo;
    private int numeroDePassageiros; 
    private double valorPassagem;
    private double lucro;
    private String origem;
    private String destino;
    private double distanciaEntreDoisPontos;
    private int distanciaRealPercorrida;

    public Viagem(Veiculo veiculo, String origem, String destino, int numeroDePassageiros, double valorPassagem, int distanciaRealPercorrida){
        this.veiculo = veiculo;
        this.numeroDePassageiros = numeroDePassageiros;
        this.valorPassagem = valorPassagem;
        this.distanciaRealPercorrida = distanciaRealPercorrida;
        this.origem = origem;
        this.destino = destino;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public int getNumeroDePassageiros() {
        return numeroDePassageiros;
    }

    public double getValorPassagem(){
        return valorPassagem;
    }

    public int getDistanciaRealPercorrida() {
        return veiculo.getDistanciaPercorrida() ;
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

    /** 
     *  
     * Cada 16 litros de combustível é capaz de navegar por 0,5 milha, que é equivalente a quase 1 km.
     * 
     */

     public void CalcularLucro(){
        double precoCombustivelPorLitro = 1450;
        double litrosConsumidosPorKm = 20; 
        double custo = precoCombustivelPorLitro * (litrosConsumidosPorKm * getDistanciaRealPercorrida()) ;
        double receita = numeroDePassageiros * valorPassagem;
        lucro = receita - custo;
     }

   
}
