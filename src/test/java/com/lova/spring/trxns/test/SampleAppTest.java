package com.lova.spring.trxns.test;

import com.lova.spring.trxns.config.AppConfig;
import com.lova.spring.trxns.domain.SampleDomain;
import com.lova.spring.trxns.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lovababu on 5/2/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class SampleAppTest {

    @Autowired
    private SampleService sampleService;

    @Test
    public void testInsert(){
        SampleDomain domain = getSampleDomain(101L, "Avol", "Banglore");
        Long id = (Long) sampleService.insert(domain);
        assertEquals(id, domain.getId());
    }

    @Test
    public void testGet(){
        //This 100 id record inserted into the db while initializing.
        //Check /dbscript/my-test-data.sql and AppConfig.java @Line 71 to 73.
        SampleDomain domain = sampleService.get(100L);

        assertEquals(domain.getName(), "Avol");
        assertEquals(domain.getAddress(), "AP");
    }

    private SampleDomain getSampleDomain(Long id, String name, String addr){
        SampleDomain domain = new SampleDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setAddress(addr);
        return domain;
    }
}
