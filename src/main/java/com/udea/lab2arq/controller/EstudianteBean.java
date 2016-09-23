/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.lab2arq.controller;

import com.udea.lab2arq.modelo.Estudiante;
import com.udea.lab2arq.modelo.EstudianteFacadeLocal;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author mario.garciat
 */
public class EstudianteBean {

    @EJB
    private EstudianteFacadeLocal estudianteFacade;
    private String id;
    private String name;
    private String lastName;
    private String level;
    private String email;

    public EstudianteFacadeLocal getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacadeLocal estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }

    public EstudianteBean() {
    }

    private UIComponent find;
    private UIComponent save;

    public UIComponent getFind() {
        return find;
    }

    public void setFind(UIComponent find) {
        this.find = find;
    }

    public UIComponent getSave() {
        return save;
    }

    public void setSave(UIComponent save) {
        this.save = save;
    }

    public void matricular() {
        Estudiante est = new Estudiante();
        est.setDocumento(id);
        est.setNombre(name);
        est.setApellido(lastName);
        est.setNivel(level);
        est.setEmail(email);
        this.estudianteFacade.create(est);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    private Locale locale = FacesContext.getCurrentInstance()
            .getViewRoot().getLocale();
    public Locale getLocale() {
        return locale;
    }
    public String getLanguage() {
        return locale.getLanguage();
    }
    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
