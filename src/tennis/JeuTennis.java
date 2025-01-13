package tennis;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class JeuTennis extends JPanel implements Runnable {
    private Joueur joueur1, joueur2;
    private Balle balle;
    private boolean up1 = false, down1 = false;
    private boolean up2 = false, down2 = false;

    private int score1 = 0, score2 = 0;
    private Thread gameThread;
    
    private String nomJoueur1, nomJoueur2;  

    // Constructeur modifié pour accepter les noms des joueurs
    public JeuTennis(String joueur1Nom, String joueur2Nom) {
        balle = new Balle(340 - 10, 225 - 10);  
        joueur2 = new Joueur(20, 200);  
        joueur1 = new Joueur(660, 200);  

        nomJoueur1 = joueur1Nom;
        nomJoueur2 = joueur2Nom;

        setBackground(new Color(40, 128, 40)); 

        // Déplacement des raquettes
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    up1 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    down1 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    up2 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    down2 = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    up1 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    down1 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    up2 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    down2 = false;
                }
            }
        });
        setFocusable(true);

        // Démarrer animation
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            actualiser();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Mmettre à jour l'état du jeu
    public void actualiser() {
        balle.mouvement();
        balle.collision(joueur1);
        balle.collision(joueur2);

        if (up1) {
            joueur1.moveUp();
        }
        if (down1) {
            joueur1.moveDown();
        }
        if (up2) {
            joueur2.moveUp();
        }
        if (down2) {
            joueur2.moveDown();
        }

        // Marque un point, remet la balle au centre
        if (balle.getX() < 0) {
            score2++;
            balle.reset(340 - 10, 225 - 10);  
        }
        if (balle.getX() > 680) {
            score1++;
            balle.reset(340 - 10, 225 - 10);  
        }
    }

    // Dessiner les éléments du jeu 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        dessinerTerrain(g);

        balle.draw(g);
        joueur1.draw(g);
        joueur2.draw(g);

        // Afficher le score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString(nomJoueur2 + ": " + score1, 50, 30);
        g.drawString(nomJoueur1 + ": " + score2, 470, 30);
    }

    // Dessiner le terrain de tennis
    private void dessinerTerrain(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));

        g2d.drawLine(20, 50, 680, 50);  
        g2d.drawLine(20, 450, 680, 450);  

        g2d.drawLine(20, 50, 20, 450);  
        g2d.drawLine(680, 50, 680, 450);

        g2d.drawLine(340, 50, 340, 450);  

        g2d.drawLine(160, 50, 160, 450);  
        g2d.drawLine(520, 50, 520, 450);  
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tennis");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Afficher le menu principal
        MenuPrincipal.afficherMenu(frame);

        frame.setVisible(true);

        setWindowIcon(frame);
    }

    private static void setWindowIcon(JFrame frame) {
        try {
            // Charger logo
            Image logo = ImageIO.read(new File("resources/Logo.jpg"));
            Image resizedLogo = logo.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            frame.setIconImage(resizedLogo);
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du logo : " + e.getMessage());
        }
    }
}
