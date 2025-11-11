package org.zerock.ex3.product.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductImage implements Comparable<ProductImage> {

    private int idx;

    private String fileName;

    @Override
    public int compareTo(ProductImage o) {
        return this.idx - o.idx;
    }
}
