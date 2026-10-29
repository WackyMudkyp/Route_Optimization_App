

public class lugar {
    


    private String nombre;

    //contiene todas las rutas posibles a partir del lugar 
    private ruta[] Rutas;
    
    private visual_tag Vtag;

    public lugar(){
        this.nombre = "nombre";
    }


    public lugar(String nombre , int CantRutas) {
        this.nombre = nombre;
        Rutas = new ruta[CantRutas];
        
        if (getNombre() == "Local"){
            this.Vtag = new visual_tag("C:\\Alex\\Programacion\\Programas\\JAVA\\PizzaDelivery\\LAPASTAAA.png");
        }
        else{
            this.Vtag = new visual_tag("C:\\Alex\\Programacion\\Programas\\JAVA\\PizzaDelivery\\LilHouse.png");
        }

        
        Vtag.setInfo1(getNombre());
        
       // System.out.println(getRutas().length);
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setRutas(ruta[] rutas) {
        Rutas = rutas;
    }


    public String getNombre() {
        return nombre;
    }


    public ruta[] getRutas() {
        return Rutas;
    }
//devuelve solo las rutas que debe recorrer
    public ruta[] getRutas( lugar[] objetivos ) {
        
        ruta [] aux = new ruta[objetivos.length];
        //retorna solo las rutas que llevan a objetivos
        for (int i = 0 ; i < objetivos.length ; i ++){
            for (int j = 0 ; j < objetivos[i].getRutas().length ; j++){
                if (objetivos[i].getRutas()[j].getExtremo_a() == this.nombre){
                    aux[i] = objetivos[i].getRutas()[j];
                    
                }
                if (objetivos[i].getRutas()[j].getExtremo_b() == this.nombre){
                    aux[i] = objetivos[i].getRutas()[j];  
                    
                }
                
            }
        }


        return aux;
    }

    public float getDeseos(){
        float sum = 0f;
        for(ruta i : Rutas){
            sum += i.getDeseo();
        }
        return sum;
    }
    public float getDeseos( lugar[] objetivos ){
        float sum = 0f;
        
        ruta [] aux = new ruta[objetivos.length];
        //retorna solo las rutas que llevan a objetivos
        for (int i = 0 ; i < objetivos.length ; i ++){
            for (int j = 0 ; j < objetivos[i].getRutas().length ; j++){
                if (objetivos[i].getRutas()[j].getExtremo_a() == this.nombre){
                    aux[i] = objetivos[i].getRutas()[j];
                }
                if (objetivos[i].getRutas()[j].getExtremo_b() == this.nombre){
                    aux[i] = objetivos[i].getRutas()[j];  
                }
                
            }
        }

        for(ruta i : aux){
            sum += i.getDeseo();
        }
        return sum;
    }
    
    public visual_tag getVisual_tag(){
        return Vtag;
    }

}
