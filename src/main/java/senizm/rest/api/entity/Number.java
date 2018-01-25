package senizm.rest.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * <b>Project api</b><br />
 * Number.java<br />
 *
 * @author senizm
 */
@Entity
public class Number implements Serializable {
	private static final long serialVersionUID = -7031524616154346821L;

	@Id
	@Column(nullable = false)
	private Integer value;
	
	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date insertDate;
	
	@Column(nullable = false)
	@JsonIgnore
	private Boolean deleted = false;

	/**
	 * @return the value
	 *
	 * @author senizm
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 *
	 * @author senizm
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * @return the insertDate
	 *
	 * @author senizm
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate the insertDate to set
	 *
	 * @author senizm
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * @return the deleted
	 *
	 * @author senizm
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 *
	 * @author senizm
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the serialversionuid
	 *
	 * @author senizm
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
