package shared.messages.client;

import shared.model.User;
import shared.model.drawing.Drawing;

public class DrawingMessage extends ClientMessage
{
    private static final long serialVersionUID = 11400;
    private Drawing drawing;
    
    public DrawingMessage( User sender, Drawing drawing )
    {
        super(sender);

        this.drawing = drawing;
    }

    public DrawingMessage( DrawingMessage message )
    {
        this( message.sender, message.drawing);
    }

    public Drawing getDrawing()
    {
        return drawing;
    }

    public void setDrawing( Drawing drawing )
    {
        this.drawing = drawing;
    }
}
