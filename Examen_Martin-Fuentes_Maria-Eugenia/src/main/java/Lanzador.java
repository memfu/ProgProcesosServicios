public class Lanzador {
    public static void main(String[] args) {
        BufferColor generador = new BufferColor();

        new ProductorColor(generador);
        new ConsumidorColor(generador);
        new ProductorColor(generador);
        new ConsumidorColor(generador);
        new ProductorColor(generador);
    }
}
