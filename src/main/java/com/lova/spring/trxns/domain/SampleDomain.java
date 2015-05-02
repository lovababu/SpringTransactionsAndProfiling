package com.lova.spring.trxns.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Lovababu on 5/2/2015.
 */
@Entity
@Table(name = "SAMPLE")
public class SampleDomain {

    @Id
    @Column(name = "SID")
    @Setter @Getter
    private Long id;

    @Column(name = "NAME", nullable = false)
    @Setter @Getter
    private String name;

    @Column(name = "ADDRESS")
    @Setter @Getter
    private String address;
}
