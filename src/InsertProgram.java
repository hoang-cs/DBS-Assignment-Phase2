import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertProgram extends JFrame{
    private JPanel mainPanel;
    private JTextField moneyInput;
    private JTextField nationInput;
    private JLabel tnType;
    private JLabel ttType;
    private JLabel gdType;
    private JTextField provinceInput;
    private JLabel endTime;
    private JLabel startTime;
    private JLabel progName;
    private JButton confirmButton;
    private JButton exitButton;
    private Program program;

    public InsertProgram(Program p){
        super();
        program = p;

        if (program.getType().contains("TN"))
            tnType.setText("TN");
        else tnType.setText("");

        if (program.getType().contains("GD"))
            gdType.setText("GD");
        else gdType.setText("");

        if (program.getType().contains("TT"))
            ttType.setText("TT");
        else ttType.setText("");

        super.setTitle("Điền thêm thông tin");
        progName.setText(program.getName());
        endTime.setText(program.getEndTime());
        startTime.setText(program.getStartTime());
        super.add(mainPanel);
        super.setSize(600, 600);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        exitButton.addActionListener(e -> {
            super.dispose();
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                program.setMoney(Integer.parseInt(moneyInput.getText()));
                program.setNation(nationInput.getText());
                program.setCoordinate(provinceInput.getText());

                if (Main.myCon.addProgramInfor(program))
                    JOptionPane.showMessageDialog(mainPanel, "Thêm thông tin thành công");
                else
                    JOptionPane.showMessageDialog(mainPanel, "Thêm thông tin thất bại, dữ liệu có thể đã bị trùng lặp\n" +
                                                                        "vui lòng thử lại");
            }
        });
    }
}
