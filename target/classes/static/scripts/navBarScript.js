var profileDom = document.getElementById('profile')
var pathArray = window.location.pathname.split('/');
userId = pathArray[2]
var url = "http://localhost:8080/users/" + userId + "/profile"
console.log(url)
profileDom.setAttribute('href', url)