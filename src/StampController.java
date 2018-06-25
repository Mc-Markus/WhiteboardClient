import shared.messages.client.DrawingMessage;
import shared.model.drawing.Stamp;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by mark on 24/06/2018.
 */
public class StampController implements DrawingController, MouseListener {

    private WhiteboardClient client;
    private Point location;
    private boolean[][] stamp;

    private final String SQUAREPATH = "/Recourses/blokje.stp";
    private final String CIRCLEPATH = "/Recourses/cirkel.stp";
    private final String RINGPATH = "/Recourses/rondje.stp";
    private final String SMILEYPATH = "/Recourses/smiley.stp";
    private final String SOLIDPATH = "/Recourses/solid.stp";
    private final String LIEPATH = "Recourses/TheLie.stp";


    //selects the correct stamp
    public StampController(WhiteboardClient client, String stampType) {
        this.client = client;

        switch(stampType){
            case "SQUARE":
                loadStamp(SQUAREPATH);
                break;
            case "CIRCLE":
                loadStamp(CIRCLEPATH);
                break;
            //aangepast bij vraag 1
            case "ROUND":
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

    //sends the drawing to the client
    public void sendDrawing() {
        Stamp drawing = new Stamp(location, stamp);
        drawing.setKleur(client.getUser().getColor());
        client.sendMessage(new DrawingMessage(client.getUser(), drawing));
    }

    //registers the location of the stamp
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

    //loads the correct stamp
    public void loadStamp(String path){
        try {
            //ObjectInputStream input = new ObjectInputStream(new FileInputStream(path));
            ObjectInputStream input = new ObjectInputStream(getClass().getResourceAsStream(path));
            stamp = (boolean[][])input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
