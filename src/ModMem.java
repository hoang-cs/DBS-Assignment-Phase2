import javax.swing.*;
import java.awt.event.*;

public class ModMem extends JDialog{
    private JTextField nameInput;
    private JComboBox depSelect;
    private JTextField idInput;
    private JTextField birthInput;
    private JTextField phoneInput;
    private JTextField roleInput;
    private JButton addButton;
    private JButton modButton;
    private JButton exit;
    private JPanel mainPanel;
    Member m;
    public ModMem(Member mem) {
        super();
        super.add(mainPanel);
        super.setTitle("Chỉnh sửa Thành viên");
        super.setSize(500, 400);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        exit.addActionListener(e -> {
            super.dispose();
        });
        m=mem;
        nameInput.setText(mem.getFullName());
        birthInput.setText(mem.getDob());
        phoneInput.setText(mem.getPhoneNum());
        roleInput.setText(mem.getRole());

        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                m.setFullName(nameInput.getText());
                m.setDob(birthInput.getText());
                m.setPhoneNum(phoneInput.getText());
                m.setRole(roleInput.getText());
                m.setIdDep(depSelect.getSelectedIndex()+1);
                if(Main.val.val(m))
                    if(Main.myCon.modMember(m)){
                        JOptionPane.showMessageDialog(mainPanel, "Chỉnh sửa thành công");
                    }
                    else JOptionPane.showMessageDialog(mainPanel, "Thất bại");
            }
        });
    }
}