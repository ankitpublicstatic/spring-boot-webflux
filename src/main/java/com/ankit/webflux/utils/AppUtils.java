package com.ankit.webflux.utils;

import org.springframework.beans.BeanUtils;
import com.ankit.webflux.dto.Product;
import com.ankit.webflux.dto.ProductDto;

public class AppUtils {

  public static ProductDto entityToDto(Product product) {
    ProductDto productDto = new ProductDto();
    BeanUtils.copyProperties(product, productDto);
    return productDto;
  }

  public static Product dtoToEntity(ProductDto productDto) {
    Product product = new Product();
    BeanUtils.copyProperties(productDto, product);
    return product;

  }
}
