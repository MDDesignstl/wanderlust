package com.example.wanderlust.models.data;

import com.example.wanderlust.models.Favorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FavoriteDao extends CrudRepository<Favorite,Integer> {
}
