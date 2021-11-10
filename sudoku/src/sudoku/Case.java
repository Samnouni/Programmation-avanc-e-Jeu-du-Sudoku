package sudoku;


/***
 * Classe Case, représente une case du Sudoku
 * @author Samnouni, Oufaska
 */

public class Case {
	/***
	 * entier représente la valeur de la case
	 */
	public int num;
	/***
	 * un boolean représente l'état de la case
	 */
	public boolean fixe;	
	/***
	 * Constructeur par défaut pour créer une case vide et non fixe
	 */
	public Case() {
		num = 0;
		fixe = false;
	}
	/***
	 * Constructeur pour créer une case 
	 * dont la valeur est n et non fixe
	 */
	public Case(int n) {
		num = n;
	    fixe = false;
	}
	/***
	 * Constructeur pour créer une case dont la valeur 
	 * est n et dont l'état est f
	 */
	public Case(int n, boolean f) {
		num = n;
		fixe = f;
	}
	/***
	 * Constructeur pour creer une case vide et dont l'état est f 
	 */
	public Case(boolean f) {
		num = 0;
		fixe = f;
	}
	/***
	 * Accesseur à la valeur num de la case
	 */
	public int getNum() {
		return num;
	}
	/***
	 * Modifieur de la valeur num de la case
	 */ 
	public void setNum(int n) {
		num = n;
	}
	/***
	 * Accesseur à l'état fixe de la case
	 */
	public boolean getFixe() {
		return fixe;
	}
	/***
	 * Modifieur de l'état fixe de la case 
	 */
	public void setFixe(boolean f) {
		fixe = f;
	}
	/***
	 * Affichage d'une case 
	 */
	@Override
	public String toString() {
		return "Case [num=" + num + ", fixe=" + fixe + "]";
	}
	
	
	
	
	
}
 
