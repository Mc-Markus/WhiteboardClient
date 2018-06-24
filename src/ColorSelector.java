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

    //Simple colorSelector, better design than default ColorPicker
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

    //Generates a random color
    private Color generateRandomColor(){
        Random rnd = new Random();
        int red = rnd.nextInt(255);
        int green = rnd.nextInt(255);
        int blue = rnd.nextInt(255);

        updateSliders(red, green, blue);

        return new Color(red, blue, green);
    }

    //updates the sliderposistion after it has been changed by pressing the random color button
    private void updateSliders(int red, int blue, int green){
        redSlider.setValue(red);
        greenSlider.setValue(green);
        blueSlider.setValue(blue);

    }

    public Color getSelectedColor() {
        return selectedColor;
    }


    //updates the live color preview when the value of the sliders is changed
    @Override
    public void stateChanged(ChangeEvent e) {
        this.selectedColor = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
        colorPreview.setBackground(selectedColor);
    }

    //updates the live color preview when you press the random color button
    @Override
    public void actionPerformed(ActionEvent e) {
        this.selectedColor = generateRandomColor();
        colorPreview.setBackground(selectedColor);
    }
}
