document.addEventListener("DOMContentLoaded", function() {
    fetch('../layout/base.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('header-container').innerHTML = data;
            // document.getElementById("main-content").innerHTML = data;
        });
});