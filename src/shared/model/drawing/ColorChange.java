package shared.model.drawing;

import java.awt.Color;

public abstract class ColorChange implements Action
{
    protected Color kleur;

    public ColorChange( Color kleur )
    {
        this.kleur = kleur;
    }

    public Color getKleur()
    {
        return kleur;
    }

}
