
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class map {
    
    private lugar[] Paradas; // almacena los lugares donde puede colocarse la hormiga
    private ruta[] Rutas; // almacena TODAS las rutas


    private renderer Renderer;

    private input_tag TotalLocation;
    private JButton ReloadButton;
    private input_tag AntAmmountInput;
    private input_tag Rounds;
    private JButton ExecuteButton;

    public map(int cantidad , renderer Render) {

        this.Renderer = Render;
        this.Rutas = new ruta[10];
        AntAmmountInput = new input_tag("Cantidad de Hormigas: ");
        Rounds = new input_tag("Cantidad de Rondas: ");
        inicializarMapa(cantidad);
        inicializarBotones();
        
        //debug output
        viewOutput();
        

    }


    private void inicializarBotones(){
        TotalLocation = new input_tag("Cantidad de Localizaciones: ");
        Renderer.agregarITag(TotalLocation);
        TotalLocation.setLocation(20, 0 );

        ReloadButton = new JButton();
        Renderer.getInputPanel().add(ReloadButton);
        ReloadButton.setText("Recargar");
        ReloadButton.setBackground(Color.ORANGE);
        ReloadButton.setForeground(Color.BLACK);
        ReloadButton.setBounds(200, 15, 100, 30);
        
        ExecuteButton = new JButton();
        Renderer.getInputPanel().add(ExecuteButton);
        ExecuteButton.setText("Ejecutar");
        ExecuteButton.setBackground(Color.ORANGE);
        ExecuteButton.setForeground(Color.BLACK);
        ExecuteButton.setBounds(1200, 600, 100, 30);
                
        ActionListener ReloadListener = new ActionListener(){
            @Override

            public void actionPerformed(ActionEvent ae){
                String text = TotalLocation.getText_tag().getText();
                
                try{
                    int valor = Integer.parseInt(text);
                    
                    if (valor <= 3 ){
                         JOptionPane.showMessageDialog(null, "Ingrese un valor valido ( entero mayor que 3) \n Solo existe un camino posible para llegar a 3 lugares distintos");
                         return;
                    }
                    if (valor >= 10 ){
                         JOptionPane.showMessageDialog(null, "El espacio de renderizado puede no ser suficiente ");

                    }
                    
                    inicializarMapa(valor);

                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Buen intento, ingresa un valor válido");
                }  
            };
        };
        ReloadButton.addActionListener(ReloadListener);
    }

    private void inicializarMapa(int cantidad){


        //inicia paradas
        Paradas = new lugar[cantidad];
        //llena las paradas de lugares
        Paradas[0] = new lugar("Local",Paradas.length - 1);
        for (int i = 1 ; i < Paradas.length ; i++){
            Paradas[i] = new lugar("Cliente " + i , Paradas.length - 1);
        }

        //inicializa rutas
        if (Rutas.length > 0){
            for (int x = 0 ; x < Rutas.length ; x++){
                try {
                    Rutas[x].getItag().setLocation(-100, -100);
                } catch (Exception e) {
                }
                
                
            }
        }
        
        Rutas = new ruta[ CalcCantRutas(Paradas.length) ];
        
        System.out.println(Rutas.length);


        //crea todas las rutas posibles
        for (int i = 0 ; i < Paradas.length; i++){
            
            
            for (int j = 1 ; i + j < Paradas.length; j++){

                ruta aux = new ruta(Paradas[i].getNombre(),Paradas[i+j].getNombre());

                for (int k = 0 ; k < Rutas.length; k++){
                    
                    if (Rutas[k] == null){
                        Rutas[k] = aux;

                        Renderer.agregarITag(Rutas[k].getItag());
                        break;
                    }

                }

            }

        }

        //display input tags





        distribuirInputs();

        




        
        

        //display localizaciones

        int ori_x = Renderer.getWidth()/2;
        int ori_y = Renderer.getHeight()/4;

        Renderer.agregarVTag(getParadas()[0].getVisual_tag());
        
        int iter_len = 80;
        int iter_an = -1;
        int iter_dir = 1; 

        getParadas()[0].getVisual_tag().setLocation(ori_x, ori_y);
        getParadas()[0].getVisual_tag().setInfo2("");
        for ( int i = 1 ; i < getParadas().length ; i++){
            Renderer.agregarVTag(getParadas()[i].getVisual_tag());
            getParadas()[i].getVisual_tag().setLocation(-100 , -100);
            getParadas()[i].getVisual_tag().setLocation(ori_x + (iter_len * iter_dir ),ori_y + (iter_len * iter_an));
            getParadas()[i].getVisual_tag().setInfo2("");
                                        
            iter_an +=1;
            if (i%3 == 0){
                iter_an = -1;
            }
            if (i%3 == 0){
                iter_dir *= -1;
            }
            if (i%6 == 0){
                iter_len += 80;
            }
        }


        //display rutas
        int iter = 0;
        for (ruta k : Rutas){
            System.out.println(k.getNombre());
            Renderer.agregarVTag(k.getVisual_tag());
            
            //k.getVisual_tag().setLocation(5 + iter * 150, (int)k.getVisual_tag().getLocationY() );
            k.getVisual_tag().setLocation((int)k.getVisual_tag().getLocationY(),5 + iter * 70);
            iter += 1;

        }





        //llenar las paradas con las rutas que le corresponden

        for (int p = 0 ; p < Paradas.length; p++){
            ruta[] RAux = new ruta[Paradas.length -1];
            int iterador = 0;
            for (int k = 0 ; k < Rutas.length; k++){
                
                if (Rutas[k].getExtremo_a() == Paradas[p].getNombre()){
                    RAux[iterador] = Rutas[k];
                    iterador++;
                }
                if (Rutas[k].getExtremo_b() == Paradas[p].getNombre()){
                    RAux[iterador] = Rutas[k];
                    iterador++;
                }
           
            }

            
            Paradas[p].setRutas(RAux);
        }


    
    }

    private void distribuirInputs(){
        
        
        int iterx = 0;
        int itery = 0;
        int lim = Paradas.length - 1;
        
        
        Renderer.agregarITag(AntAmmountInput);
        AntAmmountInput.setLocation(20, (lim + 1) * 60 );
        
        
        Renderer.agregarITag(Rounds);
        Rounds.setLocation(20, (lim + 2) * 60 );
        
        for (int k =  0 ; k < Rutas.length ; k++){
            
            Rutas[k].getItag().setLocation(30 + 180 * iterx, 60 * itery + 55);
            
            if (itery < lim){
                itery++;
            }
            if (itery == lim){
                itery = 0;
                iterx +=1;
                lim -=1;
            } 

            
        }
    }

    //calcula la cantidad de rutas que se forman
    private int CalcCantRutas(int num){

        int sum = 0;

        if (num ==1){
            sum = num - 1 + 0;
            return sum;
        }
        else{
            sum = num - 1 + CalcCantRutas(num - 1);
            return sum;
        }
        
        
    }


    public void viewOutput(){
        
        for (lugar i : Paradas){
            
            System.out.println("===============================");

            System.out.println("Parada: " + i.getNombre());
            System.out.println("CantRutas: " + i.getRutas().length);

            System.out.println("Rutas: ");
            for (ruta k : i.getRutas()){
                System.err.println(k.getNombre());
            }
            System.out.println("===============================");
        }
        
    }
    
    public void evaporarFeromonas(){
        for (ruta j : getRutas()){
            j.evaporar();
            j.updateVtag();
        }
    }

    public void asignarValores(){
        for ( ruta j : Rutas){
            j.asignarValor();
        }
    }

    public renderer getRenderer(){
        return Renderer;
    }


    public lugar getInicio(){
        return Paradas[0];
    }
    public int getCantRutas(){
        return Paradas.length;
    }

    public lugar[] getParadas() {
        return Paradas;
    }

    public ruta[] getRutas() {
        return Rutas;
    }


    public input_tag getTotalLocation() {
        return TotalLocation;
    }


    public JButton getReloadButton() {
        return ReloadButton;
    }


    public input_tag getAntAmmountInput() {
        return AntAmmountInput;
    }


    public input_tag getRounds() {
        return Rounds;
    }


    public JButton getExecuteButton() {
        return ExecuteButton;
    }
    
}
