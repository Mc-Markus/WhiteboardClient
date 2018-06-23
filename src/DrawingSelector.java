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

    private void changeListener(MouseListener listener){
        whiteboardPanel.removeMouseListener(controller);
        controller = listener;
        whiteboardPanel.addMouseListener(controller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //send message
        System.out.println(e.getActionCommand());

        switch (e.getActionCommand()){
            case "LINE":
                changeListener(new LineController(client));
                break;
        }
    }
}