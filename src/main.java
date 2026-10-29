
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class main {
    
    private static delivery_car [] hormigas ;
    //hola :)


    public static void main(String[] args) {
        
        

        renderer v1 = new renderer();

        v1.setVisible(true); 

        //v1.crearImagen(100, 222, "C:\\Alex\\Programacion\\Programas\\JAVA\\ActionListener\\Pizzant.png");

        
        
        map Mapa = new map(4 , v1);
        

        //conecta el boton de ejecutar
        ActionListener StartupListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                
                
                boolean flag = false;
                for ( ruta x : Mapa.getRutas()){
                    String check = x.getItag().getInfo();
                    float fcheck = 0;
                    try{ 
                        
                        fcheck = Float.parseFloat(check);
                        flag = true;
                        if (fcheck <= 0){
                        flag = false;
                        JOptionPane.showMessageDialog(null, "Buen intento, ingresa un valor válido (Mayor que 0) en: " + x.getNombre());
                        return; 
                    } 

                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Buen intento, ingresa un valor válido (Mayor que 0) en: " + x.getNombre());
                        flag = false;
                    }
                    
                }
                String cHorm = Mapa.getAntAmmountInput().getInfo();
                float FcHorm = 0 ;
                    try{ 
                        
                        FcHorm = Float.parseFloat(cHorm);
                        flag = true;
                        if (FcHorm <= 0){
                        flag = false;
                        JOptionPane.showMessageDialog(null, "Ingresa un valor válido para cantidad de hormigas (Mayor que 1) ");
                        return;     
                    }

                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Ingresa un valor válido para cantidad de hormigas (Mayor que 1) ");
                        flag = false;
                    }
                String cRonda = Mapa.getRounds().getInfo();
                int IcRonda = 0;
                    try{ 
                        
                        IcRonda = Integer.parseInt(cRonda);
                        flag = true;
                        if (IcRonda <= 0){
                        flag = false;
                        JOptionPane.showMessageDialog(null, "Ingresa un valor válido para cantidad de rondas (Entero mayor que 1) ");
                        return;   
                    }

                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Ingresa un valor válido para cantidad de rondas (Entero mayor que 1) ");
                        flag = false;
                    }


            if (flag){
                Mapa.asignarValores(); 
                v1.switchPaneles();

                hormigas = new  delivery_car[(int)FcHorm];
                for (int i = 0 ; i < hormigas.length; i++){
                    hormigas[i] = new delivery_car(Mapa);
                }
                for (int i = 0 ; i < IcRonda ; i++){
                    iniciaRonda(hormigas, Mapa);
                }

                delivery_car auxiliar = hormigas[0];
                for (delivery_car i : hormigas ){
                    if (i.getMejorDist() < auxiliar.getMejorDist()){
                        auxiliar = i;
                    }
                }
                String dist = " " +auxiliar.getMejorDist(); 
                String rutaVis = "Rutas Utilizadas: ";
                for (ruta k : auxiliar.getMejorRuta()){
                rutaVis += k.getNombre()  + " --> ";
                }
                v1.actualizarResultados(dist, rutaVis);
                

            }  
        }
    };
        
        Mapa.getExecuteButton().addActionListener(StartupListener);
    
    }

    static public void iniciaRonda( delivery_car [] hormiguero , map Mapa){

        for (delivery_car C : hormiguero){
            C.recorrerMapa();
        }
        Mapa.evaporarFeromonas();

        

    }
    
}