package ec.edu.ups.rally.modelo;

import java.util.List;
import javax.persistence.*;

@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String marca;
    private String modelo;
    private String chasis;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Componente> componentes;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Mantenimiento> mantenimientos;

    @OneToOne(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private Piloto piloto;

    @ManyToMany
    @JoinTable(
        name = "vehiculo_carrera",
        joinColumns = @JoinColumn(name = "vehiculo_id"),
        inverseJoinColumns = @JoinColumn(name = "carrera_id")
    )
    private List<Carrera> carreras;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public void setMantenimientos(List<Mantenimiento> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
}