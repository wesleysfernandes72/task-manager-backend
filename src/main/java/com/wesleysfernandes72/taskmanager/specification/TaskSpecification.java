package com.wesleysfernandes72.taskmanager.specification;

import com.wesleysfernandes72.taskmanager.dto.TaskSearchRequest;
import com.wesleysfernandes72.taskmanager.model.TaskModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {

    public static Specification<TaskModel> byFilter(TaskSearchRequest filter) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter.status() != null) {
                predicates.add(
                        cb.equal(root.get("status"), filter.status())
                );
            }

            if (filter.priority() != null) {
                predicates.add(
                        cb.equal(root.get("priority"), filter.priority())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}