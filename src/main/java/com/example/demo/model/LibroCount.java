package com.example.demo.model;

import java.util.Calendar;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class LibroCount {

    @PersistenceContext
    private EntityManager entityManager;

    public Long contarLibrosUltimos6Meses() {
        Calendar fechaHoy = Calendar.getInstance();
        fechaHoy.add(Calendar.MONTH, -6);

        return (Long) entityManager.createQuery(
                "SELECT COUNT(l) FROM Libro l WHERE l.fechaPubli >= :fecha")
                .setParameter("fecha", fechaHoy.getTime())
                .getSingleResult();
    }
}
