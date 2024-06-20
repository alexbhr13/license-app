package com.license.studentscenespring.repository;

import com.license.studentscenespring.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
}
