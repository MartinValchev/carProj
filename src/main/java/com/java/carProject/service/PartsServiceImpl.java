package com.java.carProject.service;

import com.java.carProject.entity.Parts;
import com.java.carProject.repository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    Pattern pattern  = Pattern.compile("\\d+");

       for(String partId:partIds){
           Matcher matcher = pattern.matcher(partId);
           if(matcher.find()){
               String partIdStr = matcher.toMatchResult().group();
               Long currentId = Long.parseLong(partIdStr);
               partsRepository.deleteById(currentId);
           }

       }
    }
}
