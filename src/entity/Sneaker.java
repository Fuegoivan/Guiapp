/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author krasa
 */
@Entity
public class Sneaker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sneakerModel;
    @OneToOne 
    private Brand sneakerFirm;
    private double sneakerSize;
    private double sneakerPrice;
    private int sneakerQuantity;


    public String getSneakerModel() {
        return sneakerModel;
    }

    public void setSneakerModel(String sneakerModel) {
        this.sneakerModel = sneakerModel;
    }

    public double getSneakerSize() {
        return sneakerSize;
    }

    public void setSneakerSize(double sneakerSize) {
        this.sneakerSize = sneakerSize;
    }

    public double getSneakerPrice() {
        return sneakerPrice;
    }

    public void setSneakerPrice(double sneakerPrice) {
        this.sneakerPrice = sneakerPrice;
    }

    public int getSneakerQuantity() {
        return sneakerQuantity;
    }

    public void setSneakerQuantity(int quantity) {
        this.sneakerQuantity = quantity;
    }
    @Override
    public String toString() {
        return  sneakerFirm.getBrand() +" "+ sneakerModel + ", размер: " + sneakerSize + ", цена: " + sneakerPrice + " евро, " + sneakerQuantity + " шт." + ' ';
    }

    public Brand getSneakerFirm() {
        return sneakerFirm;
    }

    public void setSneakerFirm(Brand sneakerFirm) {
        this.sneakerFirm = sneakerFirm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
