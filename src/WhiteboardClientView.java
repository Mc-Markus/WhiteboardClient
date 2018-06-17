import shared.messages.server.UsersMessage;
import shared.messages.server.WhiteboardMessage;
import shared.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.List;

/**
 * Created by mark on 11/06/2018.
 */
public class WhiteboardClientView extends JFrame implements Observer{

    private SettingsPanel settingsPanel;
    private JPanel whiteboardPanel;
    private JPanel usersPanel;

    private Image whiteboard;

    public static void main(String[] args){
        new WhiteboardClientView();
    }

    public WhiteboardClientView(){

        setTitle("Whiteboard chat client ");
        settingsPanel = new SettingsPanel(this);
        add(settingsPanel);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    public void maakGUI(String name, SendController controller){

        setVisible(false);
        this.remove(settingsPanel);
        System.out.println("gui remove");

        setTitle("Whiteboard chat client " + name);

        whiteboardPanel = new JPanel();
        usersPanel = new JPanel();

        whiteboardPanel.setBackground(Color.PINK);
        usersPanel.setBackground(Color.orange);

        setLayout( new BorderLayout());
        //this.add(whiteboardPanel, BorderLayout.CENTER);
        //this.add(usersPanel, BorderLayout.EAST);

        setVisible(true);

    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof WhiteboardMessage){

            whiteboard = ((WhiteboardMessage)arg).getImage();

            System.out.println("whiteboard updated, should repaint");

            repaint();
        }
        else if( arg instanceof UsersMessage){
            ShowUsers(((UsersMessage) arg).getUsers());
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        g.drawImage(whiteboard, 0,0, 640, 480, null);

    }

    public void ShowUsers(List<User> users){
        usersPanel.removeAll();
        for (User user: users){

            JPanel userPanel = new JPanel();

            userPanel.setBackground(user.getColor());
            userPanel.add(new JLabel(user.getName()));

            usersPanel.add(userPanel);
        }
    }

    public void initConnection(String address, int port, User user){

        WhiteboardClient client = new WhiteboardClient(address, port, user);

        client.addObserver(this);

        SendController controller = new SendController(client, this);

        maakGUI(client.getName(), controller);
    }
}
