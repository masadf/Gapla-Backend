package md.gapla.repository.specification.filters;

import jakarta.persistence.criteria.*;
import md.gapla.exception.InvalidRequestException;
import md.gapla.util.FormattingUtils;
import md.gapla.util.validator.DateValidator;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Date;


public abstract class JpaAbstractSpec<T> implements Specification<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    protected transient FilterCriteria criteria;

    protected JpaAbstractSpec(FilterCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if(criteria.getOperation()==null)
            return buildEqualsPredicate(root, builder);
        else
        return switch (criteria.getOperation()) {
            case ">" -> buildGreaterThanPredicate(root, builder);
            case "<" -> buildLessThanPredicate(root, builder);
            case "<=" -> buildLessThanOrEqualToPredicate(root, builder);
            case ">=" -> buildGreaterThanOrEqualToPredicate(root, builder);
            case "==" -> buildEqualsPredicate(root, builder);
            case "!=" -> buildNotEqualsPredicate(root, builder);
            default -> throw new InvalidRequestException("FILTER_INVALID_OPERATION");
        };
    }


    protected Predicate buildGreaterThanPredicate(From<T, ?> root,
                                                  CriteriaBuilder builder) {
//        if (root.get(criteria.getKey()).getJavaType() == Date.class) {
//            return builder.greaterThan(root.get(criteria.getKey()),
//                    DateValidator.validateDateOrThrowException((String) criteria.getValue(), FormattingUtils.ISO_DATE.get().toPattern()));
//        }
        return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
    }

    protected Predicate buildLessThanPredicate(From<T, ?> root,
                                               CriteriaBuilder builder) {
//        if (root.get(criteria.getKey()).getJavaType() == Date.class) {
//            return builder.lessThan(root.get(criteria.getKey()),
//                    DateValidator.validateDateOrThrowException((String) criteria.getValue(), FormattingUtils.ISO_DATE.get().toPattern()));
//        }
        return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
    }

    protected Predicate buildLessThanOrEqualToPredicate(From<T, ?> root,
                                                        CriteriaBuilder builder) {
        if (root.get(criteria.getKey()).getJavaType() == Date.class||root.get(criteria.getKey()).getJavaType() == LocalDateTime.class) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()),
                    LocalDateTime.parse(criteria.getValue().toString()));
        }
        return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
    }

    protected Predicate buildGreaterThanOrEqualToPredicate(From<T, ?> root,
                                                           CriteriaBuilder builder) {
        if (root.get(criteria.getKey()).getJavaType() == Date.class||root.get(criteria.getKey()).getJavaType() == LocalDateTime.class) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()),
                    LocalDateTime.parse(criteria.getValue().toString()));
        }
        return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
    }

    protected Predicate buildEqualsPredicate(From<T, ?> root,
                                             CriteriaBuilder builder) {
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
            return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
        else if (root.get(criteria.getKey()).getJavaType() == Date.class || root.get(criteria.getKey()).getJavaType()== LocalDateTime.class) {
            return builder.equal(root.get(criteria.getKey()), LocalDateTime.parse(criteria.getValue().toString()));
//                    DateValidator.validateDateOrThrowException((String) criteria.getValue(), FormattingUtils.ISO_DATE.get().toPattern()));
        }
        else {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }

    protected Predicate buildNotEqualsPredicate(From<T, ?> root,
                                                CriteriaBuilder builder) {
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
            return builder.notLike(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
//        else if (root.get(criteria.getKey()).getJavaType() == Date.class) {
//            return builder.notEqual(root.get(criteria.getKey()),
//                    DateValidator.validateDateOrThrowException((String) criteria.getValue(), FormattingUtils.ISO_DATE.get().toPattern()));
//        }
        else {
            return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
        }
    }

    public void setFilterCriteria(FilterCriteria criteria) {
        this.criteria = criteria;
    }

}
