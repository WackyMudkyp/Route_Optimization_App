

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class renderer extends JFrame{

    private JPanel MainPanel;
    private JPanel InputPanel;

    private String currentPanel;

    private JTextField[] CajasDeTexto;


    private visual_tag resultados;
    

    public renderer () {
        
        this.setSize(1600 ,1200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Optimizador de Ruta");
        setLocationRelativeTo(null);
        
        iniciarComponentes();

        
    
    }

    private void iniciarComponentes(){


        iniciarMainPanel();
        iniciarInputPanel();

        addPanel(MainPanel);
        
        
        
        //crearImagen(10, 10 , "C:\\Alex\\Programacion\\Programas\\JAVA\\PizzaDelivery\\Pizzant.png");
        //crearLinea(10 , 10 , 300 , 100 , 7);
        resultados = new visual_tag("");
        resultados.setLocation(400, 450);
        resultados.setInfo1("s");
        resultados.setInfo2("");

        agregarVTag(resultados);
        
        
        
    
        
        switchPaneles();
        //switchPaneles();
    }

    public void actualizarResultados(String inf1 , String inf2  ){

        resultados.setInfo1(inf1);
        resultados.setInfo2(inf2);
    }

    private void iniciarMainPanel(){
        MainPanel = new JPanel();
        MainPanel.setName("Main");
        MainPanel.setLayout(null);
        MainPanel.setBackground(Color.darkGray);

    }
    private void iniciarInputPanel(){
        InputPanel = new JPanel();   
        MainPanel.setName("Input");
        InputPanel.setLayout(null);
        InputPanel.setBackground(Color.darkGray);    
    }

    private void addPanel(JPanel panel){
 
        this.getContentPane().add(panel);
        if (panel == InputPanel){
            currentPanel= "Input";
        }
        if (panel == MainPanel){
            currentPanel= "Main";
        }        
        
        System.out.println(currentPanel);
    }


    public void switchPaneles(){
        if (currentPanel == "Input"){
            getContentPane().removeAll();
            currentPanel = "Main";
            addPanel( MainPanel);
            revalidate();
            repaint();
            return;
        }
        if (currentPanel == "Main"){
            getContentPane().removeAll();
            currentPanel = "Input";
            addPanel(InputPanel);
            revalidate();
            repaint();
        }        
    }

    public void agregarVTag(visual_tag vtag){
        MainPanel.add(vtag.getImage_label());
        MainPanel.add(vtag.getInfo_tag_1());
        MainPanel.add(vtag.getInfo_tag_2());
        
    }

    public void agregarITag(input_tag itag){
        InputPanel.add(itag.getText_tag());
        InputPanel.add(itag.getInfo_tag());

        
    }

    


    public void crearPunto(  int posx , int posy){
        JLabel tag = new JLabel("*");
        tag.setHorizontalAlignment(SwingConstants.CENTER);
        tag.setOpaque(false);
        tag.setForeground(Color.orange);
        tag.setBounds(posx,posy,20,20);
        MainPanel.add(tag); 
        
    }

    public void crearLinea( int startx , int starty , int endx , int endy , int density ){

        int startauxX = startx;
        int startauxY = starty;
        int endauxX = endx;
        int endauxY = endy;

        crearPunto(startx,starty);
        crearPunto(endx,endy);

        if ((startauxX < endauxX) || (startauxY < endauxY)){
            while ((startauxX < endauxX) || (startauxY < endauxY)) { 
           
                if(startauxX < endauxX){
                    startauxX +=density;
                }
                if(startauxY < endauxY){
                startauxY +=density;
                }
                
                crearPunto(startauxX,startauxY);

            }
        }
    }

    public void crearImagen (int posx , int posy , String file){
        ImageIcon imagen = new ImageIcon(file);

        JLabel img1 = new JLabel(imagen);
        img1.setBounds(posx ,posy,100,100);

        img1.setIcon(new ImageIcon(imagen.getImage()));
        MainPanel.add(img1);  
    }





    public void crearInputtag(  int posx , int posy , String text){
        JLabel tag = new JLabel(text);
        tag.setHorizontalAlignment(SwingConstants.CENTER);
        tag.setOpaque(false);
        tag.setForeground(Color.orange);
        tag.setBounds(posx,posy,20,20);
        MainPanel.add(tag); 
        
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public JPanel getInputPanel() {
        return InputPanel;
    }

    public String getCurrentPanel() {
        return currentPanel;
    }

    public JTextField[] getCajasDeTexto() {
        return CajasDeTexto;
    }

    public visual_tag getResultados() {
        return resultados;
    }



}