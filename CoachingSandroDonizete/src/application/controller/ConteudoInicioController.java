package application.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.bo.FactoryBO;
import application.enumeration.ResoursesEnum;
import application.enumeration.TelaEnum;
import application.model.ConsultaCoachee;
import application.model.TableViewCoacheesAtivos;
import application.navigation.ConteudoNavigator;

public class ConteudoInicioController extends AbstraticController{
	
	private static Stage stage;
	
	private ObservableList<ConsultaCoachee> listaCoacheesAtivos;
	
	@FXML
	private AnchorPane anchorPaneConteudoInicioPrincipal;
	
	@FXML
	private ScrollPane scrollPaneConteudoInicio;
	
	@FXML
	private Hyperlink quantidadeCoacheeAtivos;
	
	@FXML
	private TableView<TableViewCoacheesAtivos> tabelaCoacheesAtivos;
	
	@FXML
    private TableColumn<TableViewCoacheesAtivos, String> nomeCoacheeColuna;
	
	@FXML
    private TableColumn<TableViewCoacheesAtivos, String> ultimaSessaoColuna;
	
	@FXML
    private TableColumn<TableViewCoacheesAtivos, String> numeroSessaoColuna;
	
	@Override
	public void initialize(URL url, ResourceBundle resourses) {
		
		ajustarScrollComTamanhoResolucaoTela(scrollPaneConteudoInicio);
		
		nomeCoacheeColuna.setCellValueFactory(cellData  -> cellData.getValue().getNomeCoachee());
		ultimaSessaoColuna.setCellValueFactory(cellData -> cellData.getValue().getUltimaSessao());
		numeroSessaoColuna.setCellValueFactory(cellData -> cellData.getValue().getNumeroSessao());
		
		tabelaCoacheesAtivos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tabelaCoacheesAtivos.setItems(recuperarTodasConsultasCoacheeAtivos());
        tabelaCoacheesAtivos.setPlaceholder(new Label("N�o existe Coachee Cadastrado"));
        
        quantidadeCoacheeAtivos.setText(String.valueOf(listaCoacheesAtivos.size()));
		
	}
	
	private ObservableList<TableViewCoacheesAtivos> recuperarTodasConsultasCoacheeAtivos(){
		
		ObservableList<TableViewCoacheesAtivos> listaTabelaConsultaCoacheesAtivos = FXCollections.observableArrayList();
		
		listaCoacheesAtivos = FactoryBO.getInicioBOInstance().recuperarTodasConsultasCoachee();
		
		for (ConsultaCoachee consultaCoachee : listaCoacheesAtivos) {
			
			listaTabelaConsultaCoacheesAtivos.add(new TableViewCoacheesAtivos(consultaCoachee.getCoachee().getNomeCoachee(),consultaCoachee.getDataUltimaSessao(),consultaCoachee.getNumeroUltimaSessao()));
			
		}
		
		return listaTabelaConsultaCoacheesAtivos;
	}
	
	@Override
	public void start(Stage initStage) throws Exception {
		try {
			
			Parent parent = FXMLLoader.load(PreLoaderController.class.getResource(TelaEnum.TELA_PRINCIPAL.getCaminhoFxml()));
			initStage.setScene(new Scene(parent));
			initStage.show();
			
			setStage(initStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void cadastroNovoCoachee() {

		try {
			PrincipalController.getBtnStaticInicio().setStyle("-fx-background-color: #e4e4e4;-fx-border-color:gray");
			PrincipalController.getBtnStaticCadastroNovoCoachee().setStyle("-fx-background-color: red;-fx-border-color:gray");
			consultaCoacheeApplication = null;
			
			ConteudoNavigator.carregarCena(TelaEnum.CONTEUDO_CADASTRO_COACHEE.getCaminhoFxml());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void carregarCoacheeSelecionado() {

		try {
			
			for (ConsultaCoachee consultaCoachee : listaCoacheesAtivos) {
				
				if(consultaCoachee.getCoachee().getNomeCoachee().equals(tabelaCoacheesAtivos.getSelectionModel().getSelectedItem().getNomeCoachee().getValue())){
					consultaCoacheeApplication = consultaCoachee;
					break;
				}
			}
			
			ConteudoNavigator.carregarCena(TelaEnum.CONTEUDO_CADASTRO_COACHEE.getCaminhoFxml());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void OpenWebSite(MouseEvent event) {
		try {
			java.awt.Desktop.getDesktop().browse(new URI(ResoursesEnum.LINK_WEB_SITE.getRecurso()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void OpenFacebook(MouseEvent event) {
		try {
			java.awt.Desktop.getDesktop().browse(new URI(ResoursesEnum.LINK_FACEBOOK.getRecurso()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void OpenYouTube(MouseEvent event) {
		try {
			java.awt.Desktop.getDesktop().browse(new URI(ResoursesEnum.LINK_YOUTUBE.getRecurso()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void AbrirGoogleMaps(MouseEvent event) {
		try {
			java.awt.Desktop.getDesktop().browse(new URI(ResoursesEnum.LINK_MAPS.getRecurso()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		ConteudoInicioController.stage = stage;
	}

}
