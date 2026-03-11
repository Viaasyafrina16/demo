package com.example.demo.service.impl;

import com.example.demo.mapper.Ktpmapper;
import com.example.demo.model.dto.KtpDto;
import com.example.demo.model.entity.Ktp;
import com.example.demo.repository.KtpRepository;
import com.example.demo.service.KtpService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    private final KtpRepository repository;

    public KtpServiceImpl(KtpRepository repository){
        this.repository = repository;
    }

    @Override
    public KtpDto create(KtpDto dto){

        repository.findByNomorKtp(dto.getNomorKtp())
                .ifPresent(k -> {
                    throw new RuntimeException("Nomor KTP sudah ada");
                });

        Ktp ktp = repository.save(Ktpmapper.toEntity(dto));
        return Ktpmapper.toDto(ktp);
    }

    @Override
    public List<KtpDto> getAll(){

        return repository.findAll()
                .stream()
                .map(Ktpmapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public KtpDto getById(Integer id){

        Ktp ktp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));

        return Ktpmapper.toDto(ktp);
    }

    @Override
    public KtpDto update(Integer id, KtpDto dto){

        Ktp ktp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));

        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());

        return Ktpmapper.toDto(repository.save(ktp));
    }

    @Override
    public void delete(Integer id){
        repository.deleteById(id);
    }

}