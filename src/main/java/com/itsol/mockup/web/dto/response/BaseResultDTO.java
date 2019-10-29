package com.itsol.mockup.web.dto.response;

import com.itsol.mockup.utils.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResultDTO {
    private String errorCode;
    private String description;
//    private List<?> listData = new ArrayList();
//    private Object data;
//    private Long totalRow = 0L;
//    private Integer totalPage = 0;

    public BaseResultDTO() {
        errorCode = Constants.ERROR;
        description = Constants.UNKNOWN;
    }

    public BaseResultDTO(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public void setSuccess(){
        this.errorCode = Constants.SUCCESS;
        this.description = "ok";
    }

    public void setFail(String msg) {
        this.errorCode = Constants.ERROR;
        this.description = msg;
    }

    public void setFail(String errorCode,String msg) {
        this.errorCode = errorCode;
        this.description = msg;
    }

    public void setItemNotfound(String msg){
        this.errorCode = Constants.ERR_CODE_ITEM_NOT_FOUND;
        this.description = msg;
    }

    public void setItemNotfound(){
        this.setItemNotfound("item not found");
    }

}
