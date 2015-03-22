package application.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroInicialController extends AbstraticController {
	
	private static Stage stage;
	
	private static CadastroInicialController instance;
	
	@FXML
	private TextField inputNome;
	
	@FXML
	private DatePicker dtNascimento;
	
	public static CadastroInicialController getInstance(){
		
		if(instance == null){
			instance = new CadastroInicialController();
		}
		
		return instance;
	}

	@Override
	public void start(Stage initStage) throws Exception {
		try {
			
			Parent parent = FXMLLoader.load(getClass().getResource(TELA_CADASTRO));
			initStage.setScene(new Scene(parent));
			initStage.setTitle("Tela de Cadastro");
			initStage.show();
			
			setStage(initStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	private void criar() {
			
			try {
				
				PrincipalController.getInstance().start(new Stage());
				CadastroInicialController.stage.hide();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	@FXML
	private void cancelar() {
		
		try {
			
			WelcomeController.getStage().show();
			CadastroInicialController.stage.hide();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		CadastroInicialController.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}