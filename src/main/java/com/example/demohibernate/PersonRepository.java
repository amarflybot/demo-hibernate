package com.example.demohibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    //DSL way of find by attribute
    Optional<Person> findByName(String name);
}
