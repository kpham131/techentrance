var formDom = document.getElementById('form')
var pathArray = window.location.pathname.split('/');
userId = pathArray[2]
var url = "http://localhost:8080/users/" + userId + "/editPersonalInfo"
console.log(url)
formDom.setAttribute('action', url)