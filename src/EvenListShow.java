import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EvenListShow extends JDialog{
    private JTable resultTable;
    private JButton exitButton;
    private JLabel announce;
    private JPanel mainPanel;
    private JButton deleteButton;
    private DefaultTableModel resultModel;

    public EvenListShow(ArrayList<Event> l, String name, JFrame parent, boolean modal){
        super(parent, modal);
        super.add(mainPanel);
        super.setSize(680, 450);


        super.setLocationRelativeTo(null);
        announce.setText("Danh sách sự kiện của chương trình "+ name);
        exitButton.addActionListener(e -> {
            super.dispose();
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                Event temp = new Event();
                temp.setIdProg(l.get(0).getIdProg());
                temp.setJob(resultModel.getValueAt(selectedRow,1).toString());
                temp.setDate(resultModel.getValueAt(selectedRow,2).toString());
                if(Main.myCon.deleteEvent(temp)){
                    JOptionPane.showMessageDialog(mainPanel, "Thành công");
                    resultModel.removeRow(selectedRow);
                }
                else JOptionPane.showMessageDialog(mainPanel, "Thất bại");
            }
        });

        for (int i = 0; i < l.size(); i++){
            resultModel.addRow(new Object[]{i + 1, l.get(i).getJob(), l.get(i).getDate()});
        }
        super.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String header[] = new String[]{"STT", "Công việc", "Thời gian"};
        resultModel = new DefaultTableModel();
        resultTable = new JTable(resultModel);
        resultModel.setColumnIdentifiers(header);
    }
}
