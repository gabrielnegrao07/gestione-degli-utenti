package it.gestione.gestionedegliutenti.service;

import it.gestione.gestionedegliutenti.model.UserEntity;
import it.gestione.gestionedegliutenti.model.UserFilter;
import it.gestione.gestionedegliutenti.repository.UserRepository;
import it.gestione.gestionedegliutenti.repository.specification.UserSpecification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService{

    UserRepository repository;

    @Override
    @Transactional
    public void save(UserEntity entity) {
        repository.save(entity);
    }

    @Override
    public Page<UserEntity> findAll(UserFilter filter, Pageable pageable) {
        return repository.findAll(new UserSpecification(filter), pageable);
    }

    @Override
    public UserEntity findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("User not found!"));
    }

    @Override
    @Transactional
    public void update(UserEntity request, Long id) throws Exception {
        var entity = this.findById(id);
        updateEntityFromRequest(request, entity);
        repository.save(entity);
    }
    private void updateEntityFromRequest(UserEntity request, UserEntity entity) {
        if (request.getAddress() != null) {
            entity.setAddress(request.getAddress());
        }
        if (request.getMail() != null) {
            entity.setMail(request.getMail());
        }
    }
}
