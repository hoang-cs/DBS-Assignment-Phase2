import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyCompany extends JDialog{
    private JPanel mainPanel;
    private JTextField compNameInput;
    private JTextField MSInput;
    private JTextField reNameInput;
    private JTextField phoneNumInput;
    private JLabel IDLabel;
    private JLabel moneyLabel;
    private JButton confirmButton;
    private JButton exitButton;

    public ModifyCompany(JFrame parent, boolean modal, Sponsor s){
        super(parent, modal);
        super.add(mainPanel);

        IDLabel.setText(s.getIdSponsor().toString());
        compNameInput.setText(s.company.getCompName());
        MSInput.setText(s.company.getMS().toString());
        reNameInput.setText(s.company.getRepName());
        phoneNumInput.setText(s.company.getRepPhoneNum());
        moneyLabel.setText(s.getMoney().toString());

        exitButton.addActionListener(e -> {
            super.dispose();
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // EXECUTE UPDATE QUERY HERE

                s.company.setCompName(compNameInput.getText());
                s.company.setMS(Integer.parseInt(MSInput.getText()));
                s.company.setRepName(reNameInput.getText());
                s.company.setRepPhoneNum(phoneNumInput.getText());
                if(Main.val.val(s))
                if(Main.myCon.modCom(s))
                    JOptionPane.showMessageDialog(mainPanel, "Chỉnh sửa thông tin thành công");
                else JOptionPane.showMessageDialog(mainPanel, "Chỉnh sửa thông tin thất bại !!!");
            }
        });
        super.setLocationRelativeTo(null);
        super.setSize(700, 600);
        super.setVisible(true);

    }

}
