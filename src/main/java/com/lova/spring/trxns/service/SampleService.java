package com.lova.spring.trxns.service;

import com.lova.spring.trxns.domain.SampleDomain;

import java.io.Serializable;

/**
 * Created by Lovababu on 5/2/2015.
 */
public interface SampleService {

    Serializable insert(SampleDomain domain);

    SampleDomain get(Serializable id);
}
