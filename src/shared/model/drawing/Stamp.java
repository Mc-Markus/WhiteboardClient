package shared.model.drawing;

import java.awt.*;

public class Stamp extends Drawing
{
    private static final long serialVersionUID = 13100;
    private boolean[][] stamp;
    
    public Stamp( Point location, boolean[][] stamp)
    {
        super(location);

        this.stamp = stamp;
    }

    public boolean[][] getStamp()
    {
        return stamp;
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
