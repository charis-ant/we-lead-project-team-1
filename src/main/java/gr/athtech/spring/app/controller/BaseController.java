package gr.athtech.spring.app.controller;

import gr.athtech.spring.app.base.BaseComponent;
import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.model.BaseModel;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.transfer.ApiResponse;
import gr.athtech.spring.app.transfer.resource.BaseResource;
import jakarta.validation.Valid;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseController<T extends BaseModel, R extends BaseResource> extends BaseComponent {
    protected abstract BaseService<T, Long> getBaseService();
    protected abstract BaseMapper<T, R> getMapper();

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<R>> get(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(
                ApiResponse.<R>builder()
                        .data(getMapper().toResource(getBaseService().get(id)))
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<R>>> findAll() {
        return ResponseEntity.ok(
                ApiResponse.<List<R>>builder()
                        .data(getMapper().toResources(getBaseService().findAll()))
                        .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<R>> create(@Valid @RequestBody final R resource) {
        var domain = getBaseService().create(getMapper().toDomain(resource));
        return new ResponseEntity<>(
                ApiResponse.<R>builder()
                        .data(getMapper().toResource(domain))
                        .build(),
                getNoCacheHeaders(),
                HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody final R resource) {
        getBaseService().update(getMapper().toDomain(resource));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        getBaseService().deleteById(id);
    }

    protected CacheControl getCacheHeaders(int cacheDuration) {
        return CacheControl.maxAge(cacheDuration, TimeUnit.SECONDS).noTransform();
    }

    protected HttpHeaders getNoCacheHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        // HTTP 1.1 cache control header
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        // Http 1.0 cache control header
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return headers;
    }
}
