package md.gapla.repository.specification;

import jakarta.persistence.criteria.Join;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.account.AccountRoleEntity;
import md.gapla.model.entity.lessons.LessonMaterialsEntity;
import md.gapla.model.entity.lessons.LessonMaterialsTypeEntity;
import md.gapla.model.entity.lessons.LessonMaterialsTypeLanguageEntity;
import md.gapla.model.enums.AccountRoleEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public final class LessonMaterialsSpec {
    public static Specification<LessonMaterialsEntity> fetchSome() {
        return (root, query, builder) -> {
            // в случае с пагинацией, делается второй запрос count(*) и он при если есть fetch ломаеться
            // c ошибкой query specified join fetching, but the owner of the fetched association was not present in the select list
            // смотри решение в комментарии метода
            // в случае если это count то query.getResultType() это long и тогда мы не делаем fetch
            if (query.getResultType() != Long.class) {

            }

            return null;
        };
    }

    public static Specification<LessonMaterialsEntity> languageCodeEqual(String expression) {

        return (root, query, builder) -> {
            return builder.equal(root.join("lessonMaterialsType").join("languages").join("language").get("languageCode"), expression);
        };
    }
    public static Specification<LessonMaterialsEntity> lessonMaterialsTypeIdEqual(Long expression) {

        return (root, query, builder) -> {
            return builder.equal(root.join("lessonMaterialsType").get("lessonMaterialsType"), expression);
        };
    }

    public static Specification<LessonMaterialsEntity> lessonIdEqual(Long expression) {

        return (root, query, builder) -> {
            return builder.equal(root.join("lesson").get("lessonId"), expression);
        };
    }

    public static Specification<LessonMaterialsEntity> likeDocumentName(String expression) {

        return (root, query, builder) -> {
            return builder.like(builder.upper(root.get("documentName")), "%" + expression + "%");
        };
    }
}
