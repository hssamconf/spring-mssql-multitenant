package lu.findl.multitenant.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Long id;
	@Version
	protected Long version;
	private Timestamp lastUpdate;
	private Timestamp creationDate;
	private Boolean state;

	//initialisation
	public AbstractEntity build(Long id, Long version, Timestamp lastUpdate,Timestamp creationDate,Boolean state) {
		this.id = id;
		this.version = version;
		this.lastUpdate = lastUpdate;
		this.creationDate = creationDate;
		this.state = state;
		return this;
	}
	
	@Override
	public String toString() {
		return "AbstractEntity [id=" + id + ", version=" + version + ", lastUpdate=" + lastUpdate + ", creationDate="
				+ creationDate + ", state=" + state + "]";
	}

	@PrePersist
	void preInsert() {
		lastUpdate = new Timestamp(new Date().getTime());
		creationDate = new Timestamp(new Date().getTime());
		if(state==null)
			state = true;
	}
	
	@PreUpdate
	void preUpdate() {
		lastUpdate = new Timestamp(new Date().getTime());
	}

	@Override
	public boolean equals(Object entity) {
		String class1 = this.getClass().getName();
		String class2 = entity.getClass().getName();
		if (!class2.equals(class1)) {
			return false;
		}
		AbstractEntity other = (AbstractEntity) entity;
		return this.id == other.id;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
}