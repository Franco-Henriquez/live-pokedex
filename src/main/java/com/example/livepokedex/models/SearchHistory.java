package com.example.livepokedex.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="searches")
public class SearchHistory {
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
    @NotEmpty(message="Search type is required!")
    @Size(min=3, max=15, message="Search type must be between 3 and 15 characters")
    private String searchType; //DEX or CARD
    
    //there may be no need for this, as it would be easier 'easier' or better to do a relationship instead
    @NotEmpty(message="Search string is required!")
    @Size(min=1, max=8, message="Search string must be between 3 and 8 characters long")
    @Column(columnDefinition="TEXT")
    private String searchString; //Pikachu/25/ S20/34
        
    //----------END TABLE COLUMNS------------//
    
    
    //------------RELATIONSHIPS--------------//
    
    
	/*
	 * //ONE HISTORY ENTRY -(SEARCHED)- ONE CARD //SEARCH CARD N:M - ONE TO ONE
	 * 
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "card_id") private Card card;
	 * 
	 * //ONE HISTORY ENTRY -(SEARCHED)- BY ONE TRAINER
	 * 
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "trainer_id") private Trainer trainer;
	 * 
	 * //ONE HISTORY ENTRY -(SEARCHED)- ONE POKEMON
	 * 
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "pokemon_id") private Pokemon pokemon;
	 */
    
    //ONE HISTORY ENTRY -(SEARCHED)- ONE CARD
    //SEARCH CARD N:M - ONE TO ONE
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;
    
    //ONE HISTORY ENTRY -(SEARCHED)- BY ONE TRAINER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    
    //ONE HISTORY ENTRY -(SEARCHED)- ONE POKEMON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    
    //NOTE: We only want to pull a single trainer and a single card, 
    //...so we wont be seeing a middle table like in a one-to-many
    
    
    //-----------END RELATIONSHIPS-----------//
    
    
    
    //------------CONSTRUCTOR / GETTERS / SETTERS --------------//
    public SearchHistory() {}
	public SearchHistory(
			@NotEmpty(message = "Search type is required!") @Size(min = 3, max = 15, message = "Search type must be between 3 and 15 characters") String searchType,
			@NotEmpty(message = "Search string is required!") @Size(min = 1, max = 8, message = "Search string must be between 3 and 8 characters long") String searchString,
			Card card, Trainer trainer, Pokemon pokemon) {
		this.searchType = searchType;
		this.searchString = searchString;
		this.card = card;
		this.trainer = trainer;
		this.pokemon = pokemon;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Trainer getTrainer() {
		return trainer;
	}
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	public Pokemon getPokemon() {
		return pokemon;
	}
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	

}
