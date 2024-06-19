document.addEventListener("DOMContentLoaded", function() {
    fetch('base.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('header-container').innerHTML = data;
        });
});

