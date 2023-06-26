package md.gapla.model.dto.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import md.gapla.model.entity.account.AccountRoleEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class AccountCheckLevelDto {

    private Long accountCheckLevelId;

    private String  email;

    private Double reading;

    private Double listening;

    private Double total;

    private LocalDateTime createdDate;
}
