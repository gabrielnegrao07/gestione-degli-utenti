package it.gestione.gestionedegliutenti.service;

import it.gestione.gestionedegliutenti.model.UserEntity;
import it.gestione.gestionedegliutenti.model.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void save(UserEntity entity);

    Page<UserEntity> findAll(UserFilter filter, Pageable pageable);

    UserEntity findById(Long id) throws Exception;

    void update(UserEntity entity, Long id) throws Exception;
}
