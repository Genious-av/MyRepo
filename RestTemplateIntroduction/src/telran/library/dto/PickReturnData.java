package telran.library.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PickReturnData {
public long id;
public long isbn;
public LocalDate date;
}
