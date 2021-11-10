package sudoku;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/***
 * Classe Jeu représente le Sudoku 
 * @author Samnouni, Oufaska 
 */
public class Jeu {
	/***
	 *  Table du jeu de 3x3 Grille  
	 */
	public Grille[][] jeu;
	/***
	 * Constructeur par défaut pour construire une grille vide
	 */
	public Jeu() {
		jeu = new Grille[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				jeu[i][j] = new Grille();
			}
		}
	}
	/***
	 * Accesseur qui retourne la grille de 
	 * coordonnées i,j dans la table du jeu
	 */
	public Grille getGrille(int i, int j) {
		return jeu[i][j];
	}
	/***
	 * Accesseur qui retourne la grille dans laquelle 
	 * se trouve la case de coordonnées i,j dans la table du jeu
	 */
	public Grille getGrilleDeCase(int i, int j) {
		return (jeu[(int)(i/3)][(int)(j/3)]);
	}
	/***
	 * Modifieur de la table du jeu
	 */
	public void setGrille(Grille[][] r) {
		jeu = r;
	}
	/**
	*Accesseur qui retourne la valeur de la case 
	*de coordonnées i,j dans la table du jeu
	*/
	public int getCaseNum(int i, int j) {
		return (jeu[(int)(i/3)][(int)(j/3)]).getCaseNum(i%3,j%3);
	}
	/***
	 * Modifieur de la valeur num de la case 
	 * de coordonnées i,j dans la grille
	 */
	public void setCaseNum(int i, int j, int c) {
		(jeu[(int)(i/3)][(int)(j/3)]).setCaseNum(i%3,j%3,c);
	}
	/***
	 * Accesseur qui retourne l'état de la case 
	 * de coordonnées i,j dans la table du jeu
	 */
	public boolean getCaseFixe(int i, int j) {
		return (jeu[(int)(i/3)][(int)(j/3)]).getCaseFixe(i%3,j%3);
	}
	/***
	 * Modifieur de la valeur fixe de la case 
	 * de coordonnées i,j dans la grille
	 */
	public void setCaseFixe(int i, int j, boolean f) {
		(jeu[(int)(i/3)][(int)(j/3)]).setCaseFixe(i%3,j%3,f);
	}

	/***
	 * Vérifié si une ligne contient tous les chiffres 
	 * de 1 a 9 une fois, retourne vrai ou faux 
	 */
	public boolean ligneComplete(int i) {
		boolean un = false;
		boolean deux = false;
		boolean trois = false;
		boolean quatre = false;
		boolean cinq = false;
		boolean six = false;
		boolean sept = false;
		boolean huit = false;
		boolean neuf = false;
		for(int j=0;j<9;j++) {
			switch(this.getCaseNum(i,j)) {
			case 1 : un = true; 
			break;
			case 2 : deux = true; 
			break;
			case 3 : trois = true; 
			break;
			case 4 : quatre = true; 
			break;
			case 5 : cinq = true;
			break;
			case 6 : six = true; 
			break;
			case 7 : sept = true;
			break;
			case 8 : huit = true;
			break;
			case 9 : neuf = true;
			break;
			}
		}
		if(un && deux && trois && quatre && cinq && six && sept && huit && neuf)
			return true;
		else 
			return false;
	}

	/***
	 * Verifié si la ligne en cours de création 
	 * serait valide avec une valeur val,retourne vrai ou faux
	 */
	public boolean ligneValide(int i, int val) {
		if(val == 0)
			return false;
		for(int k=0;k<9;k++) {
			if(val == this.getCaseNum(i,k))
				return false;
		}
		return true;
	}
	/***
	 * Vérifié si la colonne contient tous
	 * les chiffres de 1 a 9 une fois, retourne vrai ou faux
	 */
	public boolean colonneComplete(int j) {
		boolean un = false;
		boolean deux = false;
		boolean trois = false;
		boolean quatre = false;
		boolean cinq = false;
		boolean six = false;
		boolean sept = false;
		boolean huit = false;
		boolean neuf = false;
		for(int i=0;i<9;i++) {
			switch(this.getCaseNum(i,j)) {
			case 1 : un = true;
			break;
			case 2 : deux = true;
			break;
			case 3 : trois = true;
			break;
			case 4 : quatre = true;
			break;
			case 5 : cinq = true;
			break;
			case 6 : six = true;
			break;
			case 7 : sept = true;
			break;
			case 8 : huit = true;
			break;
			case 9 : neuf = true;
			break;
			}
		}
		if(un && deux && trois && quatre && cinq && six && sept && huit && neuf)
			return true;
		else 
			return false;
	}
	/***
	 * Vérifié si la colonne en cours de création 
	 * serait valide avec une valeur val, retourne vrai ou faux
	 */
	public boolean colonneValide( int j, int val) {
		if(val == 0)
			return false;
		for(int k=0;k<9;k++) {
			if(val == this.getCaseNum(k,j))
				return false;
		}
		return true;
	}

	/*** 
	 * Verifié si le joueur a gagné, retourne vrai ou faux
	 */
	public boolean gagne() {
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++)
				if(!(jeu[i][j]).GrilleComplete())
					return false;
		for (int i=0;i<9;i++)
			if(!colonneComplete(i) || !ligneComplete(i))
				return false;
		return true;
	}
	/***
	 * Remplire une grille aléatoirement 
	 */
	public void RemplissageAleatoire(int s) {
		Random rand = new Random();
		
		//remplire 13 cases aléatoirement 
		int v;
		int i;
		int j;
		int k=0;
		while( k<13) {
					i = rand.nextInt(9);
					j = rand.nextInt(9);
					v = rand.nextInt(10);
					if ( (this.getGrilleDeCase(i,j).GrilleValide(v)) && (this.ligneValide(i,v)) && (this.colonneValide(j,v)) ) {
						this.setCaseNum(i,j,v);
						
						if(v != 0) { 
							this.setCaseFixe(i,j,true);
							k++;
						}
					}
		}
		// résoudre le sudoku et cacher les 81-s cases
		if(this.resoudre(0,0)) {
			for(int p=0;p<9;p++) {
				for(int q=0;q<9;q++) {this.setCaseFixe(p,q,true);}}
			k=0;
			while( k<81-s) {
				i = rand.nextInt(9);
				j = rand.nextInt(9);
				if(this.getCaseNum(i,j)!=0) {
				this.setCaseNum(i,j,0);
				this.setCaseFixe(i,j,false);
						k++;}
					}
				}

		
	} 
				
		 
								
	
	
	/*** 
	 * Résolution du sudoku d'après la grille donnée
	 */
	public boolean resoudre(int i, int j) {
		//quand on arrive au bout de la ligne on va à la ligne suivante
		if (j == 9) {
			j = 0;
			if (++i == 9)
				return true;
		}
		//si la case est fixe, on regarde la suivante
		if (this.getCaseFixe(i,j))
			return resoudre(i,j+1);
		//on regarde si on peut placer une valeur, si oui on passe a la case
		//suivante, si non on retourne en arrière
		for (int val = 1; val <= 9; ++val) {
			if ( (this.getGrilleDeCase(i,j).GrilleValide(val)) && (this.ligneValide(i,val)) && (this.colonneValide(j,val)) ) {
				this.setCaseNum(i,j,val);
				if (resoudre(i,j+1))
					return true;
			}
		}
		setCaseNum(i,j,0);
		System.out.println(this);
		return false;

	
	}
	/*** 
	 * Affichage d'une grille de sudoku dans la console
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("-------------------------\n");
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(j == 0) sb.append("| ");
				sb.append(this.getCaseNum(i,j)+" ");
				if( (j == 2) || (j == 5) || (j == 8) ) 
					sb.append("| ");
			}
			if( (i == 2) || (i == 5) )
				sb.append("\n|-----------------------|");
			if(i == 8) sb.append("\n-------------------------");
			sb.append("\n");
		}
		return sb.toString();
	}
	

}