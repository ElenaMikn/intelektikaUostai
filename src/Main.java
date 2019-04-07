import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SpringLayout;

public class Main {

    static JTextField textfield_k_n, textfield_k_s, textfield_n_k, textfield_n_s, textfield_s_n, textfield_s_k, textfield_galia;
    static JTextArea  textfield_rez;
    public static void main(String[] args) {


        JFrame f = new JFrame("Laivų maršrutai");
        f.getContentPane().setLayout(new FlowLayout());


        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        JLabel label_k_n = new JLabel();
        JLabel label_k_s = new JLabel();
        JLabel label_n_k = new JLabel();
        JLabel label_n_s = new JLabel();
        JLabel label_s_k = new JLabel();
        JLabel label_s_n = new JLabel();
        JLabel label_galia = new JLabel();

        label_k_n.setText("Klaipeda Niujorkas:");
        label_k_s.setText("Klaipeda Sidniejus:");
        label_n_k.setText("Niujorkas Klaipeda:");
        label_n_s.setText("Niujorkas Sidniejus:");
        label_s_k.setText("Sidniejus Klaipeda:");
        label_s_n.setText("Sidniejus Niujorkas:");
        label_galia.setText("Krovinio maksimalus svoris");

        label_k_n.setBounds(10, 10, 150, 30);
        label_k_s.setBounds(180, 10, 150, 30);
        label_n_k.setBounds(10, 50, 150, 30);
        label_n_s.setBounds(180, 50, 150, 30);
        label_s_k.setBounds(10, 90, 150, 30);
        label_s_n.setBounds(180, 90, 150, 30);
        label_galia.setBounds(10, 130, 170, 30);

        //label.setLocation(10,10);

        textfield_k_n = new JTextField("3",3);
        textfield_k_s = new JTextField("1",3);
        textfield_n_k = new JTextField("2",3);
        textfield_n_s = new JTextField("4",3);
        textfield_s_n = new JTextField("3",3);
        textfield_s_k = new JTextField("0",3);
        textfield_galia = new JTextField("5",3);

        textfield_rez = new JTextArea(5,100);
        textfield_rez.setLineWrap(true);
        textfield_rez.setWrapStyleWord(true);

        textfield_k_n.setBounds(130,10,40,30);
        textfield_k_s.setBounds(300,10,40,30);
        textfield_n_k.setBounds(130,50,40,30);
        textfield_n_s.setBounds(300,50,40,30);
        textfield_s_k.setBounds(130,90,40,30);
        textfield_s_n.setBounds(300,90,40,30);
        textfield_galia.setBounds(170,130,40,30);
        textfield_rez.setBounds(10,200,400,200);

        contentPane.add(label_k_n);
        contentPane.add(label_k_s);
        contentPane.add(label_n_k);
        contentPane.add(label_n_s);
        contentPane.add(label_s_k);
        contentPane.add(label_s_n);
        contentPane.add(label_galia);

        label_k_n.setLabelFor(textfield_k_n);
        label_k_s.setLabelFor(textfield_k_s);
        label_n_k.setLabelFor(textfield_n_k);
        label_n_s.setLabelFor(textfield_n_s);
        label_s_k.setLabelFor(textfield_s_k);
        label_s_n.setLabelFor(textfield_s_n);
        label_galia.setLabelFor(textfield_galia);

        contentPane.add(textfield_k_n);
        contentPane.add(textfield_k_s);
        contentPane.add(textfield_n_k);
        contentPane.add(textfield_n_s);
        contentPane.add(textfield_s_k);
        contentPane.add(textfield_s_n);
        contentPane.add(textfield_galia);
        contentPane.add(textfield_rez);

        JButton button = new JButton("Skaiciuoti");
        button.setSize(100, 30);
        button.setLocation(95, 170);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textfield_rez.selectAll();
                textfield_rez.replaceSelection("");
                int galia=  Integer.parseInt(textfield_galia.getText());

                State s=new State(3,galia,0,2147483647);
                s.mas[0][0]=0;//---
                s.mas[0][1]=Integer.parseInt(textfield_k_n.getText());
                s.mas[0][2]=Integer.parseInt(textfield_k_s.getText());
                s.mas[1][0]=Integer.parseInt(textfield_n_k.getText());
                s.mas[1][1]=0;//---
                s.mas[1][2]=Integer.parseInt(textfield_n_s.getText());
                s.mas[2][0]=Integer.parseInt(textfield_s_k.getText());
                s.mas[2][1]=Integer.parseInt(textfield_s_n.getText());
                s.mas[2][2]=0;//---

                List<Shipments> tempShipmentsList  = s.GetPath();
                String rez="";

                for (Shipments st: tempShipmentsList) {
                    if(st!=null) {
                        rez = st.port == 0 ? "I Klaipedos " : st.port == 1 ? "I Niujorko    " : "I Sidniejaus";
                        rez += " - [" + st.cargo[0] + " - " + st.cargo[1] + " - " + st.cargo[2] + "]\n";
                        textfield_rez.append(rez);
                    }
                }
            }
        });

        contentPane.add(button);

        f.setContentPane(contentPane);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setSize(400, 500);
        f.setVisible(true);

    }


}
//
