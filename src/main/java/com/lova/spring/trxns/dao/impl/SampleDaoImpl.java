package com.lova.spring.trxns.dao.impl;

import com.lova.spring.trxns.dao.SampleDAO;
import com.lova.spring.trxns.domain.SampleDomain;
import org.h2.engine.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Lovababu on 5/2/2015.
 */

@Repository
public class SampleDaoImpl implements SampleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Serializable insert(SampleDomain domain) {
        return sessionFactory.getCurrentSession().save(domain);
    }

    @Override
    public SampleDomain get(Serializable id) {
        return (SampleDomain) sessionFactory.getCurrentSession().get(SampleDomain.class, id);
    }
}
