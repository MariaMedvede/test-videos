    package application;

    /*Секретный вопрос
     * экшн для выбора фото
     * добавление фото в бд
     */
    
import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class RegistrController  {

    
	ObservableList<String> quests = FXCollections.observableArrayList("Девичья фамилия матери",
			"Имя первого домашнего питомца", "Любимая улица", "что-то еще");
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField NameField;

    @FXML
    private TextField SurnameField;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private ChoiceBox<String> ChoiceBox = new ChoiceBox<>();

    @FXML
    private DatePicker BirthDate;

    @FXML
    private TextField Answer;

    @FXML
    private TextField Group;

    @FXML
    private TextField email;

    @FXML
    private TextField Phone;
    @FXML
    
    private Text SecretText;  
    @FXML
    private Button ChoosePhotoButt;

    @FXML
    private Text ImagePath;
    // create a function to check if the entered username already exists in the database
    
 
    
    String image_path = null;
    
    public  boolean checkUsername(String username) throws ClassNotFoundException{
        
        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;
        
        String query = "SELECT * FROM `users` WHERE `Login` = ?";
        
        try {
            
            st = DatabaseHandler.getDbConnection().prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();
        
            if(rs.next())
            {
                username_exist = true;
                SecretText.setText("Данный логин уже используется, выберите другой!");
             
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistrController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return username_exist;
    }
 // create a function to verify the empty fields  
    public boolean verifyFields()
    {
    	String name = NameField.getText();
    	String surname = SurnameField.getText();
    	String login = LoginField.getText();
		String password = PasswordField.getText();
		String answer = Answer.getText();
		String group = Group.getText();
		String Email = email.getText();
		String phone = Phone.getText();
        
        // check empty fields
        if(name.trim().equals("") || surname.trim().equals("") || login.trim().equals("")
           || password.trim().equals("") || answer.trim().equals("")
           || group.trim().equals("")|| Email.trim().equals("") || phone.trim().equals(""))
        {
            
            return false;
        }else{
            return true;
        }
    };
    
    //Image chooser
    private void jButton_SelectImageActionPerformed(java.awt.event.ActionEvent evt) {
    	//GEN-FIRST:event_jButton_SelectImageActionPerformed
        // select an image and set the image path into a jlabel
        String path = null;
        
        JFileChooser chooser = new JFileChooser();
        
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        // file extension
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*.Images","jpg","png","jpeg");
        chooser.addChoosableFileFilter(extension);
        
        int filestate = chooser.showSaveDialog(null);
         
        // check if the user select an image
        if(filestate == JFileChooser.APPROVE_OPTION){
            
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            ImagePath.setText(path);
            
            image_path = path;
        }
        
    }
    
 

    
    @FXML
    void initialize() 	{
       DatabaseHandler dbHandler = new DatabaseHandler();
       
       SignUpButton.setOnAction(event->{
        	
        	if(verifyFields())
                {
                    try {
						if(!checkUsername(LoginField.getText()))
						{
     	try {
    		dbHandler.SignUpUser(NameField.getText(), SurnameField.getText(), LoginField.getText(),
							PasswordField.getText(), BirthDate.getValue().toString(), Answer.getText(),
							Group.getText(), email.getText(), Phone.getText()
							 );
    		
    		SecretText.setText("Вы успешно зарегестрировались");
System.out.println("Пользователь зарегестрирован");
} catch (ClassNotFoundException | SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();}
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }else {SecretText.setText("Все поля должны быть заполнены!");}
			
        	
			
										});
                

                    	}   
}

