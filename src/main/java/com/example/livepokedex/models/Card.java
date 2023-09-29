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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="cards")
public class Card {
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
    @NotEmpty(message="Card name is required!")
    @Size(min=3, max=15, message="Card name must be between 3 and 15 characters")
    private String cardName;
    
    @NotEmpty(message="Card Number is required!")
    @Size(min=1, max=5, message="Card Number must be between 1 and 5 numbers")
    private Long cardNum;
    
    @NotEmpty(message="Deck ID is required!")
    @Size(min=1, max=5, message="Deck ID must be between 1 and 5 characters")
    private String cardDeckId;
    //----------END TABLE COLUMNS------------//

    
    //------------RELATIONSHIPS--------------//
    //ONE CARD - (SCANNED BY) - MULTIPLE TRAINERS
    //TRAINER_CARD N:M - MANY TO MANY
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "trainer_card", 
        joinColumns = @JoinColumn(name = "card_id"), 
        inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    private List<Trainer> trainerList; //list of courses that student is in
    //-----------END RELATIONSHIPS-----------//
    
    
    //------------CONSTRUCTOR / GETTERS / SETTERS --------------//
    public Card() {}
	public Card(
			@NotEmpty(message = "Card name is required!") @Size(min = 3, max = 15, message = "Card name must be between 3 and 15 characters") String cardName,
			@NotEmpty(message = "Card Number is required!") @Size(min = 1, max = 5, message = "Card Number must be between 1 and 5 numbers") Long cardNum,
			@NotEmpty(message = "Deck ID is required!") @Size(min = 1, max = 5, message = "Deck ID must be between 1 and 5 characters") String cardDeckId,
			List<Trainer> trainerList) {
		this.cardName = cardName;
		this.cardNum = cardNum;
		this.cardDeckId = cardDeckId;
		this.trainerList = trainerList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public Long getCardNum() {
		return cardNum;
	}
	public void setCardNum(Long cardNum) {
		this.cardNum = cardNum;
	}
	public String getCardDeckId() {
		return cardDeckId;
	}
	public void setCardDeckId(String cardDeckId) {
		this.cardDeckId = cardDeckId;
	}
	public List<Trainer> getTrainerList() {
		return trainerList;
	}
	public void setTrainerList(List<Trainer> trainerList) {
		this.trainerList = trainerList;
	}

}
