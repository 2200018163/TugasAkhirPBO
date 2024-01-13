import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private double angkaPertama, angkaKedua, hasil;
    private int operasi;

    public KalkulatorGUI() {
        // Konfigurasi frame
        setTitle("Kalkulator Sederhana");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(230, 182, 193)); // Warna background pink
        GridBagConstraints gbc = new GridBagConstraints();

        // TextField untuk menampilkan input dan output
        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(textField, gbc);

        // Tombol-tombol angka dan operasi matematika
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "^"
        };

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        int row = 1;
        int col = 0;

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setBackground(new Color(100, 169, 169)); // Warna abu-abu
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            gbc.gridx = col;
            gbc.gridy = row;
            panel.add(button, gbc);

            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]|\\.")) {
            textField.setText(textField.getText() + command);
        } else if (command.matches("[*/\\-+]")) {
            angkaPertama = Double.parseDouble(textField.getText());
            switch (command) {
                case "/":
                    operasi = 4;
                    break;
                case "*":
                    operasi = 3;
                    break;
                case "-":
                    operasi = 2;
                    break;
                case "+":
                    operasi = 1;
                    break;
            }
            textField.setText("");
        } else if (command.equals("=")) {
            angkaKedua = Double.parseDouble(textField.getText());
            switch (operasi) {
                case 1:
                    hasil = angkaPertama + angkaKedua;
                    break;
                case 2:
                    hasil = angkaPertama - angkaKedua;
                    break;
                case 3:
                    hasil = angkaPertama * angkaKedua;
                    break;
                case 4:
                    if (angkaKedua == 0) {
                        textField.setText("Error: Tidak bisa dibagi dengan nol");
                        return;
                    } else {
                        hasil = angkaPertama / angkaKedua;
                    }
                    break;
                case 5:
                    hasil = Math.pow(angkaPertama, angkaKedua);
                    break;
            }
            textField.setText(String.valueOf(hasil));
            operasi = 0; // Reset operasi setelah perhitungan
        } else if (command.equals("C")) {
            textField.setText("");
            angkaPertama = 0;
            angkaKedua = 0;
            operasi = 0;
        } else if (command.equals("^")) {
            angkaPertama = Double.parseDouble(textField.getText());
            operasi = 5;
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KalkulatorGUI());
    }
}
