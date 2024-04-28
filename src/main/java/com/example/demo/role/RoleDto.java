package com.example.demo.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Long id;
    private String name;
    private String creationDate;
    private Long creatorId;
    private String lastModifiedDate;
    private String lastModifierId;
}
