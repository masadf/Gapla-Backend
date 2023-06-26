package md.gapla.repository.specification;

import jakarta.persistence.criteria.Join;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.account.AccountRoleEntity;
import md.gapla.model.entity.lessons.LessonMaterialsTypeLanguageEntity;
import md.gapla.model.entity.test.TestTimeTypeLanguageEntity;
import md.gapla.model.enums.AccountRoleEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public final class LessonMaterialTypeLanguageSpec {
    public static Specification<LessonMaterialsTypeLanguageEntity> fetchSome() {
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

    public static Specification<LessonMaterialsTypeLanguageEntity> languageCodeEqual(String expression) {

        return (root, query, builder) -> {
            return builder.equal(root.join("language").get("languageCode"), expression);
        };
    }

    public static Specification<LessonMaterialsTypeLanguageEntity> lessonMaterialsTypeIdEqual(Long expression) {

        return (root, query, builder) -> {
            return builder.equal(root.join("lessonMaterialsType").get("lessonMaterialsTypeId"), expression);
        };
    }

    public static Specification<LessonMaterialsTypeLanguageEntity> accountRoleEqual(AccountRoleEnum expression) {
        return (root, query, builder) -> {
            return builder.equal(root.join("roles").get("accountRoleName"), expression);
        };
    }


}
