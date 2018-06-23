import shared.messages.client.StopMessage;
import shared.messages.server.UsersMessage;
import shared.messages.server.WhiteboardMessage;
import shared.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.List;

/**
 * Created by mark on 11/06/2018.
 */
public class WhiteboardClientView extends JFrame implements Observer{

    private SettingsPanel settingsPanel;
    private WhiteboardView whiteboardPanel;
    private JPanel usersPanel = new JPanel();
    private JPanel toolPanel;
    private WhiteboardClient client;

    private final Dimension SCREENSIZE = new Dimension(700,540);

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

    public void stopOnClose(){
        this.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing...");

                client.sendMessage(new StopMessage(client.getUser()));

                System.exit(0);
            }
        });
    }

    public void maakGUI(){

        setVisible(false);
        this.remove(settingsPanel);
        System.out.println("gui remove");

        setTitle("Whiteboard chat client " + client.getName());

        /*setMinimumSize(SCREENSIZE);
        setMaximumSize(SCREENSIZE);
        setPreferredSize(SCREENSIZE);*/

        whiteboardPanel = new WhiteboardView();
        usersPanel = new JPanel();
        toolPanel = new JPanel();

        DrawingSelector controller = new DrawingSelector(client, this, whiteboardPanel);
        makeToolPanel(toolPanel, controller);

        whiteboardPanel.setBackground(Color.PINK);
        usersPanel.setBackground(Color.orange);
        toolPanel.setBackground(Color.CYAN);

        setLayout( new BorderLayout());
        this.add(whiteboardPanel, BorderLayout.CENTER);
        this.add(usersPanel, BorderLayout.EAST);
        this.add(toolPanel, BorderLayout.SOUTH);

        pack();

        setVisible(true);
    }

    private void makeToolPanel(JPanel toolPanel, DrawingSelector controller){
        JButton lineButton = new JButton("Line");
        lineButton.setActionCommand("LINE");
        lineButton.addActionListener(controller);

        toolPanel.add(lineButton);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof WhiteboardMessage){

            WhiteboardMessage message = (WhiteboardMessage)arg;
            whiteboardPanel.draw(message);
        } else if( arg instanceof UsersMessage){
            ShowUsers(((UsersMessage) arg).getUsers());
        }
    }

    public void ShowUsers(List<User> users){
        setVisible(false);
        usersPanel.removeAll();

        usersPanel.setBounds(0, 0, 60, 500);
        usersPanel.setLayout(new GridLayout(16, 1));

        for (User user: users){
            System.out.println(user.getName());
            JPanel userPanel = new JPanel();

            userPanel.setBackground(user.getColor());
            userPanel.add(new JLabel(user.getName()));

            usersPanel.add(userPanel);
        }
        setVisible(true);
    }

    public void initConnection(String address, int port, User user){

        client = new WhiteboardClient(address, port, user);

        client.addObserver(this);

        maakGUI();

        stopOnClose();
    }
}
