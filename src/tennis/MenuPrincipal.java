package tennis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPrincipal extends JPanel {

    private JButton boutonDemarrer;
    private JButton boutonQuitter;
    private JTextField nomJoueur1;
    private JTextField nomJoueur2;

    public MenuPrincipal(JFrame frame) {
        setLayout(null);
        setBackground(new Color(40, 128, 40));

        // Champ pour le nom du Joueur 1
        JLabel labelJoueur1 = new JLabel("Nom Joueur 1:");
        labelJoueur1.setBounds(250, 100, 200, 30);
        labelJoueur1.setFont(new Font("Arial", Font.PLAIN, 20));
        add(labelJoueur1);

        nomJoueur1 = new JTextField();
        nomJoueur1.setBounds(250, 130, 200, 30);
        nomJoueur1.setFont(new Font("Arial", Font.PLAIN, 20));
        add(nomJoueur1);

        // Champ pour le nom du Joueur 2
        JLabel labelJoueur2 = new JLabel("Nom Joueur 2:");
        labelJoueur2.setBounds(250, 170, 200, 30);
        labelJoueur2.setFont(new Font("Arial", Font.PLAIN, 20));
        add(labelJoueur2);

        nomJoueur2 = new JTextField();
        nomJoueur2.setBounds(250, 200, 200, 30);
        nomJoueur2.setFont(new Font("Arial", Font.PLAIN, 20));
        add(nomJoueur2);

        // Bouton "Démarrer"
        boutonDemarrer = new JButton("Démarrer le jeu");
        boutonDemarrer.setBounds(250, 250, 200, 50);
        boutonDemarrer.setFont(new Font("Arial", Font.PLAIN, 20));
        boutonDemarrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String joueur1Nom = nomJoueur1.getText().trim();
                String joueur2Nom = nomJoueur2.getText().trim();

                // Vérifier que les deux noms ont été saisis
                if (joueur1Nom.isEmpty() || joueur2Nom.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Veuillez saisir les noms des deux joueurs.");
                } else {
                    frame.remove(MenuPrincipal.this);
                    JeuTennis tennis = new JeuTennis(joueur1Nom, joueur2Nom);
                    frame.add(tennis);
                    frame.revalidate();
                    frame.repaint();
                    tennis.requestFocusInWindow();  
                }
            }
        });

        // Bouton "Quitter"
        boutonQuitter = new JButton("Quitter");
        boutonQuitter.setBounds(250, 320, 200, 50);
        boutonQuitter.setFont(new Font("Arial", Font.PLAIN, 20));
        boutonQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(boutonDemarrer);
        add(boutonQuitter);
    }

    public static void afficherMenu(JFrame frame) {
        MenuPrincipal menu = new MenuPrincipal(frame);
        frame.add(menu);
        frame.revalidate();
        frame.repaint();
    }
}
