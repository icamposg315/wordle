import java.util.Scanner;
import javax.sound.sampled.TargetDataLine;

public class Wordle {

	public static final String GRIS = "\u001B[0m"; // Color Gris
	public static final String AMARILLO = "\u001B[33m"; // Color Amarillo
	public static final String VERDE = "\u001B[32m"; // Color Verde

	public static void main(String[] args) {

		int intentos = 0;
		boolean continuo = false;
		String palabraOculto = "coche";
		String palabraIntento;
		long comienzo = System.currentTimeMillis();

		// Arrays para pasar los strings
		char[] arrayOculta = new char[5];
		char[] arrayRespuesta = new char[5];

		Scanner s = new Scanner(System.in);

		while (!continuo && intentos != 5) {
			intentos++;
			System.out.println("Escribe una palabra de 5 letras");
			palabraIntento = s.nextLine().toLowerCase();

			// CONVIERTO LAS PALABRAS EN MINÚSCULAS

			if (palabraIntento.length() < 5) {
				System.out.println("Palabra demasiado corta");
			} else if (palabraIntento.length() > 5) {
				System.out.println("Palabra demasiado larga");
			} else {
				for (int i = 0; i < 5; i++) {
					arrayOculta[i] = palabraOculto.charAt(i);
					arrayRespuesta[i] = palabraIntento.charAt(i);
				}

				// Pasamos los dos arrays a un método nuevo para compararlos

				if (comparoArrays(arrayRespuesta, arrayOculta)) {
					continuo = true;
				}
			}
		}
		if (intentos == 5) {
			System.out.println("No tienes más intentos. Has perdido");
		} else {
			System.out.println("Genial, encontraste la respuesta en " + ((System.currentTimeMillis() - comienzo) / 1000)
					+ " segundos y en " + intentos + " intentos");
		}
	}

	// Creamos un método llamado "comparoArray" que encapsulará la comprobación de
	// que la palabraIntento comparte alguna letra con la palabraSecreta.
	// Si la letra está en la misma posición, se teñirá de verde, si es acertada
	// pero está en una posición distinta, se tiñe de amarillo,
	// sino coincide, se teñirá de gris. Al método debo pasarle los caracteres
	// (char, letra) de la palabraRespuesta y la palabraOculta.

	public static boolean comparoArrays(char[] arrayRespuesta, char[] palabraOculto) {
		boolean gano = true;
		char[] ocultoTemp = palabraOculto;

		// Creamos un array con números enteros con los colores.

		int[] colorPorLetra = new int[5];

		// Comprobamos si la letra existe y está en la posición correcta (VERDES)
		for (int i = 0; i < 5; i++) {
			if (arrayRespuesta[i] == ocultoTemp[i]) {
				ocultoTemp[i] = '-';
				colorPorLetra[i] = 2;
			} else {
				// Si entro aquí, sigo jugando porque no coincide las palabras
				gano = false;
			}
		}

		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 5; k++) {
				if (arrayRespuesta[j] == ocultoTemp[k] && colorPorLetra[j] != 2) {
					colorPorLetra[j] = 1;
					ocultoTemp[k] = '-';
				}
			}
		}
		// Asigno el color a cada letra del arrayRespuesta, según el array de colores.
		for (int m = 0; m < 5; m++) {
			if (colorPorLetra[m] == 0) {
				System.out.print(arrayRespuesta[m]);
			}
			if (colorPorLetra[m] == 1) {
				System.out.print(AMARILLO + arrayRespuesta[m] + GRIS);
			}
			if (colorPorLetra[m] == 2) {
				System.out.print(VERDE + arrayRespuesta[m] + GRIS);
			}
		}
		// Salto de línea
		System.out.println("");
		return gano;
	}
}
