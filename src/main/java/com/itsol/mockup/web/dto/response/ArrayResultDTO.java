package com.itsol.mockup.web.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArrayResultDTO<T> extends BaseResultDTO {

    List<T> listData;
    private Long totalRow = 0L;
    private Integer totalPage = 0;

    public ArrayResultDTO() {
    }

    public ArrayResultDTO(List<T> listData) {
        this.listData = listData;
    }

    public ArrayResultDTO(String errorCode, String description, List<T> listData) {
        super(errorCode, description);
        this.listData = listData;
    }

    public void setSuccess(List<T> listData, Long totalRow, Integer totalPage){
        super.setSuccess();
        this.listData = listData;
        this.totalRow = totalRow;
        this.totalPage = totalPage;
    }

    public void setSuccess(List<T> listData){
        super.setSuccess();
        this.listData = listData;
    }

    public void setResult(){
        super.setFail("Item not found");
    }

}
