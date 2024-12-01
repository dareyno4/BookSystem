//login.java: manages login, forgot password, create account
//author: shahnawaz syed

package application;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;

public class Login {
    private Path credentialsPath;
	private Runnable onLoginSuccess;

    public Login(String filePath, Runnable onLoginSuccess) {
        String cwd = System.getProperty("user.dir");
        credentialsPath = Paths.get(cwd, "src", "application", filePath);
        this.onLoginSuccess = onLoginSuccess;
    }

    public AnchorPane LoginPane() {
        AnchorPane loginPane = new AnchorPane();

        TextField usernameField = new TextField();
        
        usernameField.setPromptText("Enter Username");
        AnchorPane.setTopAnchor(usernameField, 100.0);
        AnchorPane.setLeftAnchor(usernameField, 100.0);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        AnchorPane.setTopAnchor(passwordField, 150.0);
        AnchorPane.setLeftAnchor(passwordField, 100.0);

        Button loginButton = new Button("Login");
        AnchorPane.setTopAnchor(loginButton, 200.0);
        AnchorPane.setLeftAnchor(loginButton, 100.0);

        Button createAccountButton = new Button("Create Account");
        AnchorPane.setTopAnchor(createAccountButton, 200.0);
        AnchorPane.setLeftAnchor(createAccountButton, 200.0);

        Button forgotPasswordButton = new Button("Forgot Password");
        AnchorPane.setTopAnchor(forgotPasswordButton, 250.0);
        
        
        AnchorPane.setLeftAnchor(forgotPasswordButton, 100.0);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            
            String password = passwordField.getText();
            if (checkLogin(username, password)) {
                alert(AlertType.INFORMATION, "Success", "Login successful!");
                onLoginSuccess.run();
            } else {
                alert(AlertType.ERROR, "Error", "Invalid credentials");
            }
        });

        createAccountButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()) { //user must enter a username and password to log in
                alert(AlertType.ERROR, "Error", "Username and password cannot be empty");
                return;
            }
            
            if (usernameExists(username)) { //can't create an account with a username that already exists
                alert(AlertType.ERROR, "Error", "Username already exists!");
            }
            else {
            	TextInputDialog securityDialog = new TextInputDialog();
            	//security question so user can reset their password they forgot it 
                securityDialog.setTitle("Security Question");
                securityDialog.setHeaderText("Please answer security question:");
                securityDialog.setContentText("What is your mother's maiden name?");
                
                securityDialog.showAndWait().ifPresent(answer -> {
                    try {
						if (createAccount(username, password, answer)) { //create account method
							    alert(AlertType.INFORMATION, "Success", "Account created successfully!");
							    usernameField.clear();
							    passwordField.clear();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                });
            }
            
        });

        forgotPasswordButton.setOnAction(e -> { //forgot password
            TextInputDialog usernameDialog = new TextInputDialog();
            usernameDialog.setTitle("Forgot Password");
            usernameDialog.setHeaderText("Enter username");
            usernameDialog.setContentText("Username:");

            usernameDialog.showAndWait().ifPresent(username -> { //given the username, will change the corresponding password in .csv
                if (username.isEmpty()) { //username field cannot be empty
                    alert(AlertType.ERROR, "Error", "Please enter username");
                    return;
                }

                TextInputDialog securityDialog = new TextInputDialog();
                securityDialog.setTitle("Security Question");
                securityDialog.setHeaderText("Please answer security question:");
                securityDialog.setContentText("What is your mother's maiden name?"); //verifies identity with security question

                securityDialog.showAndWait().ifPresent(answer -> {
                    if (answerCheck(username, answer)) { //if security answer is correct
                        enterPwd enterPwd = new enterPwd();
                        enterPwd.showAndWait().ifPresent(newPassword -> {
                            if (updatePassword(username, newPassword)) { //change the password
                                
                            	alert(AlertType.INFORMATION, "Success", "Password updated successfully!");
                            } else {
                                alert(AlertType.ERROR, "Error", "Failed to update password");
                            }});
                    } else {
                        alert(AlertType.ERROR, "Error", "Incorrect security answer");
                    }});
            });
        });

        loginPane.getChildren().addAll(usernameField, passwordField, loginButton, createAccountButton, forgotPasswordButton);
        return loginPane;
    }

    private boolean checkLogin(String uname, String password) { //method to verify the entered username and password is correct
        try {
            return Files.lines(credentialsPath) //returns true if uname are correct
                .map(line -> line.split(","))
                .filter(credentials -> credentials.length >= 2) //verifies that there are at least the uname and password in the line in .csv file
                .anyMatch(credentials -> //verify match
                    credentials[0].trim().equals(uname) && 
                    credentials[1].trim().equals(password));
        } catch (IOException e) {
            System.out.println("Error reading credentials: " + e.getMessage());
            return false;
        }
    }

    private boolean createAccount(String username, String pwd, String answer) throws IOException { //method to create the account with given username and password
        Files.write( //write uname and password to the .csv file
            credentialsPath,
            (username + "," + pwd + "," + answer + System.lineSeparator()).getBytes(),
            StandardOpenOption.APPEND //makes sure we append to the .csv file instead of overwriting
        );
        return true;
    }

    private boolean usernameExists(String username) { //checks if uname exists
        try {
            return Files.lines(credentialsPath)
                .map(line -> line.split(","))
                .filter(credentials -> credentials.length == 3)
                .anyMatch(credentials -> credentials[0].trim().equals(username));
        } catch (IOException error) {
            System.out.println("Error checking username: " + error.getMessage());
            error.printStackTrace();
            return false;
        }
    }

    private boolean answerCheck(String uname, String answer) { //verifies security answer is correct
        try {
            return Files.lines(credentialsPath)
                .map(line -> line.split(","))
                .filter(credentials -> credentials.length == 3)
                .filter(credentials -> credentials[0].trim().equals(uname))
                .anyMatch(credentials -> credentials[2].trim().toLowerCase().equals(answer.toLowerCase()));
        } catch (IOException error) {
            System.out.println("Error checking security answer: " + error.getMessage());
            error.printStackTrace();
            return false;
        }
    }

    private boolean updatePassword(String username, String newPassword) { //uses list to iterate thru and update the password for the given username
        try {
            List<String> lines = Files.readAllLines(credentialsPath);
            List<String> newLines = lines.stream()
                .map(line -> {
                    String[] parts = line.split(",");
                    if (parts.length == 3 && parts[0].trim().equals(username)) {
                        return parts[0] + "," + newPassword + "," + parts[2];
                    }
                    return line;
                })
                .collect(Collectors.toList());
            Files.write(credentialsPath, newLines); //update the .csv file
            return true;
        } catch (IOException e) {
            System.out.println("Error updating password: " + e.getMessage());
            return false;
        }
    }

    private void alert(AlertType type, String title, String content) { //relevant messages to the user
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private class enterPwd extends Dialog<String> {
        private PasswordField passwordField;

        public enterPwd() {
            setTitle("New Password");
            setHeaderText("Enter your new password");

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

            passwordField = new PasswordField();
            passwordField.setPromptText("New password");

            getDialogPane().setContent(passwordField);

            setResultConverter(dialogButton -> {
                if (dialogButton == okButton) {
                    return passwordField.getText();
                }
                return null;
            });
        }
    }
}