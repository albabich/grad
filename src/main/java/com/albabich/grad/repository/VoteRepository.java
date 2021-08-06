package com.albabich.grad.repository;

import com.albabich.grad.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT v from Vote v WHERE  v.date=?1 AND v.user.id=?2")
    Vote getByDateAndUser(LocalDate date, int userId);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v from Vote v WHERE  v.date=?1")
    List<Vote> findAllByDate(LocalDate date);
}
