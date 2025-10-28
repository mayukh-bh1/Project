// Function to add new recommendation
function addRecommendation() {
    // Get form values
    let name = document.getElementById("name").value;
    let message = document.getElementById("message").value;
    
    // Validate message
    if (message.trim() === "") {
        alert("Please enter a recommendation message");
        return;
    }
    
    // Create recommendation text
    let recommendationText = message;
    if (name.trim() !== "") {
        recommendationText = `"${message}" - ${name}`;
    } else {
        recommendationText = `"${message}"`;
    }
    
    // Create new recommendation element
    const newRecommendation = document.createElement("div");
    newRecommendation.className = "recommendation";
    newRecommendation.innerHTML = `<p>${recommendationText}</p>`;
    
    // Add to recommendations container
    const recommendationsContainer = document.querySelector(".recommendations-container");
    recommendationsContainer.appendChild(newRecommendation);
    
    // Clear form
    document.getElementById("name").value = "";
    document.getElementById("message").value = "";
    
    // Show popup
    showPopup(true);
}

// Function to show/hide popup
function showPopup(show) {
    const popup = document.getElementById("popup");
    if (show) {
        popup.style.display = "block";
    } else {
        popup.style.display = "none";
    }
}

// Close popup when clicking outside
window.onclick = function(event) {
    const popup = document.getElementById("popup");
    if (event.target === popup) {
        showPopup(false);
    }
}

// Smooth scrolling for navigation links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});