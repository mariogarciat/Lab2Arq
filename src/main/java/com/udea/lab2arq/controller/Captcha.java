/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.lab2arq.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author mario.garciat
 */
public class Captcha {

    /**
     * Creates a new instance of Captcha
     */
    public Captcha() {
    }
    
    public void check(ActionEvent e) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Su código es correcto", null);
  
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
