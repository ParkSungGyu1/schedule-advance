package com.example.develop.schedule.repository;

import com.example.develop.schedule.dto.response.SchedulePageResponseDto;
import com.example.develop.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "SELECT new com.example.develop.schedule.dto.response.SchedulePageResponseDto( " +
            " s.id, s.title, s.content, COUNT(c.id), s.createdAt, s.updatedAt, u.userName ) " +
            "FROM Schedule s " +
            "JOIN s.user u " +
            "LEFT JOIN Comment c ON c.schedule.id = s.id " +
            "GROUP BY s.id, s.title, s.content, s.createdAt, s.updatedAt, u.userName " +
            "ORDER BY s.updatedAt DESC",

            countQuery = "SELECT COUNT(s) FROM Schedule s")
    Page<SchedulePageResponseDto> findSchedulePageWithCommentCount(Pageable pageable);

}
