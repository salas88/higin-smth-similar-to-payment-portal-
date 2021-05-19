package com.example.please.repo.dto;

import java.util.List;

public interface ClientDto<T>{

    List<T> findAll();
}
