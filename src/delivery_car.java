

public class delivery_car {

    private map Mapa;

    private lugar LugarActual ;

    private ruta[] rutasRecorridas;
    private lugar[] Objetivos; 
    
    private ruta[] mejorRuta;
    private float  mejorDist;


    public delivery_car(map mapa) {
        
        this.Mapa = mapa;
        
        LugarActual = mapa.getInicio();
        rutasRecorridas = new ruta[mapa.getParadas().length];
        
        vaciarRutasRecorridas();
    

        this.mejorDist = 0;
        Objetivos = mapa.getParadas();
    }


    public void recorrerMapa(){

        Objetivos = Mapa.getParadas();
        eliminarObjetivo(Mapa.getInicio());
        
        while (Objetivos.length > 0){
            valorarDesplazamiento();
            
        }
        regresarInicio();
        

        for (ruta k : rutasRecorridas){
            if (k != null){
                //System.out.println("Nombre: " + k.getNombre() + " Feromonas: " + k.getFeromonas());
            }
        }

        aplicarFeromonas();

        for (ruta k : rutasRecorridas){
            if (k != null){
                //System.out.println("Nombre: " + k.getNombre() + " Feromonas: " + k.getFeromonas() + " Dist: " + k.getDistancia());
            }
        }
        
        System.out.println(getDistanciaRecorrida());
        if (getDistanciaRecorrida() >= mejorDist){
            mejorDist = getDistanciaRecorrida();
            mejorRuta = rutasRecorridas;
        }

        String rutaVis = "Rutas Utilizadas: ";
        for (ruta k : mejorRuta){
            rutaVis += k.getNombre()  + " --> ";
        }

        Mapa.getRenderer().actualizarResultados( "Mejor Dist Recorrida: " + getDistanciaRecorrida(), rutaVis);


    }

    private void eliminarObjetivo(lugar Obj){

        lugar[] aux = Objetivos;
        
        //desplaza los objetivos despues de eliminar el deseado
        for (int i = 0 ; i < Objetivos.length ; i++){

            if (Obj == Objetivos[i]){
                aux = new lugar[Objetivos.length - 1];
                //llena aux
                for ( int k = 0 ; k < aux.length ; k++){
                    aux[k] = Objetivos[k];
                }
                //corre los elementos para borrar
                for ( int j = i ; j < aux.length ; j ++){
                    
                    aux[j] = Objetivos[j+1];
                    
                    
                }

            }
        }
        //llena el auxiliar
        //for (int i = 0 ; i < aux.length ; i++){
        //    aux[i] = Objetivos[i];
       // }

        Objetivos = aux;

    }

    public void valorarDesplazamiento(){

        //lugar [] posiblesLugares = Objetivos;

        // evalua las probabilidades de cada ruta 
        float [] probabilidades = new float[ Objetivos.length] ;
       
        for (int i = 0 ; i < probabilidades.length ; i++){
            float probabilidad_aux = LugarActual.getRutas(Objetivos)[i].getDeseo() / LugarActual.getDeseos(Objetivos);
            
            //un monton de couts para debugear
            System.out.println("Lugar: " + LugarActual.getNombre() + " Deseo: " + LugarActual.getRutas(Objetivos)[i].getDeseo());
            System.out.println("Deseos: " + LugarActual.getDeseos(Objetivos)  + " " + LugarActual.getRutas(Objetivos)[i].getNombre());
            System.out.println(LugarActual.getRutas(Objetivos)[i].getDeseo() / LugarActual.getDeseos(Objetivos));
            
            probabilidades[i] = probabilidad_aux;
        }
        
        for (int i = 0 ; i < probabilidades.length - 1 ; i++){
            probabilidades[i + 1] += probabilidades[i];
        }



        for (float i : probabilidades){
           System.out.println("Probabilidad:" + i);
        }
        
        
        //elegir ruta aleatoriamente
        float rand = (float)(Math.random() * 1);            //num aleatorio


        ruta rutaElegida = LugarActual.getRutas(Objetivos)[0];       
        int prob_selec = 0;
        for (int i = 1 ; i < probabilidades.length ; i++){
            if ((rand <= probabilidades[i]) && (rand > probabilidades[i - 1])){
                rutaElegida = LugarActual.getRutas(Objetivos)[i];
                prob_selec = i;
            }
        }

        //mas cosas debug
        System.out.println(rand);
        System.out.println(prob_selec);
        
        
        System.out.println("Desplazado desde: " + LugarActual.getNombre());
        
        
        desplazarse(rutaElegida);
        agregarRutaRecorrida(rutaElegida);
        System.out.println("Hasta: " + LugarActual.getNombre());


    }

    public void desplazarse(ruta sigRuta){

        String sigLugar = null;
        
        //revisa a donde ir

        if (sigRuta.getExtremo_a() == LugarActual.getNombre()){
            sigLugar = sigRuta.getExtremo_b();
        }
        if (sigRuta.getExtremo_b() == LugarActual.getNombre()){
            sigLugar = sigRuta.getExtremo_a();
        }

        //busca ese lugar en el mapa comparando nombres

        for (lugar L : Mapa.getParadas()){
            if (L.getNombre() == sigLugar){
                this.LugarActual = L;
            }
        }


        eliminarObjetivo(LugarActual);


    }

    public void regresarInicio(){
        ruta rutaRegreso = null;
        for ( int i = 0 ; i < LugarActual.getRutas().length ; i++){
            
            
            if (LugarActual.getRutas()[i].getExtremo_a() == Mapa.getInicio().getNombre()){
                rutaRegreso = LugarActual.getRutas()[i];
            }
            if (LugarActual.getRutas()[i].getExtremo_b() == Mapa.getInicio().getNombre()){
                rutaRegreso = LugarActual.getRutas()[i];
            }
        }

        agregarRutaRecorrida(rutaRegreso);

        System.out.println("Desplazado desde: " + LugarActual.getNombre());
        LugarActual = Mapa.getInicio();
        System.out.println("Hasta: " + LugarActual.getNombre());

    }
    private void agregarRutaRecorrida( ruta nuevaRuta){
        for (int k = 0 ; k < rutasRecorridas.length ; k++){
            if (rutasRecorridas[k] == null){
                
                rutasRecorridas[k] = nuevaRuta;
                return;
            }
        }
    }

    private void vaciarRutasRecorridas(){
        for (ruta k : rutasRecorridas){
            k = null;
        }
    }

    private void aplicarFeromonas(){
        for (int i = 0 ; i < rutasRecorridas.length; i++){
            rutasRecorridas[i].setFeromonas(rutasRecorridas);
        }
    }

    private float  getDistanciaRecorrida(){
        float sum = 0;
        for (ruta k : rutasRecorridas){
            System.out.println(sum);
            sum += k.getDistancia();
        }
        
        return sum;
    }


    public map getMapa() {
        return Mapa;
    }


    public lugar getLugarActual() {
        return LugarActual;
    }


    public ruta[] getRutasRecorridas() {
        return rutasRecorridas;
    }


    public lugar[] getObjetivos() {
        return Objetivos;
    }


    public ruta[] getMejorRuta() {
        return mejorRuta;
    }
    

    public float getMejorDist() {
        return mejorDist;
    }
    
}
