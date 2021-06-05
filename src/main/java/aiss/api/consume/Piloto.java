
package aiss.api.consume;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "escuderia",
    "nombre",
    "edad",
    "dorsal",
    "victorias"
})
@Generated("jsonschema2pojo")
public class Piloto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("escuderia")
    private String escuderia;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("edad")
    private Integer edad;
    @JsonProperty("dorsal")
    private Integer dorsal;
    @JsonProperty("victorias")
    private Integer victorias;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("escuderia")
    public String getEscuderia() {
        return escuderia;
    }

    @JsonProperty("escuderia")
    public void setEscuderia(String escuderia) {
        this.escuderia = escuderia;
    }

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("edad")
    public Integer getEdad() {
        return edad;
    }

    @JsonProperty("edad")
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @JsonProperty("dorsal")
    public Integer getDorsal() {
        return dorsal;
    }

    @JsonProperty("dorsal")
    public void setDorsal(Integer dorsal) {
        this.dorsal = dorsal;
    }

    @JsonProperty("victorias")
    public Integer getVictorias() {
        return victorias;
    }

    @JsonProperty("victorias")
    public void setVictorias(Integer victorias) {
        this.victorias = victorias;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
