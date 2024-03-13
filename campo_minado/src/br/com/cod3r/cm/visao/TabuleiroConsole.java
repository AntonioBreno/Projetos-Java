package br.com.cod3r.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cod3r.cm.excecao.ExplosaoExcepion;
import br.com.cod3r.cm.excecao.SairException;
import br.com.cod3r.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner input = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n) ");
				String resposta = input.nextLine();
				
				if("n".equals(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
			
		}catch (SairException e ) {
			System.out.println("Tchau!!!");
		} finally {
			input.close();
		}
	}

	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capurarValorDigitado("Digite (L, C): " ); 
				
				Iterator<Integer> xy = 
						Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim()))
						.iterator();
				
				digitado = capurarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if("2".equals(digitado)) {
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
			}
			
			System.out.println(tabuleiro);
			System.out.println("Voce ganhou!!!");
		} catch (ExplosaoExcepion e) {
			System.out.println(tabuleiro);
			System.out.println("Voce perdeu!!!");
			
		}
	}
	
	private String capurarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = input.nextLine();
		
		if("Sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
}
