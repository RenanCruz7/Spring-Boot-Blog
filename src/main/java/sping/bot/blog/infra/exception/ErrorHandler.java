package sping.bot.blog.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// Geralmente, quando uma entidade não é encontrada, o Spring lança uma exceção do tipo EntityNotFoundException.
// Para tratar esse erro, criamos um tratador de erros que intercepta a exceção EntityNotFoundException e retorna um status 404.
// Para isso, criamos uma classe chamada TratadorDeErros e anotamos com @RestControllerAdvice.
// Em seguida, criamos um método chamado tratarErro404 que retorna um ResponseEntity com status 404.
// Por fim, anotamos o método com @ExceptionHandler e passamos a exceção EntityNotFoundException como parâmetro.
// Dessa forma, sempre que uma exceção EntityNotFoundException for lançada, o método tratarErro404 será chamado e retornará um status 404.
// Assim, garantimos que a resposta da API seja coerente com o erro ocorrido.
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404Error() {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handle405Error() {
        return ResponseEntity.status(405).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequestError(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorData::new).toList());

    }
    private record ValidationErrorData(String campo, String mensagem) {
        public ValidationErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
