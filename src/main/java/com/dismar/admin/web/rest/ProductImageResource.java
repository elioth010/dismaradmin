package com.dismar.admin.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dismar.admin.service.ProductImageService;
import com.dismar.admin.web.rest.errors.BadRequestAlertException;
import com.dismar.admin.web.rest.util.HeaderUtil;
import com.dismar.admin.service.dto.ProductImageDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProductImage.
 */
@RestController
@RequestMapping("/api")
public class ProductImageResource {

    private final Logger log = LoggerFactory.getLogger(ProductImageResource.class);

    private static final String ENTITY_NAME = "productImage";

    private final ProductImageService productImageService;

    public ProductImageResource(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    /**
     * POST  /product-images : Create a new productImage.
     *
     * @param productImageDTO the productImageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productImageDTO, or with status 400 (Bad Request) if the productImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-images")
    @Timed
    public ResponseEntity<ProductImageDTO> createProductImage(@Valid @RequestBody ProductImageDTO productImageDTO) throws URISyntaxException {
        log.debug("REST request to save ProductImage : {}", productImageDTO);
        if (productImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new productImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductImageDTO result = productImageService.save(productImageDTO);
        return ResponseEntity.created(new URI("/api/product-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-images : Updates an existing productImage.
     *
     * @param productImageDTO the productImageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productImageDTO,
     * or with status 400 (Bad Request) if the productImageDTO is not valid,
     * or with status 500 (Internal Server Error) if the productImageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-images")
    @Timed
    public ResponseEntity<ProductImageDTO> updateProductImage(@Valid @RequestBody ProductImageDTO productImageDTO) throws URISyntaxException {
        log.debug("REST request to update ProductImage : {}", productImageDTO);
        if (productImageDTO.getId() == null) {
            return createProductImage(productImageDTO);
        }
        ProductImageDTO result = productImageService.save(productImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-images : get all the productImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of productImages in body
     */
    @GetMapping("/product-images")
    @Timed
    public List<ProductImageDTO> getAllProductImages() {
        log.debug("REST request to get all ProductImages");
        return productImageService.findAll();
        }

    /**
     * GET  /product-images/:id : get the "id" productImage.
     *
     * @param id the id of the productImageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productImageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/product-images/{id}")
    @Timed
    public ResponseEntity<ProductImageDTO> getProductImage(@PathVariable Long id) {
        log.debug("REST request to get ProductImage : {}", id);
        ProductImageDTO productImageDTO = productImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productImageDTO));
    }

    /**
     * DELETE  /product-images/:id : delete the "id" productImage.
     *
     * @param id the id of the productImageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductImage(@PathVariable Long id) {
        log.debug("REST request to delete ProductImage : {}", id);
        productImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
