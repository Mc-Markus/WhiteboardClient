import shared.messages.server.WhiteboardMessage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mark on 23/06/2018.
 */
public class WhiteboardView extends JPanel{

    private Image image;

    public WhiteboardView(){
        setBounds(5,  5, 640, 480);
    }

    public void draw(WhiteboardMessage message){
        image = message.getImage();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        System.out.println("painting");
        synchronized (image){
            g.drawImage(image, 0,0, image.getWidth(null), image.getHeight(null), null);
        }
    }
}