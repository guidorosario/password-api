package br.com.password.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordResponse {

    private Boolean checkedPassword;

    private ArrayList<String> errorPassword = new ArrayList<>();

    public Boolean getCheckedPassword() {
        return checkedPassword;
    }

    public void setCheckedPassword(Boolean checkedPassword) {
        this.checkedPassword = checkedPassword;
    }

    public ArrayList<String> getErrorPassword() {
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
        final StringBuilder sb = new StringBuilder("PasswordResponse {");
        sb.append("checkedPassword=").append(checkedPassword);
        sb.append(", errorPassword=").append(errorPassword);
        sb.append('}');
        return sb.toString();
    }
}
