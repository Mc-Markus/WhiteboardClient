import shared.messages.client.DrawingMessage;
import shared.model.drawing.TextDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by mark on 23/06/2018.
 */
public class TextController implements DrawingController, MouseListener{

    private WhiteboardClient client;
    private Point location;
    private String text;

    public TextController(WhiteboardClient client) {
        this.client = client;
    }

    @Override
    public void sendDrawing() {
        TextDrawing textDrawing = new TextDrawing(location, text);
        textDrawing.setKleur(client.getUser().getColor());
        client.sendMessage(new DrawingMessage(client.getUser(), textDrawing));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        location = e.getPoint();

        Object object = JOptionPane.showInputDialog(null, "Enter your message:", "Write message",
                JOptionPane.PLAIN_MESSAGE, null, null, "");

        if(object!= null){
            text = (String)object;
            if(text.length() > 0){
                sendDrawing();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
