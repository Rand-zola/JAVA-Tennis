package tennis;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class JeuTennis extends JPanel{
	private Joueur joueur1, joueur2;   
	private Balle balle; 
	private boolean up1 = false, down1 = false;
	private boolean up2 = false, down2 = false;
	
	// Constructeur pour initialiser le jeu
	public JeuTennis() {
		balle = new Balle(340, 225);        
		joueur2 = new Joueur(0,185);
		joueur1 = new Joueur(665,185);
		
        setBackground(Color.BLACK); 
        
        // Gestion des événements clavier pour déplacer les raquettes
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    up1 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    down1 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_Z) {
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
                if (e.getKeyCode() == KeyEvent.VK_Z) {
                    up2 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    down2 = false;
                }
            }
        });
        setFocusable(true); 
	}
	
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
        if (balle.getX() < 0 || balle.getX() > 680) {
            balle.reset(340, 225); 
        }
	}
	

    //Dessiner les éléments du jeu (raquettes, balle, score)
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        balle.draw(g);
        joueur1.draw(g);
        joueur2.draw(g); 
    
    }
	public static void main(String[] args) {
        JFrame frame = new JFrame("Tennis");
        JeuTennis tennis = new JeuTennis();

        frame.setSize(700, 500);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.add(tennis);
        frame.setVisible(true);
        
        // Boucle principale du jeu
        while (true) {
            tennis.actualiser(); 
            tennis.repaint(); 
            try {
                Thread.sleep(10); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
	

}
