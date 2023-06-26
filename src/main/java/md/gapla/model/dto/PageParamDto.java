package md.gapla.model.dto;

import lombok.Data;
import md.gapla.repository.specification.filters.FilterCriteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PageParamDto {
    private Map<String, String> params;
    private List<FilterCriteria> criteria;
    int page;
    int size;
    Sort.Direction sortDir;
    String sortField;

    public PageRequest getPageRequest() {
        PageRequest pageRequest;
        if (getSortField() != null) {
            pageRequest =
                    PageRequest.of(getPage()
                            , getSize()
                            , getSortDir()
                            , getSortField());
        } else {
            pageRequest =
                    PageRequest.of(getPage()
                            , getSize());

        }
        return pageRequest;
    }

    public Map<String, String> getParams() {
        if (params == null) {
            return new HashMap<>();
        }
        return this.params;
    }

    public List<FilterCriteria> getCriteria() {
        if (criteria == null) {
            return new ArrayList<>();
        }
        return criteria;
    }
}
