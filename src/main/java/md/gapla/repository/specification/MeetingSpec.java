package md.gapla.repository.specification;

import jakarta.persistence.criteria.Join;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.account.AccountRoleEntity;
import md.gapla.model.entity.meeting.MeetingEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public final class MeetingSpec {
    public static Specification<MeetingEntity> fetchSome() {
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

    public static Specification<MeetingEntity> accountIdEqual(Long expression) {
        return (root, query, builder) -> {
            Join<AccountEntity, AccountRoleEntity> eve = root.join("participant");
            return builder.equal(eve.join("account").get("accountId"), expression);
        };
    }

    public static Specification<MeetingEntity> greaterDate(LocalDate expression) {
        return (root, query, builder) -> {
            return builder.greaterThan(root.get("meetingDate"), expression);
        };
    }

    public static Specification<MeetingEntity> lessDate(LocalDate expression) {
        return (root, query, builder) -> {
            return builder.lessThan(root.get("meetingDate"), expression);
        };
    }
}
