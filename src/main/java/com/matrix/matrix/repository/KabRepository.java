package com.matrix.matrix.repository;

import com.matrix.matrix.model.Kab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KabRepository extends JpaRepository<Kab, Long> {

    @Query(value = "SELECT ka.ID, ka.erstellt, ka.geaendert, ka.ersteller, ka.mandant, ka.kundennr FROM kab ka LIMIT 10000;", nativeQuery = true)
    List<Kab> selectAllKabManual();

}
