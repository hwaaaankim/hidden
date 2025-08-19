package com.dev.HiddenBATH.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.HiddenBATH.model.Popup;

public interface PopupRepository extends JpaRepository<Popup, Long> {

    List<Popup> findAllByOrderByCreatedAtDesc();

    List<Popup> findByStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByCreatedAtDesc(LocalDate today1, LocalDate today2);
}