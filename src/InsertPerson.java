import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertPerson extends JFrame{
    private JPanel mainPanel;
    private JLabel nameInput;
    private JTextField CMNDInput;
    private JTextField dobInput;
    private JTextField provinceInput;
    private JTextField districtInput;
    private JTextField villageInput;
    private JButton confirmButton;
    private JButton exitButton;
    Sponsor s;

    public InsertPerson(Sponsor sponsor){

        super();
        super.add(mainPanel);
        super.setSize(600, 580);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        s=sponsor;
        nameInput.setText(s.person.getFullName());
        exitButton.addActionListener(e -> {
            super.dispose();
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.person.setCMND(Integer.parseInt(CMNDInput.getText()));
                s.person.setDob(dobInput.getText());
                s.person.setProvince(provinceInput.getText());
                s.person.setVillage(villageInput.getText());
                s.person.setDistrict(districtInput.getText());
                // EXECUTE INSERT QUERY
                if(Main.myCon.addPerson(s)) {
                    JOptionPane.showMessageDialog(mainPanel, "Thành công");
                    close();
                }
                else JOptionPane.showMessageDialog(mainPanel, "Thất bại");

            }
        });
    }
    public void close(){dispose();}
}
