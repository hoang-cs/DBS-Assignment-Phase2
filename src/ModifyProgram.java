import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProgram extends JDialog{
    private JTextField nameInput;
    private JTextField nationInput;
    private JTextField provinceInput;
    private JTextField startTimeInput;
    private JTextField endTimeInput;
    private JTextField moneyInput;
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton exitButton;
    private JCheckBox ttCheck;
    private JCheckBox tnCheck;
    private JCheckBox gdCheck;

    public ModifyProgram(JFrame parent, boolean modal, Program p){
        super(parent, modal);
        super.add(mainPanel);
        nameInput.setText(p.getName());
        nationInput.setText(p.getNation());
        provinceInput.setText(p.getCoordinate());
        startTimeInput.setText(p.getStartTime());
        endTimeInput.setText(p.getEndTime());
        moneyInput.setText(p.getMoney().toString());


        if (p.getType().contains("GD"))
            gdCheck.setSelected(true);
        if (p.getType().contains("TN"))
            tnCheck.setSelected(true);
        if (p.getType().contains("TT"))
            ttCheck.setSelected(true);
        exitButton.addActionListener(e -> {
            dispose();
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // EXECUTE QUERY UPDATE
                p.setMoney(Integer.parseInt(moneyInput.getText()));
                p.setName(nameInput.getText());
                p.setNation(nationInput.getText());
                //bug type
                if (tnCheck.isSelected())   p.setType("TN");
                if (gdCheck.isSelected())   p.setType(" GD");
                if (ttCheck.isSelected())   p.setType(" TT");

                p.setCoordinate(provinceInput.getText());
                p.setStartTime(startTimeInput.getText());
                p.setEndTime(endTimeInput.getText());

                // call function
                if(Main.val.val(p))
                if(Main.myCon.modProgramInfor(p)){
                    JOptionPane.showMessageDialog(mainPanel, "Đã cập nhật chương trình");
                }
                else JOptionPane.showMessageDialog(mainPanel, "Thất bại");
            }
        });
        super.setTitle("Chỉnh sửa thông tin chương trình");
        super.setSize(600, 600);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }
}
