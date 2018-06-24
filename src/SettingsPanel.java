import shared.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mark on 11/06/2017.
 */
public class SettingsPanel extends JPanel implements ActionListener{
    private JButton saveButton = new JButton("Save & Connect");

    private JTextField usernameField = new JTextField("Mark");
    private JTextField ipAddressField = new JTextField("127.0.0.1");
    private JTextField portField = new JTextField("12345");

    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel ipAddressLabel = new JLabel("Ip Address");
    private JLabel portLabel = new JLabel("Port: ");

    private JPanel fields = new JPanel(new GridLayout(3,2));

    private ColorSelector colorSelector = new ColorSelector();

    private WhiteboardClientView view;

    //SettingsPanel is for setting the ip, port, name and color
    public SettingsPanel(WhiteboardClientView view){
        this.view = view;

        setLayout(new GridLayout(3,1));

        fields.add(ipAddressLabel);
        fields.add(ipAddressField);

        fields.add(portLabel);
        fields.add(portField);

        fields.add(usernameLabel);
        fields.add(usernameField);

        saveButton.addActionListener(this);

        this.add(fields);

        this.add(colorSelector);

        this.add(saveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(usernameField.getText() != null && ipAddressField.getText() != null && portField.getText() != null){
            view.initConnection(ipAddressField.getText(), Integer.parseInt(portField.getText()), new User(usernameField.getText(), colorSelector.getSelectedColor()));
        }
    }
}
