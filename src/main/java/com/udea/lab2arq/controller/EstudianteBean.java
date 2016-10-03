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
import com.udea.lab2arq.modelo.Matricula;
import com.udea.lab2arq.modelo.MatriculaFacadeLocal;
import com.udea.lab2arq.modelo.MatriculaPK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author mario.garciat
 */
public class EstudianteBean implements Serializable {

    @EJB
    private EstudianteFacadeLocal estudianteFacade;
    @EJB
    private CursoFacadeLocal cursoFacade;
    @EJB
    private MatriculaFacadeLocal matriculaFacade;
    private String id;
    private String name;
    private String lastName;
    private String level;
    private String email;
    private List cursos;         // Lista con las materias x nivel
    private List cursos_m;       //Cursos matriculados
    private List cursos_a;       //Cursos actuales
    private String[] cursos_seleccionados;

    private UIComponent find;
    private UIComponent save;

    private boolean disableCursos = true;
    private boolean disableDatos = false;

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

        Estudiante est = this.estudianteFacade.find(id);

        if (est == null) {
            est = new Estudiante();
            est.setDocumento(id);
            est.setNombre(name);
            est.setApellido(lastName);
            est.setNivel(level);
            est.setEmail(email);
            this.estudianteFacade.create(est);
        }

        int i;

        Matricula em;

        for (i = 0; i < cursos_seleccionados.length; i++) {
            em = new Matricula();

            em.setMatriculaPK(new MatriculaPK(est.getDocumento(), cursos_seleccionados[i]));

            this.getMatriculaFacade().create(em);
        }

        // Consultar todas las materias actuales del estudiante
        cursos_a = this.getMatriculaFacade().findAllByStudent(est.getDocumento());

        cursos_m = new ArrayList<Curso>();
        for (i = 0; i < cursos_a.size(); i++) {
            String c = ((Matricula) cursos_a.get(i)).getMatriculaPK().getIdcurso();
            cursos_m.add(getCursoByCode(c));
        }

    }
//se comparan los códigos para encontrar la materia correspondiente

    private Curso getCursoByCode(String codigo) {
        int l = cursos.size();
        Curso m = null;
        for (int i = 0; i < l; i++) {
            m = (Curso) cursos.get(i);
            if (codigo.equals(m.getCodigo())) {
                return m;
            }
        }
        return m; //si llega acá es porque no existe una materia con ese código y se retorna null
    }

    public void search() {

        cursos = this.cursoFacade.findAllByLevel(level);
        cursos_a = this.getMatriculaFacade().findAllByStudent(id);
        disableDatos = true;
        disableCursos = false; //disable validar

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

    /**
     * @return the disableCursos
     */
    public boolean isDisableCursos() {
        return disableCursos;
    }

    /**
     * @param disableCursos the disableCursos to set
     */
    public void setDisableCursos(boolean disableCursos) {
        this.disableCursos = disableCursos;
    }

    /**
     * @return the cursos_seleccionados
     */
    public String[] getCursos_seleccionados() {
        return cursos_seleccionados;
    }

    /**
     * @param cursos_seleccionados the cursos_seleccionados to set
     */
    public void setCursos_seleccionados(String[] cursos_seleccionados) {
        this.cursos_seleccionados = cursos_seleccionados;
    }

    /**
     * @return the cursos_m
     */
    public List getCursos_m() {
        return cursos_m;
    }

    /**
     * @param cursos_m the cursos_m to set
     */
    public void setCursos_m(List cursos_m) {
        this.cursos_m = cursos_m;
    }

    /**
     * @return the cursos_a
     */
    public List getCursos_a() {
        return cursos_a;
    }

    /**
     * @param cursos_a the cursos_a to set
     */
    public void setCursos_a(List cursos_a) {
        this.cursos_a = cursos_a;
    }

    /**
     * @return the matriculaFacade
     */
    public MatriculaFacadeLocal getMatriculaFacade() {
        return matriculaFacade;
    }

    /**
     * @param matriculaFacade the matriculaFacade to set
     */
    public void setMatriculaFacade(MatriculaFacadeLocal matriculaFacade) {
        this.matriculaFacade = matriculaFacade;
    }

    /**
     * @return the disableDatos
     */
    public boolean isDisableDatos() {
        return disableDatos;
    }

    /**
     * @param disableDatos the disableDatos to set
     */
    public void setDisableDatos(boolean disableDatos) {
        this.disableDatos = disableDatos;
    }
    
    public String generarInfoQR(){
        String info = "";
        info = info + id + "\n";
        info = info + name + "\n";
        info = info + lastName + "\n";
        info = info + level + "\n";
        info = info + email + "\n";
        for (Object curso : cursos_m) {
            Curso aux = (Curso)curso;
            info = info + aux.getNombre() + "\n";
        }
        
        return info;
    }

}
