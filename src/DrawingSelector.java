import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 * Created by mark on 11/06/2018.
 */
public class DrawingSelector implements ActionListener{

    private WhiteboardClient client;
    private WhiteboardClientView view;
    private MouseListener controller;
    private WhiteboardView whiteboardPanel;

    public DrawingSelector(WhiteboardClient client, WhiteboardClientView view, WhiteboardView whiteboardView) {
        this.client = client;
        this.view = view;
        this.whiteboardPanel = whiteboardView;
    }

    //changes the current mouselistener
    private void changeListener(MouseListener listener){
        whiteboardPanel.removeMouseListener(controller);
        controller = listener;
        whiteboardPanel.addMouseListener(controller);
    }

    //detects which button was pressed and activates corresponding listener
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        switch (e.getActionCommand()){
            case "LINE":
                changeListener(new LineController(client));
                break;
            case "TEXT":
                changeListener(new TextController(client));
                break;
            case "SQUARE":
                changeListener(new StampController(client, e.getActionCommand()));
                break;
            case "CIRCLE":
                changeListener(new StampController(client, e.getActionCommand()));
                break;
            case "RING":
                changeListener(new StampController(client, e.getActionCommand()));
                break;
            case "SMILEY":
                changeListener(new StampController(client, e.getActionCommand()));
                break;
            case "SOLID":
                changeListener(new StampController(client, e.getActionCommand()));
                break;
            case "LIE":
                changeListener(new StampController(client, e.getActionCommand()));
                break;

        }
    }
}
