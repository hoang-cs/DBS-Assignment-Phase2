import javax.swing.*;

public class PracticeSwing extends JFrame{
    private JPanel panel1;
    private JPanel pan1;
    private JPanel pan2;
    private JPanel pan3;
    public PracticeSwing (){
        super();
        super.add(panel1);
        //pan1.add(new MemberGUI().mainPanel);
        super.setSize(700, 700);
        super.setVisible(true);
    }

    public static void main(String[] args) {
        new PracticeSwing();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        pan1 = new JPanel();
    }
}
