import shared.messages.client.DrawingMessage;
import shared.model.drawing.Line;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by mark on 23/06/2018.
 */
public class LineController implements DrawingController, MouseListener {

    private WhiteboardClient client;
    private Point start;
    private Point end;

    public LineController(WhiteboardClient client) {
        this.client = client;
    }

    //sends the drawing to the client
    public void sendDrawing(){
        Line line = new Line(start, end);
        line.setKleur(client.getUser().getColor());
        client.sendMessage(new DrawingMessage(client.getUser(), line));
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //registers the starting location
    @Override
    public void mousePressed(MouseEvent e) {
        this.start = e.getPoint();
    }

    //registers the end location
    @Override
    public void mouseReleased(MouseEvent e) {
        this.end = e.getPoint();
        sendDrawing();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
