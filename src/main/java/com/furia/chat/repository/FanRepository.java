package com.furia.chat.repository;

import com.furia.chat.model.Fan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanRepository extends CrudRepository<Fan, String> {
    Fan findByUsername(String username);
}
