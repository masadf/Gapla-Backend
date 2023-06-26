package md.gapla.repository.specification;

import md.gapla.model.entity.forum.ForumQuestionsEntity;
import md.gapla.model.entity.forum.ForumTypeDetailEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public final class ForumQuestionSpec {
    public static Specification<ForumQuestionsEntity> fetchSome() {
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

    public static Specification<ForumQuestionsEntity> forumIdEqual(Long expression) {

        return (root, query, builder) -> {
            return builder.equal(root.join("forum").get("forumId"), expression);
        };
    }



}
