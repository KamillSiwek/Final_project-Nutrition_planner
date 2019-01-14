package pl.coderslab.finishingproject.controllers;

import java.util.ArrayList;
import java.util.List;

public class Result {

  private String code;
  private String message;
  private List<String> errors = new ArrayList<>();
  private Object data;

  public Result(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public static Result ok(Object data) {
    Result result = new Result("200");
    result.setData(data);
    result.setMessage("OK");
    return result;
  }

  public static Result error(String message) {
    Result result = new Result("500");
    result.setMessage("ERROR");
    result.getErrors().add(message);
    return result;
  }

}