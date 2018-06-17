package shared.model.drawing;

import java.awt.Point;

public class Line extends Drawing
{
    private static final long serialVersionUID = 13300;
    private Point end;
    
    public Line( Point start, Point end )
    {
        super( start );
        this.end = end;
    }

    public Point getEnd()
    {
        return end;
    }
}
