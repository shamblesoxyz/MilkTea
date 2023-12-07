package hcmute.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "milk_tea")
public class MilkTeaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_milk_tea")
	private int idMilkTea;

	@Column(name = "name", columnDefinition = "nvarchar(100)")
	private String name;

	@Column(name = "cost")
	private int cost;

	@Column(name = "description", columnDefinition = "nvarchar(1000)")
	private String description;

	@Column(name = "remain_quantity")
	private int remainQuantity;

	@Column(name = "image", columnDefinition = "varchar(1000)")
	private String image;

	@ManyToOne
	@JoinColumn(name = "id_type", insertable = false, updatable = false)
	private MilkTeaTypeEntity milkTeaTypeByMilkTea;

	@ManyToOne
	@JoinColumn(name = "id_branch", insertable = false, updatable = false)
	private BranchEntity branchByMilkTea;

	@OneToMany(mappedBy = "milkTeaByCartDetail")
	private Set<CartDetailEntity> cartDetails;

	@OneToMany(mappedBy = "milkTeaByOrderDetail")
	private Set<OrderDetailEntity> orderDetails;
}