/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Jan 2, 2014.
 */
package com.m4gik.business.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEPOT")
public class WarehouseFinland {

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "depot_time", nullable = true)
    private Timestamp depotDate;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public WarehouseFinland() {
    }

    /**
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @return the depotDate
     */
    public Timestamp getDepotDate() {
        return depotDate;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @param depotDate
     *            the depotDate to set
     */
    public void setDepotDate(Timestamp depotDate) {
        this.depotDate = depotDate;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
