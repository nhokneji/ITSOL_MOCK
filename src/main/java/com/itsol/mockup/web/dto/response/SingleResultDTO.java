package com.itsol.mockup.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResultDTO<T> extends BaseResultDTO {
    private T data;

    public SingleResultDTO() {
    }

    public SingleResultDTO(T data) {
        this.data = data;
    }

    public SingleResultDTO(String errorCode, String description, T data) {
        super(errorCode, description);
        this.data = data;
    }

    public void setSuccess(T data){
        super.setSuccess();
        this.data = data;
    }

//    public void setSuccess(String mess){
//        super.setSuccess();
//        this.mess = mess;
//    }

    public void setResult(T data, String msgErr){
        if(data == null){
            super.setItemNotfound(msgErr);
        }else {
            super.setSuccess();
            this.data = data;
        }
    }

}
