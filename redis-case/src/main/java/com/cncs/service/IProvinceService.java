package com.cncs.service;

import com.cncs.domain.Province;

import java.util.List;

public interface IProvinceService {
    List<Province> findAll();

    String findAllJson();
}
