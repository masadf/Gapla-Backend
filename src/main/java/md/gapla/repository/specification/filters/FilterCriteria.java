package md.gapla.repository.specification.filters;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilterCriteria {
    private String key;
    private String operation;
    private Object value;
}
