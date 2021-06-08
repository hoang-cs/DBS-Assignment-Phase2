import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEvent extends JDialog{
    private JTextField eventName;
    private JTextField eventTime;
    private JLabel progName;
    private JButton confirmButton;
    private JButton exitButton;
    private JLabel introLabel;
    private JPanel mainPanel;

    public AddEvent(JFrame parent, boolean modal, Event event, String name){
        super(parent, modal);
        introLabel.setText("Điền các thông tin cần thiết cho sự kiện");
        super.add(mainPanel);
        progName.setText(name);
        super.setLocationRelativeTo(null);
        super.setSize(500, 500);
        exitButton.addActionListener(e -> {
            super.dispose();
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.setDate(eventTime.getText());
                event.setJob(eventName.getText());
                event.setIdDept(2);

                executeQuery(event);

            }
        });
        super.setVisible(true);

    }

    private void executeQuery(Event event){
        if(event != null){
            JOptionPane.showMessageDialog(this, "Thêm sự kiện thành công !!!");
        }
        else JOptionPane.showMessageDialog(this, "Thêm sự kiện thất bại.\nVui lòng thử lại sau");
    }

}
