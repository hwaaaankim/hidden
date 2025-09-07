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
     * - contact 검색 시 tel/mobile/fax 모두 하이픈 제거 후 부분일치
     * - keyword도 하이픈 제거 후 비교
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
                        FUNCTION('REPLACE', COALESCE(a.tel, ''),    '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', COALESCE(:keyword, ''), '-', ''), '%')
                     OR FUNCTION('REPLACE', COALESCE(a.mobile, ''), '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', COALESCE(:keyword, ''), '-', ''), '%')
                     OR FUNCTION('REPLACE', COALESCE(a.fax, ''),    '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', COALESCE(:keyword, ''), '-', ''), '%')
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
                        FUNCTION('REPLACE', COALESCE(a.tel, ''),    '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', COALESCE(:keyword, ''), '-', ''), '%')
                     OR FUNCTION('REPLACE', COALESCE(a.mobile, ''), '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', COALESCE(:keyword, ''), '-', ''), '%')
                     OR FUNCTION('REPLACE', COALESCE(a.fax, ''),    '-', '') LIKE CONCAT('%', FUNCTION('REPLACE', COALESCE(:keyword, ''), '-', ''), '%')
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