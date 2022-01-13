

function logInSubmit(evt) {
    console.log('working');
    
    evt.preventDefault();
    let inputs = document.getElementById("logIn").elements;
    console.log(inputs[1].value);
    let apiURL="http://localhost:7777/login/"+inputs[0].value+" "+inputs[1].value;
    fetch(apiURL) 
    
    .then(response => response.json())  
    .then(json => console.log(json))    
    .catch(err => console.log('Request Failed', err));

}

