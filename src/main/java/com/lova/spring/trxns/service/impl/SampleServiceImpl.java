package com.lova.spring.trxns.service.impl;

import com.lova.spring.trxns.dao.SampleDAO;
import com.lova.spring.trxns.domain.SampleDomain;
import com.lova.spring.trxns.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Lovababu on 5/2/2015.
 */

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleDAO sampleDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Serializable insert(SampleDomain domain) {
        return sampleDAO.insert(domain);
    }

    @Override
    @Transactional(readOnly = true)
    public SampleDomain get(Serializable id) {
        return sampleDAO.get(id);
    }
}
