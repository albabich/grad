package com.albabich.grad.repository;

import com.albabich.grad.model.MenuItem;
import com.albabich.grad.model.Restaurant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @Modifying
    @Transactional
    @Query("DELETE FROM MenuItem m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @Modifying
    @Transactional
    <S extends MenuItem> S save(S entity);
}
