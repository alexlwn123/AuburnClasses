import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductController {
    AddButtonListerner addButtonListerner = new AddButtonListerner();
    CancelButtonListerner cancelButtonListerner = new CancelButtonListerner();

}

class AddButtonListerner implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null, "You click on Add button!!!");
    }
}

class CancelButtonListerner implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null, "You click on Cancel button!!!");
    }
}
