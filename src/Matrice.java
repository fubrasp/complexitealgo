public class Matrice{
	public int n,m;	
	public int mat[][];
	private boolean error;

	/**
	 * Constructeur Matrice vide
	 */
	public Matrice(){

	}

	/**
	 * Constructeur Matrice n*m (int*int)
	 */
	public Matrice(int a, int b){
		n = a;
		m = b;
		mat = new int[n][m];
		initialiser();
	}

	/**
	 * Constructeur Matrice n*m + valeurs (args)
	 */
	public Matrice(String[] args){
		n = Integer.parseInt(args[0]);
		m = Integer.parseInt(args[1]);
		mat = new int[n][m];
		if(args.length == (n*m)+2){
			int k = 0;
			int i = 0;
			while(i != n){
				int j = 0;
				while(j != m){
					mat[i][j] = Integer.parseInt(args[k+2]);
					k++;					
					j++;
				}
				i++;
			}
		}
		else{ // si le nombre de parametre n'est pas egal a la taille de la matrice on remplit de 0
			initialiser();
		}
	}

	/**
	 * Initialise la matrice avec des 0
	 */
	public void initialiser(){
		int i = 0;
		while(i != n){
			int j = 0;
			while(j != m){
				mat[i][j] = 0;
				j++;
			}
			i++;
		}
	}


	/**
	 * Affiche la matrice
	 */
	public void afficher(){
		int i = 0;
		while(i != n){
			int j = 0;
			while(j != m){
				System.out.print(mat[i][j]+"\t");
				j++;
			}
			System.out.println("");
			i++;
		}
	}
	

	/**
	 * Calcul le determinant de la matrice si elle est carr��e.
	 */
	public int determinant(){
		if(estCarree()){
			return determinant(this);
		}
		else{
			error = true;
			return 0;
		}
	}


	/**
	 * renvoi le determinant d'une matrice
	 */
	public int determinant(Matrice matrice){
		if(matrice.n == 2 && matrice.m == 2){
			return (matrice.mat[0][0] * matrice.mat[1][1] - matrice.mat[0][1] * matrice.mat[1][0]);
		}
		else{
			int det = 0;
			int i = 0;
			while(i != matrice.n){
				String[] args = new String[2+((matrice.n-1)*(matrice.m-1))];
				args[0] = ""+(matrice.n-1);
				args[1] = ""+(matrice.m-1);
				int j = 0;
				int l = 2;
				while(j != matrice.n){
					int k = 1;
					while(k != matrice.m){
						if(j != i){
							args[l] = ""+matrice.mat[j][k];
							l++;
						}
						k++;
					}
					j++;
				}
				Matrice newMatrice = new Matrice(args);
				if(i % 2 != 0){
					det += -1*matrice.mat[i][0]*determinant(newMatrice);
				}
				else{
					det += matrice.mat[i][0]*determinant(newMatrice);
				}
				i++;
			}
			return det;
		}
	}

	/**
	 * Calcul la trace de la matrice si elle est carr��e.
	 */
	public int trace(){
		if(estCarree()){
			int tra = 0;
			int i = 0;
			while(i != n){
				tra += mat[i][i];
				i++;
			}
			return tra;
		}
		else{
			error = true;
			return 0;
		}
	}


	/**
	 * Renvoi vraisi la matrice est carr��e n == m
	 */
	public boolean estCarree(){
		return n == m;
	}


	/**
	 * Calcul la transposer de la matrice
	 */
	public Matrice transposer(){
		String[] args = new String[2+(n*m)];
		args[0] = ""+(m);
		args[1] = ""+(n);
		int j = 0;
		int l = 2;
		while(j != m){
			int i = 0;
			while(i != n){
				args[l] = ""+mat[i][j];
				l++;
				i++;
			}
			j++;
		}
		return new Matrice(args);
	}

	/**
	 * Calcul la transposer de la matrice
	 */
	public Matrice produit(Matrice matrice){
		if(n == matrice.m){
			String[] args = new String[2+(n*matrice.m)];
			args[0] = ""+(n);
			args[1] = ""+(matrice.m);
			int i = 0;
			int a = 2;
			while(i != n){
				int j = 0;
				while(j != matrice.m){
					int b = 0;
					int k = 0;
					while(k != m){
						b += mat[i][k]*matrice.mat[k][j];
						k++;
					}
					args[a] = ""+b;
					a++;
					j++;
				}
				i++;
			}
			return new Matrice(args);
		}
		else{
			error = true;
			return new Matrice();
		}
	}


	/**
	 * Main
	 * Exemple : java Matrice 3 3 1 2 3 4 5 6 7 8 9
	 *	     Matrice 3 * 3 avec pour valeur 1 2 3
	 *					    4 5 6
	 *					    7 8 9
	 */
	public static void main(String args[]){
		String[] mat = new String[]{"3","3","1","1","1","2","4","1","1","-2","1"};
		Matrice matrice = new Matrice(mat);
		
		System.out.println("La matrice est : ");
		matrice.afficher();
	
		// determinant
		matrice.error = false;
		int a = matrice.determinant();
		if(matrice.error){
			System.out.println("Determinant : La matrice n'est pas carr��e!!!");
		}
		else{
			System.out.println("Determinant : "+a);
		}

		// trace
		matrice.error = false;
		a = matrice.trace();
		if(matrice.error){
			System.out.println("Trace : La matrice n'est pas carrée!!!");
		}
		else{
			System.out.println("Trace : "+a);
		}

		// Transposer
		Matrice tmatrice = matrice.transposer();
		System.out.println("La transposer de la matrice est : ");
		tmatrice.afficher();
	}

}