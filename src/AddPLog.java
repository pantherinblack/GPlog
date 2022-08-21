import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddPLog extends JFrame {
    private final JTextField textField = new JTextField();
    private final JButton addButton = new JButton("Add PLog");
    private final BaseScreen baseScreen;
    private final AddPLog thisscreen = this;
    public AddPLog(BaseScreen baseScreen) throws HeadlessException {
        this.baseScreen = baseScreen;
        setTitle("Creating new PLog");
        setLayout(null);
        setVisible(true);
        setBounds(0,0,400,140);
        requestFocus();
        textField.setBounds(20,20,360,20);
        setTitle("Add PLog");
        textField.setToolTipText("Filename");
        add(textField);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        textField.requestFocus();

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runCreation();
            }
        });
        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                runCreation();
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
        addButton.setBounds(20, 60, 120, 20);
        add(addButton);
    }

    private void runCreation(){
        if ( textField.getText()!="" && textField.getText()!=null && !textField.getText().isBlank() && baseScreen.getController().searchIndexByDocName(textField.getText())==-1) {
            dispatchEvent(new WindowEvent(thisscreen, WindowEvent.WINDOW_CLOSING));
            baseScreen.addPLog(textField.getText().replace("\n", "").replace(".txt", ""));
        }
    }

}
