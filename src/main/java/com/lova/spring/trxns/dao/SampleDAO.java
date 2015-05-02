package com.lova.spring.trxns.dao;

import com.lova.spring.trxns.domain.SampleDomain;
import org.hibernate.SessionFactory;

import java.io.Serializable;

/**
 * Created by Lovababu on 5/2/2015.
 */
public interface SampleDAO {

    Serializable insert(SampleDomain domain);

    SampleDomain get(Serializable id);
}
