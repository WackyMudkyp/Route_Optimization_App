public class ruta {
    


    private String extremo_a; // almacena el nombre de la parada a la que corresponde
    private String extremo_b; //almacena el nombre del destino

    private float distancia;
    private float feromonas;

    private visual_tag Vtag;

    private input_tag Itag;

    public ruta(String extremo_a, String extremo_b) {
        
        if (extremo_a == extremo_b) {
            System.out.println("ERROR");
            return;
        }
        
        
        this.extremo_a = extremo_a;
        this.extremo_b = extremo_b;
        this.distancia = 10.0f;
        this.feromonas = 0.1f;

        this.Vtag = new visual_tag("");
        //this.Vtag = new visual_tag("C:\\Alex\\Programacion\\Programas\\JAVA\\PizzaDelivery\\Pizzant.png");
        Vtag.setInfo1(getNombre());
        Vtag.setInfo2(getDistancia() + "k " + getFeromonas());

        this.Itag = new input_tag("Ruta: " + getNombre());

    }

    public String getNombre(){
        return extremo_a + " - "+  extremo_b;
    }

    public float getDeseo(){
        return feromonas * (10 / distancia);
    }

    public void setFeromonas(float distanciaHormiga){
        this.feromonas = 1 / distanciaHormiga;
    }

    public void setFeromonas(ruta [] rutaTomada){
        
        float sum = 0f;
        
        for ( ruta k : rutaTomada){
            sum += k.getDistancia();
        }
        
        this.feromonas += 1 / sum;
        updateVtag();
    }

    public void evaporar(){
        this.feromonas = feromonas * 0.5f;
    }
    
    public String getExtremo_a() {
        return extremo_a;
    }

    public String getExtremo_b() {
        return extremo_b;
    }

    public float getDistancia() {
        return distancia;
    }

    public float getFeromonas() {
        return feromonas;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
        updateVtag();
    }

    public visual_tag getVisual_tag(){
        return Vtag;
    }

    public void updateVtag(){
        Vtag.setInfo2("Distancia: " + getDistancia() + "km Feromonas: " + getFeromonas());
    }

    public visual_tag getVtag() {
        return Vtag;
    }

    public input_tag getItag() {
        return Itag;
    }


    public void asignarValor(){
        distancia = Float.parseFloat(getItag().getInfo());
    }
}
