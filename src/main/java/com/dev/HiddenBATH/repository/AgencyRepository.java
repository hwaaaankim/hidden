package com.dev.HiddenBATH.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long>, JpaSpecificationExecutor<Agency> {

    Page<Agency> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * 목록 검색: 연관들 미리 로딩 (Province/City/District)
     */
    @Query(
        value = """
            SELECT a
              FROM Agency a
              LEFT JOIN FETCH a.province p
              LEFT JOIN FETCH a.city c
              LEFT JOIN FETCH a.district d
             WHERE
                 ( :keyword IS NULL OR :keyword = '' )
              OR ( :type = 'name'
                   AND LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%')) )
              OR ( :type = 'contact'
                   AND (
                        FUNCTION('REPLACE', a.tel, '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', :keyword, '-', ''), '%')
                     OR FUNCTION('REPLACE', a.mobile, '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', :keyword, '-', ''), '%')
                   )
                 )
            """,
        countQuery = """
            SELECT COUNT(a)
              FROM Agency a
             WHERE
                 ( :keyword IS NULL OR :keyword = '' )
              OR ( :type = 'name'
                   AND LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%')) )
              OR ( :type = 'contact'
                   AND (
                        FUNCTION('REPLACE', a.tel, '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', :keyword, '-', ''), '%')
                     OR FUNCTION('REPLACE', a.mobile, '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', :keyword, '-', ''), '%')
                   )
                 )
            """
    )
    Page<Agency> searchByTypeAndKeyword(@Param("type") String type,
                                        @Param("keyword") String keyword,
                                        Pageable pageable);

    /**
     * 단건 조회도 연관 미리 로딩
     */
    @Query("""
        SELECT a
          FROM Agency a
          LEFT JOIN FETCH a.province
          LEFT JOIN FETCH a.city
          LEFT JOIN FETCH a.district
         WHERE a.id = :id
        """)
    Optional<Agency> findWithRegionsById(@Param("id") Long id);
}