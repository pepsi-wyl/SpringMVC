package controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author by pepsi-wyl
 * @date 2022-03-01 16:34
 */
@ControllerAdvice
public class ExceptionController {
    // @ExceptionHandler用于设置所标识方法处理的异常
    @ExceptionHandler(ArithmeticException.class)
    public String handleArithmeticException(Exception exception, Model model){
        model.addAttribute("exception", exception);
        return "error";
    }
}
