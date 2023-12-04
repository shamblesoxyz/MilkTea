package hcmute.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import hcmute.entity.CartDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcmute.embeddedId.CartDetailId;
import hcmute.entity.CartDetailEntity;
import hcmute.entity.MilkTeaEntity;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetailEntity, CartDetailId> {
	List<CartDetailEntity> findByCartByCartDetailIdCart(int idCart);

    @Query("SELECT cd.milkTeaByCartDetail FROM CartDetailEntity cd WHERE cd.cartByCartDetail.idCart = :idCart")
    List<MilkTeaEntity> findMilkTeaByCartId(int idCart);
    
    // Add new product to cart
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cart_detail (id_cart, id_milk_tea, size) VALUES (:idCart, :idMilkTea, :size)", nativeQuery = true)
    void addProductToCart(@Param("idCart") int idCart, @Param("idMilkTea") int idMilkTea, @Param("size") String size);

}