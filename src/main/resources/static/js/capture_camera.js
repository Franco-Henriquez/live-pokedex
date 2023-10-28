/**
 * 
 */
 
 
 document.getElementById('bigbluebutton').addEventListener('click', function() {
    // Access the device camera
    console.log("Camera button clicked");
    navigator.mediaDevices.getUserMedia({ video: true })
        .then(function(stream) {
			console.log("Camera loading continued...");
            var video = document.getElementById('video');
            video.srcObject = stream;
            video.play();
        })
        .catch(function(err) {
            console.error('Error accessing the camera: ', err);
        });
});