package com.dev.HiddenBATH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long>{

}
