import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

public class EditScreen extends JFrame{
    private final JTextArea textField = new JTextArea();
    private final JButton saveButton = new JButton("Save");
    private final EditScreen thisScreen = this;
    private final JPanel panel = new JPanel();

    public EditScreen(String fileSource, BaseScreen baseScreen, String text){
        this(fileSource, baseScreen);
        textField.setText(text);
        setTitle("Editing: "+fileSource);
    }
    public EditScreen(String fileSource, BaseScreen baseScreen) {

        setLayout(null);

        add(panel);
        textField.setBackground(Color.DARK_GRAY);
        saveButton.setBackground(Color.GRAY);

        setVisible(true);
        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
        setExtendedState(MAXIMIZED_BOTH);
        requestFocus();

        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(getBounds());
        panel.setLayout(null);

        saveButton.requestFocus();

        textField.setLineWrap(true);
        textField.setForeground(Color.WHITE);
        textField.setBounds(20,60,getWidth()-60,getHeight()-120);
        panel.add(textField);
        textField.requestFocus();

        saveButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispatchEvent(new WindowEvent(thisScreen, WindowEvent.WINDOW_CLOSING));
                baseScreen.saveText(fileSource, textField.getText());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        saveButton.setBounds(20,20,120,20);
        panel.add(saveButton);

    }
}
