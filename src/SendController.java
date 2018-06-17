import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mark on 11/06/2018.
 */
public class SendController implements ActionListener{

    private WhiteboardClient client;
    private WhiteboardClientView view;

    public SendController(WhiteboardClient client, WhiteboardClientView view) {
        this.client = client;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //send message
    }
}
