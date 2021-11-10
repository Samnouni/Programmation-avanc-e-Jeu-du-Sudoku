package sudoku;

import java.util.Arrays;

/*** 
 * Classe Grille représente une grille 3*3 cases du Sudoku
 * @author Samnouni, Oufaska 
 */

public class Grille {
	/***
	 * Grille de 3x3 Cases
	 */
	public Case[][] _Grille;
	/***
	 * Constructeur par défaut pour construire 
	 * une grille de 3*3 cases avec des cases vides
	 */
	public Grille() {
		_Grille = new Case[3][3];
	    for(int i=0;i<3;i++) {
	    	for(int j=0;j<3;j++) {
	    		_Grille[i][j] = new Case();
	    	}
	    }
	}
	/***
	 * Accesseur de la grille, retourne la case 
	 * de coordonnées i,j dans la grille
	 */
	public Case getCase(int i, int j) {
		return _Grille[i][j];
	}
	/***
	 * Modifieur de la grille
	 */
	public void setCase(int i, int j, Case c) {
		_Grille[i][j].setNum(c.getNum());
	}
	/***
	 * Accesseur de la grille, retoune la valeur de 
	 * la case de coordonnées i,j dans la grille 
	 */
	public int getCaseNum(int i, int j) {
		return _Grille[i][j].getNum();
	}
	/***
	 * Modifieur de la grille 
	 */
	public void setCaseNum(int i, int j, int c) {
		_Grille[i][j].setNum(c);
	}
	/***
	 * Accesseur à la valeur fixe, retourne l'état 
	 * de la case de coordonnées i,j dans la grille
	 */
	public boolean getCaseFixe(int i, int j) {
		return _Grille[i][j].getFixe();
	}
	/***
	 * Modifieur de la veleur fixe
	 */
	public void setCaseFixe(int i, int j, boolean f) {
		_Grille[i][j].setFixe(f);
	}
	/***
	 * Verifié si la grille contient des chiffres 
	 * de 1 a 9 une fois, retourne vrai ou faux
	 */
	public boolean GrilleComplete() {
		boolean un = false;
		boolean deux = false;
		boolean trois = false;
		boolean quatre = false;
		boolean cinq = false;
		boolean six = false;
		boolean sept = false;
		boolean huit = false;
		boolean neuf = false;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				switch(_Grille[i][j].getNum()) {
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
		}
		if(un && deux && trois && quatre && cinq && six && sept && huit && neuf)
			return true;
		else
			return false;
	}

	/***
	 * Verifié si la grille en cours de création 
	 * serait validé avec la valeur val, return vrai ou faux
	 */
	public boolean GrilleValide(int val) {
		if(val == 0)
			return false;
		for(int k=0;k<3;k++) {
			for(int l=0;l<3;l++) {
				if(this.getCaseNum(k,l) == val)
					return false;
			}
		}
		return true;
	}
	/***
	 * Affichage d'une grille
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.println(_Grille[i][j]+" ");
			}
			System.out.println("\n");
		}
		System.out.println("\n");
		return sb.toString();
	}

	
}
