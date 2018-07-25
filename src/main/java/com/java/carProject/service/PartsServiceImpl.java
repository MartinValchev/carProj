package com.java.carProject.service;

import com.java.carProject.entity.Parts;
import com.java.carProject.repository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartsServiceImpl implements PartsService {
    @Autowired
    PartsRepository partsRepository;

    @Override
    public Parts getPartsById(Long id) {
        return partsRepository.getPartsById(id);
    }

    @Override
    public List<Parts> getAllPartsList() {
        return partsRepository.getAllPartsList();
    }

    @Override
    public void deleteParts(List<String> partIds) {
       List<Long> longIds = (partIds.stream().mapToLong(Long::parseLong)).boxed().collect(Collectors.toList());
        partsRepository.deleteParts(longIds);
    }
}
