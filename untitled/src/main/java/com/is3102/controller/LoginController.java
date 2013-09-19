package com.is3102.controller;

import com.is3102.util.DateUtility;
import com.is3102.util.SHA;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private String username;

    private String password;

    private Logger logger;

    /**
     * Default constructor
     */
    public LoginController(){
        System.out.println("logincontroller");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Manage login
     *
     *
     * */
    public void login(ActionEvent actionEvent) {

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String redirect = "";
        String navigateString = "";
        String value = "24215";
        SHA sha = new SHA();
        System.out.println(sha.convert(value));
        try {
            // Checks if username and password are valid if not throws a ServletException
            System.out.println(username);
            System.out.println(password);
            request.login(username, password);
            // gets the user principle and navigates to the appropriate page
            Principal principal = request.getUserPrincipal();

            if (request.isUserInRole("Administrator")) {
                navigateString = "/admin/index.xhtml";
            } else if (request.isUserInRole("Doctor")) {
                navigateString = "/doctor/index.xhtml";
            }
        } catch (ServletException e) {
            System.out.println("The username or password you provided does not match our records.");
            System.out.println(e.toString());
        }
        System.out.println(navigateString);
        try {
           context.getExternalContext().redirect(request.getContextPath() + navigateString);
            System.out.println(request.getContextPath()+ navigateString);
        } catch (IOException ex) {
           context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
        }
    }

    public void logout(ActionEvent actionEvent){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("User log out " + request.getUserPrincipal().getName());
        //logger.log(Level.INFO, "#User ({" + request.getUserPrincipal().getName() +  "}) loging out #");
        if (session != null) {
            session.invalidate();
        }
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml?faces-redirect=true");
        try {
            context.getExternalContext().redirect(request.getContextPath() + "/index.xhtml");
            System.out.println(request.getContextPath() + "/index.xhtml");
        } catch (IOException ex) {
            context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
        }
    }
}

