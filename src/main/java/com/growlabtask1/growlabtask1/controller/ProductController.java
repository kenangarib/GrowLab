package com.growlabtask1.growlabtask1.controller;

import com.growlabtask1.growlabtask1.domain.Product;
import com.growlabtask1.growlabtask1.dto.RequestDto;
import com.growlabtask1.growlabtask1.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ModelMapper modelMapper;
    private static final HashMap<Integer, Product> productHashMap = new HashMap<>();
    private  Long nextId = 1L;


    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody @Valid RequestDto requestDto){
        Product product = modelMapper.map(requestDto, Product.class);

        Long objectId = nextId++;
        LocalDate createdAt = LocalDate.now();
        product.setId(objectId);
        product.setCreatedDate(createdAt);
        productHashMap.put(Math.toIntExact(product.getId()), product);
    }

    @GetMapping("/getById/{id}")
    public ResponseDto getProductById(@PathVariable Long id) {

        if (productHashMap.containsKey(id)) {
            Product product = productHashMap.get(id);
            return modelMapper.map(product, ResponseDto.class);
        } else System.out.println("Yoxdur");

        return null;
    }

    @GetMapping
    public List<ResponseDto> getAllProducts() {
        return new ArrayList<>(productHashMap.values().stream().map(product -> modelMapper.map(product, ResponseDto.class)).collect(Collectors.toList()));
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseDto update(@PathVariable Integer id, @RequestBody @Valid RequestDto productRequest) {

        if (productHashMap.containsKey(id)) {
            Product product = modelMapper.map(productRequest, Product.class);
            product.setId(Long.valueOf(id));
            product.setCreatedDate(LocalDate.now());
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());

            productHashMap.put(id, product);

            return modelMapper.map(product, ResponseDto.class);
        } else return null;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void delete(@PathVariable Integer id) {
        if (productHashMap.containsKey(id)) {
            productHashMap.remove(id);
        } else System.out.println("Yoxdur");
    }

}
