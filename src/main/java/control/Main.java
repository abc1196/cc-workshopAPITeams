package control;

import java.io.IOException;
import modelo.EquipoService;

public class Main {

	public static void main(String[] args) {

		try {
			new EquipoController(new EquipoService());

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}

}
