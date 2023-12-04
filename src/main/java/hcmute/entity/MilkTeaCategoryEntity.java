package hcmute.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MilkTeaCategory")
public class MilkTeaCategoryEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCategory")
	private int idCategory;
	
	@Column(name = "name",columnDefinition = "nvarchar(100)")
	private String name;	
	
	@OneToMany(mappedBy = "milkTeaCategoryByMilkTeaType")
	private Set<MilkTeaTypeEntity> milkTeaTypes;
}