package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoExcepion;

public class CampoTest {

	private Campo campo; 
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}
	
	@Test
	void testeVizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1Direita() {
		Campo vizinho = new Campo(3,4);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1EmCima() {
		Campo vizinho = new Campo(2,3);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1EmBaixo() {
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());;
	}
	
	@Test
	void testAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());;
	}
	
	@Test
	void testAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());;
	}
	
	@Test
	void testAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());;
	}
	
	@Test
	void testAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());;
	}
	
	@Test
	void testAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());;
	}
	
	@Test
	void testAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoExcepion.class,() -> {
			campo.abrir();
		});
	}
	
	@Test
	void testAbrirComVizinhos1() {
		
		Campo campo11 = new Campo(1, 1);
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinhos(campo11);
		
		campo.adicionarVizinhos(campo22);
		
		campo.abrir();	
		
		assertTrue(campo22.isAberto() && campo11.isAberto());;
	}
	@Test
	void testAbrirComVizinhos2() {
		
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		campo12.minar(); 
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinhos(campo11);
		campo22.adicionarVizinhos(campo12);
		
		campo.adicionarVizinhos(campo22);
		
		campo.abrir();	
		
		assertTrue(campo22.isAberto() && campo11.isFechado());;
	}
}

