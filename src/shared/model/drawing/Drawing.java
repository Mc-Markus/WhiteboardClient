package shared.model.drawing;

import java.awt.Color;
import java.awt.Point;

public abstract class Drawing implements Action
{
    protected Point location;
    protected Color kleur;
    
    public Drawing( Point location )
    {
        this.location = location;
    }

    public Point getLocation()
    {
        return location;
    }

    public void setKleur(Color kleur)
    {
        this.kleur = kleur;
    }

    public Color getKleur()
    {
        return kleur;
    }
}
