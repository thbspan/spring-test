package com.test.data.jdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("job")
public record Job(@Id long jobId, String name) {
}
