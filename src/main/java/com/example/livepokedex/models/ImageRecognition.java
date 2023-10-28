package com.example.livepokedex.models;

public class ImageRecognition {
    private String response;
    private String imageData;
    private String otherField;
    
    public ImageRecognition(String response) {
        this.response = response;
    }
    
    
    public ImageRecognition() {}
    public ImageRecognition(String response, String imageData, String otherField) {
		this.response = response;
		this.imageData = imageData;
		this.otherField = otherField;
	}



	// Getters and setters
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getOtherField() {
		return otherField;
	}

	public void setOtherField(String otherField) {
		this.otherField = otherField;
	}
}
