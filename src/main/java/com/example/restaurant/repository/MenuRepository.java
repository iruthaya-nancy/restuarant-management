package com.example.restaurant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restaurant.dto.FoodSoldDto;
import com.example.restaurant.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> findByIdIn(List<Long> menuItemIds);

	List<Menu> findByIdInAndIsActiveIsTrue(List<Long> id);

	@Query(value = "Select new com.example.restaurant.dto.FoodSoldDto(m.name as name ,count(m.id) as count) from SelectedFood sf left join Menu m on m.id = sf.menu.id where DATE (sf.createdAt)>=(:fromDate) and DATE(sf.createdAt)<=(:toDate) group by m.id")
	//@Query(value = "select name,count(menu_id) from selected_food left join menu on menu.id = selected_food.menu_id where date(selected_food.created_at) between :fromDate and :toDate group by(menu_id);" ,nativeQuery = true)
	List<FoodSoldDto> findFoodSold(Date fromDate, Date toDate);
}
