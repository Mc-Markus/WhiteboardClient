package shared.model.drawing;

import java.awt.Point;

public class TextDrawing extends Drawing
{
    private static final long serialVersionUID = 13200;
    private String text; 
    
    public TextDrawing( Point location, String text )
    {
        super( location );
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
}
