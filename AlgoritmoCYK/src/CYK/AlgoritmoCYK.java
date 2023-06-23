package CYK;

import java.util.Scanner;

public class AlgoritmoCYK {
        
	    
	    public static boolean cyk(String input) {
	        int n = input.length();
	        
	        // Construindo a tabela CYK
	        boolean[][][] table = new boolean[n][n][2];
	        
	        // Inicializando a diagonal da tabela com a produção inicial
	        for (int i = 0; i < n; i++) {
	            if (input.charAt(i) == 'a') {
	                table[i][i][0] = true;
	            } else if (input.charAt(i) == 'b') {
	                table[i][i][1] = true;
	            } else {
	                return false; // Se encontrarmos uma letra diferente de 'a' ou 'b', a sequência não pertence à linguagem
	            }
	        }
	        
	        // Preenchendo a tabela de forma bottom-up
	        for (int len = 2; len <= n; len++) {
	            for (int i = 0; i <= n - len; i++) {
	                int j = i + len - 1;
	                for (int k = i; k < j; k++) {
	                    for (int p = 0; p < 2; p++) {
	                        for (int q = 0; q < 2; q++) {
	                            if (table[i][k][p] && table[k+1][j][q]) {
	                                table[i][j][0] = true;
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        
	        // Verificando se a produção inicial está presente na célula superior direita da tabela
	        return table[0][n-1][0];
	    }
	    
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Digite uma sequência de 'a's e 'b's: ");
	        String input = scanner.nextLine();
	        
	        int conteA = 0;
	        int conteB = 0;
	        boolean estaBalanceada = true;
	        boolean encontrouB = false;
	        
	        // Contando o número de 'a's e 'b's na sequência e verificando se estão balanceados
	        for (int i = 0; i < input.length(); i++) {
	            if (input.charAt(i) == 'a') {
	                conteA++;
	                if (encontrouB) {
	                    estaBalanceada = false;
	                    break;
	                }
	            } else if (input.charAt(i) == 'b') {
	                conteB++;
	                encontrouB = true;
	            } else {
	                System.out.println("A sequência contém letras diferentes de 'a' e 'b'.");
	                return;
	            }
	        }
	        
	        if (input.isEmpty()) {
	            System.out.println("A sequência pertence à linguagem L = {a^n b^n | n >= 0}");
	        } else {
	            boolean pertenceALinguagem = cyk(input);
	            
	            // Verificando se a sequência pertence à linguagem L = {a^n b^n | n >= 0}
	            if (pertenceALinguagem && conteA == conteB && estaBalanceada) {
	                System.out.println("A sequência pertence à linguagem L = {a^n b^n | n >= 0}");
	            } else {
	                System.out.println("A sequência não pertence à linguagem L = {a^n b^n | n >= 0}");
	            }
	        }
	    }
	}
