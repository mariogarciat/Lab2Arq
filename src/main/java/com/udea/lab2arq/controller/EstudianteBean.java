/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.lab2arq.controller;

import com.udea.lab2arq.modelo.Curso;
import com.udea.lab2arq.modelo.CursoFacadeLocal;
import com.udea.lab2arq.modelo.Estudiante;
import com.udea.lab2arq.modelo.EstudianteFacadeLocal;
import static com.udea.lab2arq.modelo.Estudiante_.cursoCollection;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author mario.garciat
 */
public class EstudianteBean implements Serializable{

    @EJB
    private EstudianteFacadeLocal estudianteFacade;
    @EJB
    private CursoFacadeLocal cursoFacade;
    private String id;
    private String name;
    private String lastName;
    private String level;
    private String email;
    private List cursos;
    
    private UIComponent find;
    private UIComponent save;
    
        // Lista con las materias x nivel

    

    public EstudianteFacadeLocal getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacadeLocal estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }

    public EstudianteBean() {
    }


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
    
    public void search(){
              
        setCursos(this.getCursoFacade().findAllByLevel(Integer.parseInt(level)));
        int i;
        for(i=0;i<=getCursos().size();i++)
        {
            System.out.println(getCursos().get(i));
        }
                
        /* materias = this.materiaFacade.findAllByLevel(nivel);
        
     
        // Consultar todas las materias actuales del estudiante
        materias_actuales = this.estudiantepormateriaFacade.findAllByStudent(cedula);
        materias_seleccionadas = new int[materias_actuales.size()];
        int i;
        for (i = 0; i < materias_actuales.size(); i++) {
            materias_seleccionadas[i] = ((Estudiantepormateria) materias_actuales.get(i)).getEstudiantepormateriaPK().getCodigo();
        }*/
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

    /**
     * @return the cursoFacade
     */
    public CursoFacadeLocal getCursoFacade() {
        return cursoFacade;
    }

    /**
     * @param cursoFacade the cursoFacade to set
     */
    public void setCursoFacade(CursoFacadeLocal cursoFacade) {
        this.cursoFacade = cursoFacade;
    }

    /**
     * @return the cursos
     */
    public List getCursos() {
        return cursos;
    }

    /**
     * @param cursos the cursos to set
     */
    public void setCursos(List cursos) {
        this.cursos = cursos;
    }

}
