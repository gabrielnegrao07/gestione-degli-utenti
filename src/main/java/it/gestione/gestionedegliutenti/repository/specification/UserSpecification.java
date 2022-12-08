package it.gestione.gestionedegliutenti.repository.specification;

import it.gestione.gestionedegliutenti.model.UserEntity;
import it.gestione.gestionedegliutenti.model.UserFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSpecification implements Specification<UserEntity> {

    UserFilter filter;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicate = criteriaBuilder.conjunction();

        Optional.ofNullable(this.filter.getName())
                .filter(Objects::nonNull)
                .map(it -> "%".concat(it).concat("%"))
                .ifPresent(name -> add(predicate, criteriaBuilder.like(root.get("name"), name)));

        Optional.ofNullable(this.filter.getSurname())
                .filter(Objects::nonNull)
                .map(it -> "%".concat(it).concat("%"))
                .ifPresent(surname -> add(predicate, criteriaBuilder.like(root.get("surname"), surname)));

        return predicate;
    }

    private void add(Predicate predicate, Expression expression) {
        predicate.getExpressions().add(expression);
    }
}
