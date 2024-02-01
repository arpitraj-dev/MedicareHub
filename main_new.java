import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class main_new extends log_in {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    main_new frame = new main_new();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public main_new() {
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBounds(0, 0, 1366, 768);

        // Create a JLabel to hold the background image
        JLabel backgroundLabel = new JLabel();
        contentPane = new JPanel();
        contentPane.setLayout(null);

        // Load the background image and set it to the JLabel
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("back-image.jpg"));
        Image img = backgroundImage.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, 1366, 768);

        // Add the JLabel with the background image to the content pane
        contentPane.add(backgroundLabel);

        // Add the heading "MediCareHub" in big bold letters directly to the content
        // pane
        // JLabel headingLabel = new JLabel("MediCareHub");
        // headingLabel.setFont(new Font("Arial", Font.BOLD, 40));
        // headingLabel.setBounds(500, 50, 400, 50);
        // contentPane.add(headingLabel);

        JButton btnNewButton = new JButton("Add New Patient");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new addnew().setVisible(true);
            }
        });
        btnNewButton.setBounds(38, 186, 365, 45);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1_1 = new JButton("Add Diagnosis Information");
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new add_diagonosis().setVisible(true);
            }
        });
        btnNewButton_1_1.setBounds(38, 259, 365, 45);
        contentPane.add(btnNewButton_1_1);

        JButton btnNewButton_1_2 = new JButton("Full History Of Patients");
        btnNewButton_1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new patient_history().setVisible(true);
            }
        });
        btnNewButton_1_2.setBounds(38, 334, 365, 45);
        contentPane.add(btnNewButton_1_2);

        JButton btnNewButton_1_3 = new JButton("Log out");
        btnNewButton_1_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO Log Out ?", "Select",
                        JOptionPane.YES_NO_OPTION);
                if (a == 0) {
                    setVisible(false);
                    new log_in().setVisible(true);
                }
            }
        });
        btnNewButton_1_3.setBounds(38, 412, 365, 41);
        contentPane.add(btnNewButton_1_3);

        setContentPane(contentPane); // Set the content pane with the background image
    }
}
