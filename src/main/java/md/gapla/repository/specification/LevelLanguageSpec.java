package md.gapla.repository.specification;

import md.gapla.model.entity.lessons.LessonEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageDetailEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public final class LevelLanguageSpec {
    public static Specification<LevelLanguageDetailEntity> fetchSome() {
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

    public static Specification<LevelLanguageDetailEntity> levelLanguageIdEqual(Long expression) {

        return (root, query, builder) -> {
            return builder.equal(root.join("levelLanguage").get("levelLanguageId"), expression);
        };
    }

    public static Specification<LevelLanguageDetailEntity> languageCodedEqual(String expression) {

        return (root, query, builder) -> {
            return builder.equal(root.join("language").get("languageCode"), expression);
        };
    }

}
