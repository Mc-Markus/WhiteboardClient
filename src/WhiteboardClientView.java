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

    private final Dimension SCREENSIZE = new Dimension(750,540);

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

    //this method is responsible for the correct closing of the connection with the server
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

    //Makes the gui
    public void maakGUI(){

        setVisible(false);
        this.remove(settingsPanel);
        System.out.println("gui removed");

        setTitle("Whiteboard chat client " + client.getName());

        setMinimumSize(SCREENSIZE);
        setMaximumSize(SCREENSIZE);
        setPreferredSize(SCREENSIZE);

        whiteboardPanel = new WhiteboardView();
        toolPanel = new JPanel();

        DrawingSelector controller = new DrawingSelector(client, this, whiteboardPanel);
        makeToolPanel(toolPanel, controller);

        usersPanel.setBackground(Color.orange);
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.X_AXIS));

        setLayout( new BorderLayout());
        this.add(whiteboardPanel, BorderLayout.CENTER);
        this.add(usersPanel, BorderLayout.SOUTH);
        this.add(toolPanel, BorderLayout.EAST);

        pack();

        setVisible(true);
    }

    //creates the toolpanel
    private void makeToolPanel(JPanel toolPanel, DrawingSelector controller){

        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));

        JButton lineButton = new JButton("Line");
        lineButton.setActionCommand("LINE");
        lineButton.addActionListener(controller);

        JButton textButton = new JButton("Text");
        textButton.setActionCommand("TEXT");
        textButton.addActionListener(controller);

        JButton squareStampButton = new JButton("Square");
        squareStampButton.setActionCommand("SQUARE");
        squareStampButton.addActionListener(controller);

        JButton circleStampButton = new JButton("Circle");
        circleStampButton.setActionCommand("CIRCLE");
        circleStampButton.addActionListener(controller);

        //aangepast vanwege vraag 1
        JButton ringStampButton = new JButton("Round");
        ringStampButton.setActionCommand("ROUND");
        ringStampButton.addActionListener(controller);

        JButton smileyStampButton = new JButton("Smiley");
        smileyStampButton.setActionCommand("SMILEY");
        smileyStampButton.addActionListener(controller);

        JButton solidStampButton = new JButton("Solid");
        solidStampButton.setActionCommand("SOLID");
        solidStampButton.addActionListener(controller);

        JButton lieStampButton = new JButton("LIE");
        lieStampButton.setActionCommand("LIE");
        lieStampButton.addActionListener(controller);

        //toegevoegd bij vraag 1
        JButton ringButton = new JButton("Ring");
        ringButton.setActionCommand("RING");
        ringButton.addActionListener(controller);


        toolPanel.add(lineButton);
        toolPanel.add(textButton);
        toolPanel.add(squareStampButton);
        toolPanel.add(circleStampButton);
        toolPanel.add(ringStampButton);
        toolPanel.add(smileyStampButton);
        toolPanel.add(solidStampButton);
        toolPanel.add(lieStampButton);
        toolPanel.add(ringButton);
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

    //updates the visible list of users
    public void ShowUsers(List<User> users){
        usersPanel.setVisible(false);
        usersPanel.removeAll();

        for (User user: users){
            System.out.println(user.getName());
            JPanel userPanel = new JPanel();
            userPanel.setBackground(user.getColor());
            JLabel nameLabel = new JLabel(user.getName());
            userPanel.add(nameLabel);

            usersPanel.add(userPanel);
        }
        usersPanel.setVisible(true);
    }

    //sets up the connetion to the server
    public void initConnection(String address, int port, User user){

        client = new WhiteboardClient(address, port, user);

        client.addObserver(this);

        maakGUI();

        stopOnClose();
    }
}
