package com.pblgllgs.productservice.util;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import com.pblgllgs.productservice.dto.ProductDto;
import com.pblgllgs.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class UtilMapper {

    public static ProductDto toDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product toEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
