import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class JoinedProgramList extends JFrame{
    private JTable resultList;
    private JButton exitButton;
    private JPanel mainPanel;
    private DefaultTableModel resultModel;
    public JoinedProgramList(ArrayList<Program> l){
        super();
        super.add(mainPanel);
        super.setSize(650, 500);
        super.setLocationRelativeTo(null);
        exitButton.addActionListener(e -> {
            super.dispose();
        });
        for(Program p : l){
            resultModel.addRow(new Object[]{p.getId(), p.getName(), p.getType(),
                    p.getCoordinate(), p.getNation(), p.getEndTime()});
        }
        super.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        resultModel = new DefaultTableModel();
        resultList = new JTable(resultModel);
        resultModel.setColumnIdentifiers(new String[]{"ID", "Tên chương trình", "Loại chương trình", "Tỉnh/thành phố",
                "Quốc gia", "Thời gian kết thúc"});
    }
}
