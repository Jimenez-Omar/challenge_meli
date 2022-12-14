package com.mercadolibre.challenge.category.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRequest {
    private String name;
    private float rate;
    private int max;
}
