import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import net.proteanit.sql.DbUtils;

public class patient_history extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    patient_history frame = new patient_history();
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
    public patient_history() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBounds(450, 10, 753, 382);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Full History Of Patient");
        lblNewLabel_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_1.setForeground(new Color(255, 255, 128));

        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(190, 11, 483, 60);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Enter patient ID");
        lblNewLabel_2.setForeground(new Color(255, 0, 0));
        lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 23));
        lblNewLabel_2.setBounds(74, 82, 254, 35);
        contentPane.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(274, 82, 277, 35);
        contentPane.add(textField);
        textField.setColumns(10);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.BOLD, 14));
        table.setBounds(21, 145, 533, 187);
        // table.set
        table.setBackground(Color.WHITE);
        contentPane.add(table);

        JButton btnNewButton = new JButton("Exit");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO Exit?", "Select",
                        JOptionPane.YES_NO_OPTION);
                if (a == 0)
                    setVisible(false);
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBounds(564, 275, 166, 35);
        contentPane.add(btnNewButton);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = textField.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root",
                            "Arpit@0502");

                    PreparedStatement pst = con.prepareStatement("SELECT id FROM patients WHERE id=?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    pst.setString(1, id);
                    ResultSet ss = pst.executeQuery();

                    if (ss.first()) {
                        PreparedStatement psst = con.prepareStatement(
                                "SELECT p.id as id,p.name as NAME,p.age AS AGE,p.problem AS PROBLEM,u.symptom AS SYMPTOM,u.diagnosis AS DIAGONOSIS,u.medicine AS MEDICINE FROM patients P JOIN update_record u USING(id) WHERE id=?",
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        psst.setString(1, id);
                        ResultSet rs = psst.executeQuery();

                        // Using DefaultTableModel to set data to the JTable
                        DefaultTableModel model = new DefaultTableModel();
                        ResultSetMetaData metaData = rs.getMetaData();
                        int columnCount = metaData.getColumnCount();

                        // Add column names to the model
                        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                            model.addColumn(metaData.getColumnLabel(columnIndex));
                        }

                        // Add data to the model
                        while (rs.next()) {
                            Object[] rowData = new Object[columnCount];
                            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                                rowData[columnIndex - 1] = rs.getObject(columnIndex);
                            }
                            model.addRow(rowData);
                        }

                        // Set the model to the table
                        table.setModel(model);
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter Correct Id!!!");
                    }

                    con.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Connection Problem");
                }
            }
        });

        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSearch.setBounds(561, 82, 166, 35);
        contentPane.add(btnSearch);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 753, 382);
        contentPane.add(lblNewLabel);
    }
}