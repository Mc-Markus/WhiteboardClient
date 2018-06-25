package shared.model.drawing;

import java.awt.*;

/**
 * Created by mark on 25/06/2018.
 */
public class Ring extends Drawing {

    private int diameter;
    private int width;

    public Ring(Point location, int diameter, int width) {
        super(location);
        this.diameter = diameter;
        this.width = width;
    }

    @Override
    public Point getLocation() {
        return super.getLocation();
    }

    @Override
    public void setKleur(Color kleur) {
        super.setKleur(kleur);
    }

    @Override
    public Color getKleur() {
        return super.getKleur();
    }

    public int getDiameter() {
        return diameter;
    }

    public int getWidth() {
        return width;
    }
}
