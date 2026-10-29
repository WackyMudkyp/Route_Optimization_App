

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class input_tag {

    private JLabel info_tag;
    private JTextField text_tag;
    
    
    public input_tag(String info_tag) {
        this.info_tag = new JLabel(info_tag);
        
        
        this.text_tag = new JTextField();

        
        //tag.setHorizontalAlignment(SwingConstants.CENTER);
        text_tag.setOpaque(true);
        text_tag.setBackground(Color.BLACK);
        text_tag.setForeground(Color.orange);
        
        this.info_tag.setOpaque(false);
        //this.info_tag.setBackground(Color.BLACK);
        this.info_tag.setForeground(Color.BLACK);
        
    }

    public void setLocation( int posx , int posy){
        
        text_tag.setBounds(posx, posy + 30, 150, 30 );
        
        info_tag.setBounds(posx, posy + 0, 200, 30 );
        
    }

    

    public void setInfo(String info) {
        this.info_tag.setText(info);
    }

    public String getInfo(){

        //tengo que limitar los uhhhhh inputs para que no me pongan un numero negativo hijos de fruta
        return text_tag.getText();
    }

    public float getLocationX(){
        return info_tag.getAlignmentX();
    }
    public float getLocationY(){
        return info_tag.getAlignmentY();
    }

    public JLabel getInfo_tag() {
        return info_tag;
    }

    public JTextField getText_tag() {
        return text_tag;
    }


    
}
