
package aiss.api.consume;

import java.util.HashMap;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 3b3e5c14e4acb5637dc24c691070dd37b849b773
=======

import java.util.List;

>>>>>>> 1224f1626e117592fed1d14cc714c58b93ce9bce
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
public class CircuitosResource {

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
<<<<<<< HEAD
<<<<<<< HEAD
    private Object pilotos;
=======
    private List<Piloto> pilotos = null;
>>>>>>> 3b3e5c14e4acb5637dc24c691070dd37b849b773
=======


    private List<Piloto> pilotos;

>>>>>>> 1224f1626e117592fed1d14cc714c58b93ce9bce
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
<<<<<<< HEAD
<<<<<<< HEAD
    public Object getPilotos() {
=======
    public List<Piloto> getPilotos() {
>>>>>>> 3b3e5c14e4acb5637dc24c691070dd37b849b773
=======

  

    public List<Piloto> getPilotos() {

>>>>>>> 1224f1626e117592fed1d14cc714c58b93ce9bce
        return pilotos;
    }

    @JsonProperty("pilotos")
<<<<<<< HEAD
<<<<<<< HEAD
    public void setPilotos(Object pilotos) {
=======
    public void setPilotos(List<Piloto> pilotos) {
>>>>>>> 3b3e5c14e4acb5637dc24c691070dd37b849b773
=======

    public void setPilotos(List<Piloto> pilotos) {
>>>>>>> 1224f1626e117592fed1d14cc714c58b93ce9bce
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
