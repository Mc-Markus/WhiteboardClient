import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by mark on 11/06/2017.
 */
public class ColorSelector extends JPanel implements ChangeListener, ActionListener{
    private JLabel redLabel = new JLabel("Red");
    private JLabel greenLabel = new JLabel("Green");
    private JLabel blueLabel = new JLabel("Blue");


    private JSlider redSlider = new JSlider(0, 255, 64);
    private JSlider greenSlider = new JSlider(0, 255, 128);
    private JSlider blueSlider = new JSlider(0, 255, 192);


    private JPanel colorPreview = new JPanel();

    private JButton randomColorButton = new JButton("Random Color");

    private Color selectedColor;

    //deze klasse geeft de gebruiker de mogelijkheid, om een kleur te kiezen
    public ColorSelector(){
        setLayout(new GridLayout(4, 2));

        redSlider.addChangeListener(this);
        greenSlider.addChangeListener(this);
        blueSlider.addChangeListener(this);
        randomColorButton.addActionListener(this);

        this.add(redLabel);
        this.add(redSlider);
        this.add(greenLabel);
        this.add(greenSlider);
        this.add(blueLabel);
        this.add(blueSlider);

        selectedColor = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
        colorPreview.setBackground(selectedColor);

        this.add(randomColorButton);
        this.add(colorPreview);
    }

    //deze methode genereerd een random kleur
    private Color generateRandomColor(){
        Random rnd = new Random();
        int red = rnd.nextInt(255);
        int green = rnd.nextInt(255);
        int blue = rnd.nextInt(255);

        updateSliders(red, green, blue);

        return new Color(red, blue, green);
    }

    //deze methode update de sliders naar de waardes van de random kleur
    private void updateSliders(int red, int blue, int green){
        redSlider.setValue(red);
        greenSlider.setValue(green);
        blueSlider.setValue(blue);

    }

    public Color getSelectedColor() {
        return selectedColor;
    }


    //deze methode zorgt ervoor dat als een slider veranderd de kleur en de preview wordt aangepast
    @Override
    public void stateChanged(ChangeEvent e) {
        this.selectedColor = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
        colorPreview.setBackground(selectedColor);
    }

    //deze methode zorgt ervoor dat als er op de knop wordt gedrukt er een random kleur wordt gegenereerd
    // en dat de geselecteerde kleur en de preview worden geupdate
    @Override
    public void actionPerformed(ActionEvent e) {
        this.selectedColor = generateRandomColor();
        colorPreview.setBackground(selectedColor);
    }
}
