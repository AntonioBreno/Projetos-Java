package fxml;

import org.controlsfx.control.Notifications;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControlador {

	@FXML
	private TextField email;
	@FXML
	private PasswordField senha;
	
		public void entrar(){
			boolean emailValido = email.getText().equals("aluno@123");
			boolean senhaValida = senha.getText().equals("12345678");
			
			if(emailValido && senhaValida) {
				Notifications.create()
						.position(Pos.TOP_RIGHT)
						.title("Login FXML")
						.text("Login efetuado com sucesso!")
						.showInformation();
			}else {
				Notifications.create()
						.position(Pos.TOP_RIGHT)
						.title("Login FXML")
						.text("Usuario ou senha inválido!")
						.showError();
			}
		}
}
