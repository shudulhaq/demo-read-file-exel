package com.infosys.demoreadexcel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Excel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "typegl")
    private String typeGl;

    @Column(name = "typeexpense")
    private String typeExpense;

    @Column(name = "rccode")
    private String rcCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeGl() {
        return typeGl;
    }

    public void setTypeGl(String typeGl) {
        this.typeGl = typeGl;
    }

    public String getTypeExpense() {
        return typeExpense;
    }

    public void setTypeExpense(String typeExpense) {
        this.typeExpense = typeExpense;
    }

    public String getRcCode() {
        return rcCode;
    }

    public void setRcCode(String rcCode) {
        this.rcCode = rcCode;
    }
}
