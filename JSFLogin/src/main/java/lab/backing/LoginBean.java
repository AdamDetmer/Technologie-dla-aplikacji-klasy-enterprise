package lab.backing;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.component.html.HtmlCommandButton;
import jakarta.faces.component.html.HtmlSelectBooleanCheckbox;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.context.FacesContext; // Punkt 27

@Named
@RequestScoped
public class LoginBean {
    private String username;
    private String password;
    private HtmlSelectBooleanCheckbox acceptCheckbox;
    private HtmlCommandButton loginButton;

    public String login() {
        if (username != null && username.equals(password)) {
            return "success";
        } else {
            return "failure";
        }
    }

    // Punkt 27: Metoda obsługi zmiany checkboxa
    public void activateButton(ValueChangeEvent e) {
        if (Boolean.TRUE.equals(e.getNewValue())) {
            loginButton.setDisabled(false);
        } else {
            loginButton.setDisabled(true);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.renderResponse(); // Pominięcie faz walidacji pozostałych pól
    }

    // Gettery i Settery (pozostają bez zmian)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public HtmlSelectBooleanCheckbox getAcceptCheckbox() { return acceptCheckbox; }
    public void setAcceptCheckbox(HtmlSelectBooleanCheckbox acceptCheckbox) { this.acceptCheckbox = acceptCheckbox; }
    public HtmlCommandButton getLoginButton() { return loginButton; }
    public void setLoginButton(HtmlCommandButton loginButton) { this.loginButton = loginButton; }
}