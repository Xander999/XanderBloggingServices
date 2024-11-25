package com.xanderProjects.blog.XanderBlogging.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty(message = "Category must contain some Title")
    private String categoryTitle;

    @NotEmpty(message = "Category description is must")
    private String categoryDescription;

}
