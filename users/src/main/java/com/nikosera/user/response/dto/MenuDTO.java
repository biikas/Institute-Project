package com.nikosera.user.response.dto;

import com.nikosera.common.dto.ModelBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"title", "icon", "iconActive", "link", "isMultilevel", "parentLinks"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDTO extends ModelBase {

    @JsonIgnore
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String icon;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String iconActive;

    private Character isMultilevel;

    @JsonProperty("title")
    private String name;

    @JsonIgnore
    private Long parentId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("link")
    private String path;

    @JsonIgnore
    private String action;

    @JsonIgnore
    private Integer sortOrder;

    @JsonIgnore
    private String navType;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("parentLinks")
    private List<MenuDTO> childPermission = new ArrayList<>();

    public MenuDTO(Long id) {
        this.id = id;
    }

    public void addChildren(MenuDTO menuDTO) {
        childPermission.add(menuDTO);
    }
}
