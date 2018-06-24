import shared.messages.client.DrawingMessage;
import shared.model.drawing.Stamp;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by mark on 24/06/2018.
 */
public class StampController implements DrawingController, MouseListener {

    private WhiteboardClient client;
    private Point location;
    private boolean[][] stamp;

    private final String SQUAREPATH = "/blokje.stp";
    private final String CIRCLEPATH = "/cirkel.stp";
    private final String RINGPATH = "/rondje.stp";
    private final String SMILEYPATH = "/smiley.stp";
    private final String SOLIDPATH = "/solid.stp";
    private final String LIEPATH = "/TheLie.stp";


    public StampController(WhiteboardClient client, String stampType) {
        this.client = client;

        switch(stampType){
            case "SQUARE":
                loadStamp(SQUAREPATH);
                break;
            case "CIRCLE":
                loadStamp(CIRCLEPATH);
                break;
            case "RING":
                loadStamp(RINGPATH);
                break;
            case "SMILEY":
                loadStamp(SMILEYPATH);
                break;
            case "SOLID":
                loadStamp(SOLIDPATH);
                break;
            case "LIE":
                loadStamp(LIEPATH);
                break;
        }
    }


    @Override
    public void sendDrawing() {
        Stamp drawing = new Stamp(location, stamp);
        drawing.setKleur(client.getUser().getColor());
        client.sendMessage(new DrawingMessage(client.getUser(), drawing));
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        location = e.getPoint();
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

    public void loadStamp(String path){
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(path));
            stamp = (boolean[][])input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
