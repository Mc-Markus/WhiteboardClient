import shared.messages.client.DrawingMessage;
import shared.model.drawing.Ring;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by mark on 25/06/2018.
 */
public class RingController implements DrawingController, MouseListener {

    private WhiteboardClient client;
    private Point location;
    private int diameter;
    private int width;

    public RingController(WhiteboardClient client) {
        this.client = client;
    }

    @Override
    public void sendDrawing() {
        Ring ring = new Ring(location, diameter, width);
        ring.setKleur(client.getUser().getColor());
        client.sendMessage(new DrawingMessage(client.getUser(), ring));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        location = e.getPoint();

        diameter = 60;
        width = 5;

        sendDrawing();
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
