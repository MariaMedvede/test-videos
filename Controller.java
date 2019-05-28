package application;

	import java.io.IOException;
import java.net.URL;
	import java.util.ResourceBundle;
	import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
	import javafx.scene.control.TextField;
import javafx.stage.Stage;

	public class Controller {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField LoginField;

	    @FXML
	    private TextField PasswordField;

	    @FXML
	    private Button enterButton;

	    @FXML
	    private Button SingUpButton;

	    @FXML
	    void initialize() {
	        enterButton.setOnAction(event -> {
	        	String loginText = LoginField.getText().trim();
	        	String loginPassword = PasswordField.getText().trim();
	        	
	        	if(!loginText.equals("")&&!loginPassword.equals(""))
	        	{
	        		loginUser(loginText, loginPassword);
	        	}
	        	else
	        		System.out.println("Login and password is empty");
	        });
	    	SingUpButton.setOnAction(event -> {
	    		SingUpButton.getScene().getWindow().hide();
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(getClass().getResource("/application/registration.fxml"));
	    		try {
					loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		Parent root = loader.getRoot();
	    		Stage stage = new Stage();
	    		stage.setScene(new Scene(root));
	    		stage.showAndWait();
	    	});

	    }

		private void loginUser(String loginText, String loginPassword) {
			// TODO Auto-generated method stub
			
		}
	}

