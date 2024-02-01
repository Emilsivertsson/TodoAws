package org.codeforpizza.todoaws.repository;

import org.codeforpizza.todoaws.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {


}
