/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.lab2arq.controller;

import com.udea.lab2arq.modelo.EstudianteFacadeLocal;
import javax.ejb.EJB;

/**
 *
 * @author mario.garciat
 */
public class EstudianteBean {
    @EJB
    private EstudianteFacadeLocal estudianteFacade;

    public EstudianteFacadeLocal getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacadeLocal estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }

    
    public EstudianteBean() {
    }
    
}
