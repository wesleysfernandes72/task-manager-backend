package com.wesleysfernandes72.taskmanager.specification;

import com.wesleysfernandes72.taskmanager.dto.TaskSearchRequest;
import com.wesleysfernandes72.taskmanager.model.TaskModel;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<TaskModel> byFilter(TaskSearchRequest filter) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.status() != null) {
                predicates.getExpressions().add(
                        cb.equal(root.get("status"), filter.status())
                );
            }

            if (filter.priority() != null) {
                predicates.getExpressions().add(
                        cb.equal(root.get("priority"), filter.priority())
                );
            }

            return predicates;
        };
    }
}