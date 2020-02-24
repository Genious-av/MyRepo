package application.tinkoff.assign.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import application.tinkoff.config.CodeResults;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "result")
public class ResultEntity {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column(name = "CODE", nullable=false, length=50)
	String code;
	
	
	@Column(name = "NUMBER")
	int number;
	
	@Column(name = "FILENAMES", length=100)
	String filenames;
	
	@Column(name = "ERROR", length=100)
	String error;

	public ResultEntity(String code, int number, String filenames, String error) {
		super();
		this.code = code;
		this.number = number;
		this.filenames = filenames;
		this.error = error;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultEntity other = (ResultEntity) obj;
		if (code != other.code)
			return false;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		if (filenames == null) {
			if (other.filenames != null)
				return false;
		} else if (!filenames.equals(other.filenames))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((filenames == null) ? 0 : filenames.hashCode());
		result = prime * result + number;
		return result;
	}
	
	
	
}
