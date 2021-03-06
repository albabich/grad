package com.albabich.grad.repository;

import com.albabich.grad.model.Restaurant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Cacheable("restaurantsAndMenus")
    @Query("SELECT DISTINCT(r) FROM Restaurant r JOIN FETCH  r.menuItems m WHERE m.date=?1")
    List<Restaurant> getAllWithMenuItemsByDate(LocalDate date);

    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id ")
    int delete(@Param("id") int id);

    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @Transactional
    @Modifying
    @Override
    <S extends Restaurant> S save(S entity);

    @Query("SELECT DISTINCT(r) FROM Restaurant r JOIN FETCH  r.votes m WHERE m.date=?1")
    Collection<Restaurant> findAllByVoteDate(@Param("date") LocalDate localDate);
}
