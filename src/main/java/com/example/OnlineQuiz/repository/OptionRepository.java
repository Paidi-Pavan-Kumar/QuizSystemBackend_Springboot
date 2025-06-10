package com.example.OnlineQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineQuiz.entity.Option;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllById(Iterable<Long> ids);
}
