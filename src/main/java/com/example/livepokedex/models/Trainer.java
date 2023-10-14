package com.example.livepokedex.models;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Range;
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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="trainers")
public class Trainer {
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
    @NotEmpty(message="Trainer name is required!")
    @Size(min=3, max=15, message="Trainer name must be between 3 and 15 characters")
    private String trainerName;
       
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")//this max 128 limit we use speficially because of bcrypt
    @Column(columnDefinition="TEXT")//This helps define the type of data in the DB, and set a hard limit of 256?
    private String password;
    
    @Transient //just tells the below instruction model not to save to the db
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirmPassword;
    
    @Range(min=1, message="Favorite pokemon ID must be 1-4 digits long")
    private Integer favoritePokemon = 1;
    
    @Size(min=3, max=8, message="Favorite pokemon CARD must be 3-8 characters long")
    private String favoriteCard = "0123"; //this will store deckId+cardNum //find a way to separate or add a divider to 'split' later
    //----------END TABLE COLUMNS------------//

    
    //------------RELATIONSHIPS--------------//
    //ONE TRAINER -(SCANNED)- MANY CARDS
    //TRAINER_CARD N:M - MANY TO MANY
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "trainer_card", 
        joinColumns = @JoinColumn(name = "trainer_id"), 
        inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cardList; //current card list scanned by trainers
    
    //ONE TRAINER -(SEARCHED)- MANY POKEMON
    //TRAINER_CARD N:M - MANY TO MANY
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "trainer_pokemon", 
        joinColumns = @JoinColumn(name = "trainer_id"), 
        inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<Pokemon> pokemonList; //current card list scanned by trainers
    
    
    //ONE TRAINER - (HISTORY) - MULTIPLE SEARCHES FOR POKEMON
    //TRAINER_POKEMON N:M - MANY TO MANY
    @OneToMany(mappedBy="trainer", fetch = FetchType.LAZY)
    private List<SearchHistory> pokemon; //list trainers who have searched this card
    
    
    //-----------END RELATIONSHIPS-----------//
    
    
    
    //------------CONSTRUCTOR / GETTERS / SETTERS --------------//
    public Trainer() {}
    public Trainer(
			@NotEmpty(message = "Trainer name is required!") @Size(min = 3, max = 15, message = "Trainer name must be between 3 and 15 characters") String trainerName,
			@NotEmpty(message = "Email is required!") @Email(message = "Please enter a valid email!") String email,
			@NotEmpty(message = "Password is required!") @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") String password,
			@NotEmpty(message = "Confirm Password is required!") @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters") String confirmPassword,
			@Range(min = 1, message = "Favorite pokemon ID must be 1-4 digits long") Integer favoritePokemon,
			@Size(min = 3, max = 8, message = "Favorite pokemon CARD must be 3-8 characters long") String favoriteCard,
			List<Card> cardList, List<Pokemon> pokemonList, List<SearchHistory> pokemon) {
		this.trainerName = trainerName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.favoritePokemon = favoritePokemon;
		this.favoriteCard = favoriteCard;
		this.cardList = cardList;
		this.pokemonList = pokemonList;
		this.pokemon = pokemon;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Integer getFavoritePokemon() {
		return favoritePokemon;
	}
	public void setFavoritePokemon(Integer favoritePokemon) {
		this.favoritePokemon = favoritePokemon;
	}
	public String getFavoriteCard() {
		return favoriteCard;
	}
	public void setFavoriteCard(String favoriteCard) {
		this.favoriteCard = favoriteCard;
	}
	public List<Card> getCardList() {
		return cardList;
	}
	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}
	public List<Pokemon> getPokemonList() {
		return pokemonList;
	}
	public void setPokemonList(List<Pokemon> pokemonList) {
		this.pokemonList = pokemonList;
	}
	public List<SearchHistory> getPokemon() {
		return pokemon;
	}
	public void setPokemonHistoryList(List<SearchHistory> pokemon) {
		this.pokemon = pokemon;
	}
    
    
       

}
