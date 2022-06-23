package cinema.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"reserved"}, allowSetters = true)
public class SeatModel {

    @JsonProperty("row")
    private Integer row;

    @JsonProperty("column")
    private Integer column;
    @JsonProperty("reserved")
    private boolean reserved;

    @JsonProperty("price")
    private Integer price;

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
