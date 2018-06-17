package shared.messages.server;

import java.awt.Image;

import javax.swing.ImageIcon;

public class WhiteboardMessage extends ServerMessage
{
    private static final long serialVersionUID = 12100;

    protected ImageIcon image;

    public WhiteboardMessage( Image image )
    {
        super();
        this.image = new ImageIcon(image);
    }

    public Image getImage()
    {
        return image.getImage();
    }
}
