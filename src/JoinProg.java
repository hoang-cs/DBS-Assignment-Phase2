import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class JoinProg extends JDialog{
    private JPanel mainPanel;
    private JTable resultTable;
    private JButton joinButton;
    private JButton exitButton;
    private JLabel titleLabel;
    private DefaultTableModel resultModel;

    public JoinProg (JFrame parent, boolean modal, Student s){
        super(parent, modal);
        super.add(mainPanel);
        joinButton.setEnabled(false);
        titleLabel.setText("Chọn chương trình sẽ tham gia cho sinh viên");
        super.setLocationRelativeTo(null);
        super.setSize(740, 500);
        exitButton.addActionListener(e -> {
            super.dispose();
        });

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                Program p = new Program();
                p.setId(Integer.parseInt(resultModel.getValueAt(selectedRow, 0).toString()));
                checkJoin(p, s);
            }
        });
        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                joinButton.setEnabled(true);
            }
        });
        super.setVisible(true);


    }

    public void checkJoin(Program p, Student s){
        if (Main.myCon.joinProgram(p, s))
            JOptionPane.showMessageDialog(this, "Tham gia thành công");
        else
            JOptionPane.showMessageDialog(this,
                    "Tham gia không thành công.\nVui lòng thử lại sau");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String[] header = new String[]{"ID", "Tên chương trình", "Loại chương trình", "Tỉnh/thành phố",
                                        "Quốc gia", "Thời gian kết thúc"};

        resultModel = new DefaultTableModel();
        resultTable = new JTable(resultModel);
        resultModel.setColumnIdentifiers(header);
        ArrayList<Program> l = Main.myCon.searchProgramActive();
        for (Program p:l){
            resultModel.addRow(new Object[]{p.getId(), p.getName(),
                    p.getType(),p.getCoordinate(), p.getNation(), p.getEndTime()});
        }
    }
}
