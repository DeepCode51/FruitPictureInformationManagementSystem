package com.spring.dao;

import com.spring.config.MapperBase;
import com.spring.entity.Admins;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsMapper extends MapperBase<Admins> {
    Admins login(Admins admins);
}
