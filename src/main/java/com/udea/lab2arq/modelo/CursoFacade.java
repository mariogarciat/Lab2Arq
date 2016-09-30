/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.lab2arq.modelo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mario.garciat
 */
@Stateless
public class CursoFacade extends AbstractFacade<Curso> implements CursoFacadeLocal {
    @PersistenceContext(unitName = "persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }
    
  @Override
  public List<Curso> findAllByLevel(int level) {
    return em.createNamedQuery("Materia.findByNivel", Curso.class)
            .setParameter("nivel", level)
            .getResultList();
  }
    
}
