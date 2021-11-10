package principal;

import sudoku.Case;
import sudoku.Grille;
import sudoku.Jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;



/***
 * Classe Sudoku, qui gère toutes les opirations et le mode graphique
 * @author Samnouni, Oufaska
 */
public class Sudoku extends JFrame implements ActionListener {
	/***
	 * Objet de la classe Jeu, represente une grille de Sudoku
	 */
	Jeu jeu;
	/***
	 * Fichier courant sur lequel l'utilisateur joue
	 */
	File fichierCourant;
	Container c;
	JPanel panel, panelGeneral;
	JPanel[][] jp = new JPanel[3][3];
	/***
	 * Boutons représentant les cases
	 */
	JButton cases[][] = new JButton[9][9];
	GridLayout grille;
	JMenu menuFichier;
	JMenuBar menu;
	JMenuItem  nouveau, nouveauAlea, resoudre,nouveauAlea_1,nouveauAlea_2;
 
	/***
	 * Constructeur par défaut, cree la grille, puis 
	 * cree une fenetre en y plaçant tous les elements, et l'affiche
	 */
	public Sudoku() {
		//on cree la fenetre
		super("Sudoku M2-ISN");
		//on cree le jeu
		jeu = new Jeu();
		//on definit la terminaison du programme lorsqu'on ferme la fenetre
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//definit la taille de la fenetre 
		setSize(650,650);
		//On ajoute la barre des menus
		menu = new JMenuBar();
		menuFichier = new JMenu("Fichier");
		nouveau = new JMenuItem("Nouvelle grille");
		nouveauAlea_2 = new JMenuItem("Nouvelle grille aleatoire (facile)");
		nouveauAlea_1 = new JMenuItem("Nouvelle grille aleatoire (moyen)");
		nouveauAlea = new JMenuItem("Nouvelle grille aleatoire (difficile)");
		resoudre = new JMenuItem("Resoudre ce Sudoku");

		//on ajoute les elements au menu
		menuFichier.add(nouveau);
		menuFichier.addSeparator();
		menuFichier.add(nouveauAlea);
		menuFichier.addSeparator();
		menuFichier.add(nouveauAlea_1);
		menuFichier.addSeparator();
		menuFichier.add(nouveauAlea_2);
		menuFichier.addSeparator();
		menuFichier.add(resoudre);
		menu.add(menuFichier);
		nouveau.addActionListener(this);
		nouveauAlea.addActionListener(this);
		nouveauAlea_1.addActionListener(this);
		nouveauAlea_2.addActionListener(this);
		resoudre.addActionListener(this);
		setJMenuBar(menu);
		//on cree panel avec une GridLayout 
		//puis on ajoute les boutons
		c = getContentPane();
		panel = new JPanel();
		grille = new GridLayout(3,3);
		panel.setLayout(grille);
		panelGeneral = new JPanel();
		panelGeneral.setLayout(new BorderLayout());
		panelGeneral.add(panel, BorderLayout.CENTER);
		//on cree les panels qui representeront les 9 grilles de 3x3 cases
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				jp[i][j] = new JPanel();
				(jp[i][j]).setLayout(new GridLayout(3,3));
				(jp[i][j]).setBorder(BorderFactory.createEtchedBorder());
			}
		}
		//on cree les boutons et on les ajoute aux panels
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				Integer in = new Integer((jeu.getCaseNum(i,j)));
				if(in == 0) 
					cases[i][j] = new JButton("");
				else 
					cases[i][j] = new JButton(in.toString());
				
			    if (jeu.getCaseFixe(i,j))
					(cases[i][j]).setFont(new java.awt.Font("Helvetica",
							java.awt.Font.BOLD, 25)); 
				else (cases[i][j]).setFont(new java.awt.Font("Helvetica",
						java.awt.Font.PLAIN, 25));
				(cases[i][j]).setSize(10,10);
				(jp[(int)(i/3)][(int)(j/3)]).add(cases[i][j]);
				panel.add(jp[(int)(i/3)][(int)(j/3)]);
				//on ajoute des ecouteurs aux boutons
				(cases[i][j]).addActionListener(this);
			}
		}
		c.add(panelGeneral);
		//on affiche la fenetre
		show();
	}
	/***
	 * Affiche un nombre fixe en gras
	 */
	public void CaseFixe(int i,int j) {
		if(jeu.getCaseFixe(i,j)) {
			(cases[i][j]).setFont(new java.awt.Font("Helvetica",
			java.awt.Font.BOLD, 25));
		}
	}
	
	
	/***
	 * Initialiser la couleur des cases
	 */
	public void ColorerCases() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
					
		(cases[i][j]).setForeground(Color.black);
		if (jeu.getCaseFixe(i,j))
			(cases[i][j]).setFont(new java.awt.Font("Helvetica",
					java.awt.Font.BOLD, 25)); 
		else (cases[i][j]).setFont(new java.awt.Font("Helvetica",
				java.awt.Font.PLAIN, 25));
			}}}
	/***
	 * Colorer la case fausse
	 */
	public void ColorerCaseFausse(int i,int j, int val ) {
		
				if ( (jeu.getGrilleDeCase(i,j).GrilleValide(val))==false || (jeu.ligneValide(i,val))==false || (jeu.colonneValide(j,val))==false )			
		           (cases[i][j]).setForeground(Color.red);
		        else
			       (cases[i][j]).setForeground(Color.black);		
		
	}
 	
	/***
	 *  colorer les grilles complètes 
	 *  apres verification 
	 */
	public void Verifier(int i, int j) {
		
		
		this.ColorerGrille(i,j);
		this.CaseFixe(i,j);
		if (jeu.gagne())
			JOptionPane.showMessageDialog(this, "Vous avez gagne !", "Felicitations", JOptionPane.PLAIN_MESSAGE);
	
	}
	/***
	 * Colorer la grille si elle contient tous les chiffres de 1 a 9
	 */
	public void ColorerGrille(int i, int j) {
		if( (jeu.getGrilleDeCase(i,j)).GrilleComplete() )
			(jp[(int)(i/3)][(int)(j/3)]).setBorder(BorderFactory.createLineBorder(Color.green));
		else
			(jp[(int)(i/3)][(int)(j/3)]).setBorder(BorderFactory.createEtchedBorder());
	}
	
	
	
	/***
	 * Incrementer le chiffre d'une case quand on appuie dessus
	 */
	public void IncrementerCase(int i, int j) {
		if(!jeu.getCaseFixe(i,j)) {
			if(jeu.getCaseNum(i,j) < 9) {
				//colorer la case pour une valeur fausse
				this.ColorerCaseFausse(i,j,jeu.getCaseNum(i,j) + 1 );
				jeu.setCaseNum(i,j,jeu.getCaseNum(i,j) + 1);
			}
			else
				jeu.setCaseNum(i, j, 0);
			Integer in = new Integer(jeu.getCaseNum(i,j));
			if (in == 0) 
				(cases[i][j]).setText("");
			else 
				(cases[i][j]).setText(in.toString());
		}
	}
	/***
	 * Créer un nouveau jeu vide
	 */
	public void nouveauJeu() {
		Jeu tmp = new Jeu();
		jeu = tmp;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				(cases[i][j]).setText("");
			}
		}
		//creer nouvelle fenetre
		fichierCourant = null;
    }
	/*** 
	 * Créer une nouvelle grille aleatoire
	 * pour une difficulté k
	 */
	
	public void NouveauJeuAlea(int k) {
		jeu.RemplissageAleatoire(k);
		for(int i=0;i<9;i++) { 
			for(int j=0;j<9;j++) {
				Integer in = new Integer((jeu.getCaseNum(i,j)));
				if(in == 0) 
					(cases[i][j]).setText("");
				else 
					(cases[i][j]).setText(in.toString());
				this.CaseFixe(i,j);
			}
		}
		
	}
	
	
	
	/***
	 * Resoudre le Sudoku
	 */
	public void resoudre() {
		
		if(jeu.resoudre(0,0)) {
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					Integer in = new Integer((jeu.getCaseNum(i,j)));
					(cases[i][j]).setText(in.toString());
					this.CaseFixe(i,j);
				}
			}
		}
		else JOptionPane.showMessageDialog(this,"Impossible de resoudre ce Sudoku!","Impossible", JOptionPane.ERROR_MESSAGE);
	}
	/*** 
	 * Gere les actions des boutons et des menus
	 */
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				//on clique sur un bouton de la grille et on l'incremente
				if(e.getSource() == cases[i][j]) {
					this.IncrementerCase(i, j);
					this.Verifier(i, j);
					
				}
			}
		}
		if(e.getSource() == nouveau)
			{this.nouveauJeu();
		    this.ColorerCases();}
		
		if(e.getSource() == nouveauAlea)
		{   this.nouveauJeu();
		    this.ColorerCases();
			this.NouveauJeuAlea(10);
			
		}
		if(e.getSource() == nouveauAlea_1)
		{   this.nouveauJeu();
		    this.ColorerCases();
			this.NouveauJeuAlea(20);
		}
		if(e.getSource() == nouveauAlea_2)
		{   this.nouveauJeu();
		    this.ColorerCases();
			this.NouveauJeuAlea(30);
		}
		if(e.getSource() == resoudre)
			{this.resoudre();
		    this.ColorerCases();}
	}

	
	
}
