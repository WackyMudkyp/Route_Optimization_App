
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class visual_tag {

    private ImageIcon imagen;
    private JLabel image_label;
    private JLabel info_tag_1;
    private JLabel info_tag_2;
    
    public visual_tag(String image) {
        this.imagen = new ImageIcon(image);
        this.image_label = new JLabel(imagen);
        this.info_tag_1 = new  JLabel("Placeholder");
        this.info_tag_2 = new  JLabel("Placeholder");

        


        info_tag_1.setForeground(Color.CYAN);
        info_tag_1.setHorizontalAlignment(SwingConstants.CENTER);
        
        info_tag_2.setForeground(Color.ORANGE);
        //info_tag_2.setHorizontalAlignment(SwingConstants.CENTER);
        setLocation(100, 200);

        //image_label.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(image_label.getWidth(),image_label.getHeight(), Image.SCALE_SMOOTH)));
        //image_label.setHorizontalAlignment(SwingConstants.CENTER);


    }


    public void setImage(String image) {
        this.imagen = new ImageIcon(image);
        
        //image_label.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(image_label.getWidth(),image_label.getHeight(), Image.SCALE_SMOOTH)));
        
        this.image_label = new JLabel(this.imagen);
    }

    public void setInfo1(String info) {
        this.info_tag_1.setText(info);
    }

    public void setInfo2(String info) {
        this.info_tag_2.setText(info);
    }

    public void setLocation( int posx , int posy){
        
        image_label.setBounds(posx - 0, posy - 15, 200, 100 );
        
        info_tag_1.setBounds(posx, posy + 20, 200, 100 );
        info_tag_2.setBounds(posx + 30, posy + 40, 900, 100);
    }


    public ImageIcon getImage() {
        return imagen;
    }


    public JLabel getImage_label() {
        return image_label;
    }


    public JLabel getInfo_tag_1() {
        return info_tag_1;
    }


    public JLabel getInfo_tag_2() {
        return info_tag_2;
    }

    public float getLocationX(){
        return info_tag_1.getAlignmentX();
    }
    public float getLocationY(){
        return info_tag_1.getAlignmentY();
    }


}
