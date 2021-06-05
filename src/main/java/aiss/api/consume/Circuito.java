
package consume;

import java.util.HashMap;
import java.util.List;
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
    "nombre",
    "canalEmision",
    "ciudad",
    "fecha",
    "pilotos"
})
@Generated("jsonschema2pojo")
public class Piloto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("canalEmision")
    private String canalEmision;
    @JsonProperty("ciudad")
    private String ciudad;
    @JsonProperty("fecha")
    private String fecha;
    @JsonProperty("pilotos")
    private List<Piloto__1> pilotos = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Piloto() {
    }

    /**
     * 
     * @param pilotos
     * @param fecha
     * @param ciudad
     * @param canalEmision
     * @param id
     * @param nombre
     */
    public Piloto(String id, String nombre, String canalEmision, String ciudad, String fecha, List<Piloto__1> pilotos) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.canalEmision = canalEmision;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.pilotos = pilotos;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("canalEmision")
    public String getCanalEmision() {
        return canalEmision;
    }

    @JsonProperty("canalEmision")
    public void setCanalEmision(String canalEmision) {
        this.canalEmision = canalEmision;
    }

    @JsonProperty("ciudad")
    public String getCiudad() {
        return ciudad;
    }

    @JsonProperty("ciudad")
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @JsonProperty("fecha")
    public String getFecha() {
        return fecha;
    }

    @JsonProperty("fecha")
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @JsonProperty("pilotos")
    public List<Piloto__1> getPilotos() {
        return pilotos;
    }

    @JsonProperty("pilotos")
    public void setPilotos(List<Piloto__1> pilotos) {
        this.pilotos = pilotos;
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
