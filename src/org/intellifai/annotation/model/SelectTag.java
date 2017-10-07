package org.intellifai.annotation.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Model object that represents a select input widget.
 */
@Entity
@Table(name="selectTag")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class SelectTag {

	private Long id;

    private String selectId;

    private String label;
    
    private String value;

    protected SelectTag() {
    }


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional=false)
    public String getSelectId() {
        return selectId;
    }

    public void setSelectId(String selectId) {
        this.selectId = selectId;
    }

    @Basic(optional=false)
    public String getLabel() {
    	return label;
    }
    
    public void setLabel(String label) {
    	this.label = label;
    }
    
    @Basic(optional=false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
