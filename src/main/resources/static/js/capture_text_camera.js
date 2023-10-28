// Function to handle image capture
function captureImage() {
    // Get the video element and canvas
    const video = document.getElementById('video');
    const canvas = document.getElementById('canvas');
    const context = canvas.getContext('2d');

    // Draw the video frame on the canvas
    context.drawImage(video, 0, 0, canvas.width, canvas.height);

    // Convert canvas to base64 image data URL
    const imageData = canvas.toDataURL('image/png');
    
    // Show the captured image on the page (optional)
    //const capturedImage = document.getElementById('capturedImage');
    //capturedImage.src = imageData;

    // Call the OCR function with the captured image data
    performOCR(imageData);
}

// Function to perform OCR on the captured image
function performOCR(imageData) {
    // Send the imageData to the server for processing (you can use AJAX or fetch API)
    // Example using fetch API:
    fetch('/card/recognize-text', {
        method: 'POST',
        body: JSON.stringify({ imageData: imageData }),
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        // Handle OCR results (data.recognizedText)
        //.response is exactly what it's called in the ImageRecognition model
        console.log('OCR Result:', data.response);
        
        // Display the recognized text on the page (optional)
        const recognizedTextElement = document.getElementById('stats');
        recognizedTextElement.textContent = data.response;
    })
    .catch(error => {
        console.error('Error during OCR processing:', error);
    });
}

// Event listener for the capture button
document.getElementById('bigbluebutton').addEventListener('click', () => {
    captureImage();
});

// Event listener for the recognize button
document.getElementById('barbutton1').addEventListener('click', () => {
    // Perform OCR using the captured image data (you can call performOCR function here)
    // performOCR(imageData);
    // Note: Ensure the 'imageData' variable is accessible here or pass it as a parameter.
});
