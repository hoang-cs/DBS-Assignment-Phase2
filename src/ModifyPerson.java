import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyPerson extends JDialog{
    private JTextField nameInput;
    private JTextField CMNDInput;
    private JTextField dobInput;
    private JTextField villageInput;
    private JTextField districtInput;
    private JTextField provinceInput;
    private JLabel IDLabel;
    private JLabel moneyLabel;
    private JButton confirmButton;
    private JButton exitButton;
    private JPanel mainPanel;

    public ModifyPerson(JFrame parent, boolean modal, Sponsor s){
        super(parent, modal);
        super.add(mainPanel);
        IDLabel.setText(s.getIdSponsor().toString());
        nameInput.setText(s.person.getFullName());
        CMNDInput.setText(s.person.getCMND().toString());
        dobInput.setText(s.person.getDob());
        villageInput.setText(s.person.getVillage());
        districtInput.setText(s.person.getDistrict());
        provinceInput.setText(s.person.getProvince());
        moneyLabel.setText(s.getMoney().toString());

        exitButton.addActionListener(e -> {
            super.dispose();
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.person.setFullName(nameInput.getText());
                s.person.setCMND(Integer.parseInt(CMNDInput.getText()));
                s.person.setDob(dobInput.getText());
                s.person.setVillage(villageInput.getText());
                s.person.setDistrict(districtInput.getText());
                s.person.setProvince(provinceInput.getText());
                if(Main.val.val(s))
                if(Main.myCon.modPerson(s))
                    JOptionPane.showMessageDialog(mainPanel, "Chỉnh sửa thông tin thành công");
                else JOptionPane.showMessageDialog(mainPanel, "Chỉnh sửa thông tin thất bại !!!");
            }
        });
        super.setSize(650, 700);
        super.setLocationRelativeTo(null);
        super.setVisible(true);

    }
}
