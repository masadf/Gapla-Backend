package md.gapla.repository.specification;

import jakarta.persistence.criteria.Join;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.account.AccountRoleEntity;
import md.gapla.model.entity.forum.ForumEntity;
import md.gapla.model.entity.forum.ForumTypeDetailEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public final class ForumSpec {
    public static Specification<ForumEntity> fetchSome() {
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



    public static Specification<ForumEntity> forumForumTypeIdEqual(Long expression) {
        return (root, query, builder) -> {

            return builder.equal(root.join("forumTypeDetail").get("forumTypeId"), expression);
        };
    }
    public static Specification<ForumEntity> forumLanguageCodeEqual(String expression) {

        return (root, query, builder) -> {
            return builder.equal(root.join("forumTypeDetail").join("language").get("languageCode"), expression);
        };
    }
}
