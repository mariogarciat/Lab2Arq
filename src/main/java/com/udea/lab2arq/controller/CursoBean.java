/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.lab2arq.controller;

import com.udea.lab2arq.modelo.CursoFacadeLocal;
import javax.ejb.EJB;

/**
 *
 * @author mario.garciat
 */
public class CursoBean {
    @EJB
    private CursoFacadeLocal cursoFacade;

    public CursoFacadeLocal getCursoFacade() {
        return cursoFacade;
    }

    public void setCursoFacade(CursoFacadeLocal cursoFacade) {
        this.cursoFacade = cursoFacade;
    }

    
    public CursoBean() {
    }
    
    
}
