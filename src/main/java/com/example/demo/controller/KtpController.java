package com.example.demo.controller;

import com.example.demo.model.dto.KtpDto;
import com.example.demo.service.KtpService;
import com.example.demo.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
public class KtpController {

    private final KtpService service;

    public KtpController(KtpService service){
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ApiResponse create(@RequestBody KtpDto dto){
        return new ApiResponse("Data berhasil ditambahkan",
                service.create(dto));
    }

    // READ ALL
    @GetMapping
    public ApiResponse getAll(){
        List<KtpDto> data = service.getAll();
        return new ApiResponse("Data berhasil diambil", data);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Integer id){
        return new ApiResponse("Data ditemukan",
                service.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Integer id,
                              @RequestBody KtpDto dto){
        return new ApiResponse("Data berhasil diupdate",
                service.update(id,dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        service.delete(id);
        return new ApiResponse("Data berhasil dihapus",null);
    }
}