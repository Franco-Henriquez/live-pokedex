package com.example.livepokedex.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="pokemon")
public class Pokemon {
	//------------AUTOMATIC--------------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    // other getters and setters removed for brevity
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
        this.updatedAt = new Date(); //we manually added this to also update 'update it' when first created.
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    //------------END AUTOMATIC--------------//

    
    //------------TABLE COLUMNS--------------//

    @NotNull(message="Pokemon Name is required!")
    @Size(min=3, max=15, message="Pokemon Name must be between 3 and 15 characters")
    private String pokemonName;
    
    @NotNull(message="Pokemon's Pokedex ID is required!")
    private Long dexId;
    
	/*
	 * @Transient //just tells the below instruction model not to save to the db
	 * private String searchString;
	 */

    
    //----------END TABLE COLUMNS------------//

    //------------RELATIONSHIPS--------------//
    //ONE POKEMON - (SEARCHED BY) - MULTIPLE TRAINERS
    //TRAINER_POKEMON N:M - MANY TO MANY
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "trainer_pokemon", 
        joinColumns = @JoinColumn(name = "pokemon_id"), 
        inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    private List<Trainer> trainerList; //list trainers who have searched this card
    
    
    
    //ONE POKEMON - (HISTORY) - MULTIPLE SEARCHES BY TRAINER
    //TRAINER_POKEMON N:M - MANY TO MANY
    @OneToMany(mappedBy="pokemon", fetch = FetchType.LAZY)
    private List<SearchHistory> trainer; //list trainers who have searched this card
        
    //-----------END RELATIONSHIPS-----------//
    
    
    
    //------------CONSTRUCTOR / GETTERS / SETTERS --------------//
    public Pokemon() {}
	public Pokemon(
			@NotNull(message = "Pokemon Name is required!") @Size(min = 3, max = 15, message = "Pokemon Name must be between 3 and 15 characters") String pokemonName,
			@NotNull(message = "Pokemon's Pokedex ID is required!") Long dexId, List<Trainer> trainerList) {
		this.pokemonName = pokemonName;
		this.dexId = dexId;
		this.trainerList = trainerList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPokemonName() {
		return pokemonName;
	}
	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}
	public Long getDexId() {
		return dexId;
	}
	public void setDexId(Long dexId) {
		this.dexId = dexId;
	}
	public List<Trainer> getTrainerList() {
		return trainerList;
	}
	public void setTrainerList(List<Trainer> trainerList) {
		this.trainerList = trainerList;
	}
    
	
    
    
    
}
