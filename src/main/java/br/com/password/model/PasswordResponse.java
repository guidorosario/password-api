package br.com.password.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordResponse {

    private Boolean checkedPassword;

    private List<String> errorPassword = new ArrayList<>();

    public Boolean getCheckedPassword() {
        return checkedPassword;
    }

    public void setCheckedPassword(Boolean checkedPassword) {
        this.checkedPassword = checkedPassword;
    }

    public List<String> getErrorPassword() {
        return errorPassword;
    }

    public PasswordResponse() {
    }

    public PasswordResponse(Boolean checkedPassword) {
        this.checkedPassword = checkedPassword;
    }

    public PasswordResponse checkResponse() {
        this.setCheckedPassword(this.getErrorPassword().isEmpty());
        return this;
    }

    @Override
    public String toString() {
        return "PasswordResponse{" +
                "checkedPassword=" + checkedPassword +
                ", errorPassword=" + errorPassword +
                '}';
    }
}
