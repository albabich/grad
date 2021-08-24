package com.albabich.grad.repository;

import com.albabich.grad.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT v FROM Vote v WHERE  v.date=?1 AND v.user.id=?2")
    Vote getByDateAndUser(LocalDate date, int userId);
}
