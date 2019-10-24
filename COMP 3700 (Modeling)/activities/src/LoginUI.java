import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LoginUI {
    public JFrame view;

    public JButton btnLogin = new JButton("Login");
    public JButton btnLogout = new JButton("Logout");

    public JTextField txtUsername = new JTextField(20);
    public JTextField txtPassword = new JPasswordField(20);

    Socket link;
    Scanner input;
    PrintWriter output;

    int accessToken;

    public LoginUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Login");
        view.setSize(600, 400);

        Container pane = view.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel();
        line.add(new JLabel("Username"));
        line.add(txtUsername);
        pane.add(line);

        line = new JPanel();
        line.add(new JLabel("Password"));
        line.add(txtPassword);
        pane.add(line);

        pane.add(btnLogin);
        pane.add(btnLogout);

        btnLogin.addActionListener(new LoginActionListener());

        btnLogout.addActionListener(new LogoutActionListener());

    }

    public static void main(String[] args) {
        int port = 1025;
        LoginUI ui = new LoginUI();
        ui.view.setVisible(true);

    }

    private class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                link = new Socket("localhost", 1000);
                input = new Scanner(link.getInputStream());
                output = new PrintWriter(link.getOutputStream(), true);

                output.println("LOGOUT");
                output.println(accessToken);
                int res = input.nextInt();
                System.out.println("Sent LOGOUT " + accessToken + " received " + res);

                if (res == 0)
                    JOptionPane.showMessageDialog(null, "Invalid token for logout!");
                else
                    JOptionPane.showMessageDialog(null, "Logout successfully = ");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            if (username.length() == 0 || password.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username or password cannot be null!");
                return;
            }
            try {
                try {
                    link = new Socket("localhost", 1025);
                    input = new Scanner(link.getInputStream());
                    output = new PrintWriter(link.getOutputStream(), true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                output.println("LOGIN");
                output.println(username);
                output.println(password);
                accessToken = input.nextInt();
                System.out.println("Sent " + username + "/" + password + " received " + accessToken);

                if (accessToken == 0)
                    JOptionPane.showMessageDialog(null, "Invalid username or password! Access denied!");
                else
                    JOptionPane.showMessageDialog(null, "Access granted with access token = " + accessToken);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
