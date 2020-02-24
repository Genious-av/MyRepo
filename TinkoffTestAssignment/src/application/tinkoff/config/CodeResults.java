package application.tinkoff.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
 * @author Alexander Gryaznov
 * enum @link CodeResults contains basic return codes for application
 */
@AllArgsConstructor
@Getter
public enum CodeResults {
 resultOk ("00.Result.OK"),
 resultNF ("01.Result.NotFound"),
 resultEr ("02.Result.Error");
 
 String result;
}
